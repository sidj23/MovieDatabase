package com.sid.moviedatabase.Model;

public class RecyclerPopularModel {

    String title;
    String rank;
    String imgUrl;
    String movieId;

    public RecyclerPopularModel(String title, String rank, String imgUrl, String movieId) {
        this.title = title;
        this.rank = rank;
        this.imgUrl=imgUrl;
        this.movieId=movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
