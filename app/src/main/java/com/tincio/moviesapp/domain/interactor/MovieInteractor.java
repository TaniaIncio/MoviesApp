

package com.tincio.moviesapp.domain.interactor;

import com.tincio.moviesapp.data.api.client.MovieService;
import com.tincio.moviesapp.data.model.Movie;

import io.reactivex.Observable;

public class MovieInteractor {

  private MovieService movieService;

  public MovieInteractor(MovieService movieService) {
    this.movieService = movieService;
  }

  public Observable<Movie> searchMovie(String key, String query) {
    return movieService.search(key, query);
  }

  public Observable<Movie> getMovies(String key, Integer count) {
    return movieService.getMovies(key, count);
  }

}
