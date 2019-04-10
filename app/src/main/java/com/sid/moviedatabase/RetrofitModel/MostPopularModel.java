package com.sid.moviedatabase.RetrofitModel;

import android.view.WindowManager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MostPopularModel {

    @SerializedName("page")
    @Expose
    private int page;


    @SerializedName("results")
    @Expose
    private List<movieResults> movieResults = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MostPopularModel.movieResults> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<MostPopularModel.movieResults> movieResults) {
        this.movieResults = movieResults;
    }

    public class movieResults {


        @SerializedName("id")
        @Expose
        private  String movieId;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("poster_path")
        @Expose
        private String imgUrl;

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}