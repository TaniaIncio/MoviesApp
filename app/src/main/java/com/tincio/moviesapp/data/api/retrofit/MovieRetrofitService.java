

package com.tincio.moviesapp.data.api.retrofit;

import com.tincio.moviesapp.data.api.Constants;
import com.tincio.moviesapp.data.model.Response;
import com.tincio.moviesapp.data.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieRetrofitService {

  @GET(Constants.Endpoint.MOVIE_SEARCH)
  Observable<Movie> searchMovie(
          @Query(Constants.Params.QUERY_SEARCH_KEY) String key, @Query(Constants.Params.QUERY_SEARCH) String movie);

  @GET(Constants.Endpoint.MOVIE_LIST)
  Observable<Movie> getMovies(
          @Query(Constants.Params.QUERY_SEARCH_KEY) String key, @Query(Constants.Params.QUERY_ROWS) Integer movie);

  @GET(Constants.Endpoint.MOVIE_DETAIL)
  Observable<Response> getMovieId(
          @Path(Constants.Params.MOVIE_ID) Integer id, @Query(Constants.Params.QUERY_SEARCH_KEY) String key);
}
