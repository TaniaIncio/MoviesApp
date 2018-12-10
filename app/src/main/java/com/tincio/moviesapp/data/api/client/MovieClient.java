package com.tincio.moviesapp.data.api.client;

import com.tincio.moviesapp.data.api.retrofit.MovieRetrofitClient;
import com.tincio.moviesapp.data.model.Response;
import com.tincio.moviesapp.data.model.Movie;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MovieClient extends MovieRetrofitClient implements MovieService {

  @Override
  public Observable<Movie> search(String key, String query) {
    return getSpotifyService().searchMovie(key, query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<Movie> getMovies(String key, Integer movieId) {
    return getSpotifyService().getMovies(key, movieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<Response> getDetailMovie(String key, Integer id) {
    return getSpotifyService().getMovieId(id, key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }
}
