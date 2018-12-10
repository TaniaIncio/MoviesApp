

package com.tincio.moviesapp.domain.interactor;

import com.tincio.moviesapp.data.api.client.MovieService;
import com.tincio.moviesapp.data.model.Response;

import io.reactivex.Observable;

public class DetailMovieInteractor {

  private MovieService movieService;

  public DetailMovieInteractor(MovieService movieService) {
    this.movieService = movieService;
  }

  public Observable<Response> getMovieId(String key, Integer id) {
    return movieService.getDetailMovie(key, id);
  }


}
