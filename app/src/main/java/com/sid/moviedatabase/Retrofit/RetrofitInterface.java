package com.sid.moviedatabase.Retrofit;

import com.sid.moviedatabase.RetrofitModel.InTheatreModel;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {


    @GET("popular/")
    Call<MostPopularModel> popularMovies(@Query("api_key")String key,@Query("page")String pages);

    @GET("now_playing/")
    Call<InTheatreModel> inTheatreMovies(@Query("api_key")String key,@Query("page")String pages);
}
