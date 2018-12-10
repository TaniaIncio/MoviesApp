package com.tincio.moviesapp.presentation.adapter;


import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tincio.moviesapp.R;
import com.tincio.moviesapp.data.model.Result;
import com.tincio.moviesapp.presentation.util.MovieImageBuilder;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

  private List<Result> movies;
  private ItemClickListener itemClickListener;

  public ResultsAdapter() {
    movies = Collections.emptyList();
  }

  @Override
  public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
    return new ResultsViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(ResultsViewHolder holder, int position) {
    Result movie = movies.get(position);
    holder.movie = movie;
    holder.textView.setText(movie.getOriginalTitle());
    MovieImageBuilder movieImageBuilder = new MovieImageBuilder();
    if (!TextUtils.isEmpty(movie.getPosterPath())) {
      Picasso.with(holder.imageView.getContext())
              .load(movieImageBuilder.buildPathUrl(movie.getPosterPath()))
              .placeholder(R.mipmap.ic_placeholder)
              .into(holder.imageView);
    }

    holder.itemView.setOnClickListener((View view) -> {
      if (itemClickListener != null) {
        itemClickListener.onItemClick(movie, position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

  public void setResults(List<Result> movies) {
    this.movies = movies;
  }

  public void setItemClickListener(ItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public interface ItemClickListener {
    void onItemClick(Result movie, int position);
  }

  public static class ResultsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_view_movie_image)
    ImageView imageView;
    @BindView(R.id.txt_movie_name)
    TextView textView;

    Result movie;
    View itemView;

    public ResultsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.itemView = itemView;
    }
  }
}
