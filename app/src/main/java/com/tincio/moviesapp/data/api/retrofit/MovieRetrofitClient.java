package com.tincio.moviesapp.data.api.retrofit;

import com.tincio.moviesapp.data.api.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class MovieRetrofitClient {

  private MovieRetrofitService movieRetrofitService;

  public MovieRetrofitClient() {
    initRetrofit();
  }

  private void initRetrofit() {
    Retrofit retrofit = retrofitBuilder();
    movieRetrofitService = retrofit.create(getSpotifyServiceClass());
  }

  private Retrofit retrofitBuilder() {
    return new Retrofit.Builder().baseUrl(Constants.SPOTIFY_API)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build();
  }

  private OkHttpClient getOkHttpClient() {
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    ApiInterceptor apiInterceptor = new ApiInterceptor();
    client.addInterceptor(apiInterceptor);
    return client.build();
  }


  private Class<MovieRetrofitService> getSpotifyServiceClass() {
    return MovieRetrofitService.class;
  }

  protected MovieRetrofitService getSpotifyService() {
    return movieRetrofitService;
  }
}

