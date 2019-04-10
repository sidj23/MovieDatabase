package com.sid.moviedatabase.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ComingSoonModel {

    @SerializedName("results")
    @Expose
    private List<Results> results = new ArrayList<>();

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results {

        @SerializedName("id")
        @Expose
        private String movieId;

        @SerializedName("title")
        @Expose
        private String movieName;

        @SerializedName("poster_path")
        @Expose
        private String imgUrl;

        @SerializedName("release_date")
        @Expose
        private String releaseDate;

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }
    }
}