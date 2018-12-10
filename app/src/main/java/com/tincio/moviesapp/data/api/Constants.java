

package com.tincio.moviesapp.data.api;

public class Constants {

  public static final String SPOTIFY_API = "https://api.themoviedb.org/3/";
  public static final String API_KEY = "Authorization";

  public static final String TOKEN = "1f54bd990f1cdfb230adb312546d765d";
  public static final String ACCESS_TOKEN = "Bearer 1f54bd990f1cdfb230adb312546d765d";

  public static final class Endpoint {

    public static final String MOVIE_SEARCH = "search/movie";
    public static final String MOVIE_LIST =
        "movie/upcoming";
    public static final String MOVIE_DETAIL=
        "movie/{movieId}";
  }

  public static final class Params {
    public static final String QUERY_SEARCH = "query";
    public static final String QUERY_SEARCH_KEY = "api_key";
    public static final String QUERY_ROWS = "page";
    public static final String MOVIE_ID = "movieId";
  }
}
