package com.sid.moviedatabase.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InTheatreModel {

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

        @SerializedName("vote_average")
        @Expose
        private String rating;

        @SerializedName("poster_path")
        @Expose
        private String imgUrl;

        @SerializedName("release_date")
        @Expose
        private String date;

        @SerializedName("vote_count")
        @Expose
        private String count;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

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

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
