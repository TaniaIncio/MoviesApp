package com.tincio.moviesapp.presentation.util;


import com.tincio.moviesapp.data.api.Constants;

public class MovieImageBuilder {

    private static final String URL = "https://image.tmdb.org/t/p/w780";

    public String buildPathUrl(String posterPath) {
        return URL + posterPath + "?api_key=" + Constants.API_KEY;
    }

}
