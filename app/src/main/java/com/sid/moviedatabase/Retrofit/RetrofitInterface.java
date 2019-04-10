package com.sid.moviedatabase.Retrofit;

import com.sid.moviedatabase.RetrofitModel.CastModels;
import com.sid.moviedatabase.RetrofitModel.ComingSoonModel;
import com.sid.moviedatabase.RetrofitModel.InTheatreModel;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;
import com.sid.moviedatabase.RetrofitModel.MovieDetailsModel;
import com.sid.moviedatabase.RetrofitModel.TopRatedModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {


    @GET("popular/")
    Call<MostPopularModel> popularMovies(@Query("api_key")String key,@Query("page")String pages);

    @GET("now_playing/")
    Call<InTheatreModel> inTheatreMovies(@Query("api_key")String key,@Query("page")String pages);

    @GET("upcoming/")
    Call<ComingSoonModel> comingSoon(@Query("api_key")String key,@Query("page")String pages);

    @GET("top_rated/")
    Call<TopRatedModel> topRated(@Query("api_key")String key,@Query("page")String pages);

    @GET("{movie_id}")
    Call<MovieDetailsModel> getDetails(@Path("movie_id")String id, @Query("api_key")String key);

    @GET("{movie_id}/credits")
    Call<CastModels> getCast(@Path("movie_id")String id, @Query("api_key")String key);
}
