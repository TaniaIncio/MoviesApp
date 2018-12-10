package com.tincio.moviesapp.presentation.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tincio.moviesapp.R;
import com.tincio.moviesapp.data.api.client.MovieClient;
import com.tincio.moviesapp.data.model.Response;
import com.tincio.moviesapp.data.model.Result;
import com.tincio.moviesapp.domain.interactor.DetailMovieInteractor;
import com.tincio.moviesapp.presentation.presenter.DetailMoviePresenter;
import com.tincio.moviesapp.presentation.util.MovieImageBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity implements DetailMoviePresenter.View{

    private DetailMoviePresenter DetailMoviePresenter;
    @BindView(R.id.iv_movie) ImageView iv_movie;
    @BindView(R.id.iv_path) ImageView iv_path;
    @BindView(R.id.txt_title) TextView txt_title;
    @BindView(R.id.txt_tagline) TextView txt_tagline;
    @BindView(R.id.txt_overview) TextView txt_overview;
    @BindView(R.id.txt_status) TextView txt_status;
    @BindView(R.id.txt_release) TextView txt_release;
    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    Result movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ButterKnife.bind(this);
        movie = (Result)getIntent().getSerializableExtra(EXTRA_MOVIE);
        DetailMoviePresenter = new DetailMoviePresenter(new DetailMovieInteractor(new MovieClient()));
        DetailMoviePresenter.setView(this);
        DetailMoviePresenter.getMovieId(movie.getId());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void showMovie(Response movieDetail) {
        MovieImageBuilder movieImageBuilder = new MovieImageBuilder();
        if (!TextUtils.isEmpty(movieDetail.getBackdropPath())) {
            Picasso.with(this)
                    .load(movieImageBuilder.buildPathUrl(movie.getBackdropPath()))
                    .placeholder(R.mipmap.ic_placeholder)
                    .into(iv_movie);
        }
        if (!TextUtils.isEmpty(movieDetail.getPosterPath())) {
            Picasso.with(this)
                    .load(movieImageBuilder.buildPathUrl(movie.getPosterPath()))
                    .placeholder(R.mipmap.ic_placeholder)
                    .into(iv_path);
        }
        txt_tagline.setText(movieDetail.getTagline());
        txt_title.setText(movieDetail.getTitle());
        txt_status.setText(movieDetail.getStatus());
        txt_release.setText(movieDetail.getReleaseDate());
        txt_overview.setText(movieDetail.getOverview());
    }

    @Override
    public Context context() {
        return this;
    }
}
