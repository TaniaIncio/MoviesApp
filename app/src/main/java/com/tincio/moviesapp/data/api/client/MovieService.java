package com.tincio.moviesapp.data.api.client;

import com.tincio.moviesapp.data.model.Response;
import com.tincio.moviesapp.data.model.Movie;

import io.reactivex.Observable;

public interface MovieService {

  Observable<Movie> search(String key, String query);

  Observable<Movie> getMovies(String key, Integer count);

  Observable<Response> getDetailMovie(String key, Integer id);
}
