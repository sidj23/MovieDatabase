package com.sid.moviedatabase.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("backdrop_path")
    @Expose
    private String imgBackUrl;

    @SerializedName("poster_path")
    @Expose
    private String imgProfileUrl;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private String rating;

    @SerializedName("vote_count")
    @Expose
    private String vote;

    @SerializedName("release_date")
    @Expose
    private String date;

    @SerializedName("overview")
    @Expose
    private String plot;

    @SerializedName("genres")
    @Expose
    private List<Genre> genre = new ArrayList<>();

    @SerializedName("runtime")
    @Expose
    private int runtime;

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgBackUrl() {
        return imgBackUrl;
    }

    public void setImgBackUrl(String imgBackUrl) {
        this.imgBackUrl = imgBackUrl;
    }

    public String getImgProfileUrl() {
        return imgProfileUrl;
    }

    public void setImgProfileUrl(String imgProfileUrl) {
        this.imgProfileUrl = imgProfileUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public class Genre {

        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
