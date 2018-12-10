package com.tincio.moviesapp.presentation.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tincio.moviesapp.R;
import com.tincio.moviesapp.data.api.client.MovieClient;
import com.tincio.moviesapp.data.model.Result;
import com.tincio.moviesapp.domain.interactor.MovieInteractor;
import com.tincio.moviesapp.presentation.adapter.ResultsAdapter;
import com.tincio.moviesapp.presentation.presenter.MoviePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviePresenter.View, SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_movies) RecyclerView rv_movies;
    @BindView(R.id.pv_movies) ProgressBar pv_movies;
    @BindView(R.id.iv_movies) ImageView iv_movie;
    @BindView(R.id.txt_line_movies) TextView txt_line_movies;
    @BindView(R.id.txt_subline_movies) TextView txt_sub_line_movies;

    private MoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        moviePresenter = new MoviePresenter(new MovieInteractor(new MovieClient()));
        moviePresenter.setView(this);
        moviePresenter.getMovies();
        //  setHasOptionsMenu(true);
        setupToolbar();
        setupRecyclerView();
    }


    @Override
    public void onDestroy() {
        moviePresenter.terminate();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        setupSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        moviePresenter.onSearchMovie(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public void showLoading() {
        pv_movies.setVisibility(View.VISIBLE);
        iv_movie.setVisibility(View.GONE);
        txt_line_movies.setVisibility(View.GONE);
        txt_sub_line_movies.setVisibility(View.GONE);
        rv_movies.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pv_movies.setVisibility(View.VISIBLE);
        rv_movies.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovieNotFoundMessage() {
        pv_movies.setVisibility(View.GONE);
        txt_line_movies.setVisibility(View.VISIBLE);
        iv_movie.setVisibility(View.VISIBLE);
        txt_line_movies.setText(getString(R.string.error_movie_not_found));
        iv_movie.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_not_found));
    }

    @Override
    public void showConnectionErrorMessage() {
        pv_movies.setVisibility(View.GONE);
        txt_line_movies.setVisibility(View.VISIBLE);
        iv_movie.setVisibility(View.VISIBLE);
        txt_line_movies.setText(getString(R.string.error_internet_connection));
        iv_movie.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_not_internet));
    }

    @Override
    public void showServerError() {
        pv_movies.setVisibility(View.GONE);
        txt_line_movies.setVisibility(View.VISIBLE);
        iv_movie.setVisibility(View.VISIBLE);
        txt_line_movies.setText(getString(R.string.error_internet_connection));
        iv_movie.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_not_found));
    }

    @Override
    public void renderMovies(List<Result> movies) {
        ResultsAdapter adapter = (ResultsAdapter) rv_movies.getAdapter();
        adapter.setResults(movies);
        rv_movies.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    private void setupSearchView(Menu menu) {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setMaxWidth(toolbar.getWidth());
        searchView.setOnQueryTextListener(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
    }

    private void setupRecyclerView() {
        rv_movies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ResultsAdapter adapter = new ResultsAdapter();
        adapter.setItemClickListener(
                (Result movies, int position) -> moviePresenter.launchMovieDetail(movies));
        rv_movies.setAdapter(adapter);
    }

    @Override
    public void launchMovieDetail(Result movie) {
        Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public Context context() {
        return this;
    }
}
