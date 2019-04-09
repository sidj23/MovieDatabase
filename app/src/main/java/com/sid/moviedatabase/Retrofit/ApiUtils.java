package com.sid.moviedatabase.Retrofit;

public class ApiUtils {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";


    public static RetrofitInterface getServiceClass(){
        return RetrofitApi.getRetrofit(BASE_URL).create(RetrofitInterface.class);
    }
}
