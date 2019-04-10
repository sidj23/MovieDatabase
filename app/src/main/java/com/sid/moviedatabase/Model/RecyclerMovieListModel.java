package com.sid.moviedatabase.Model;

public class RecyclerMovieListModel {
    String id,name,rating,vote,date,imgUrl;

    public RecyclerMovieListModel(String id, String name, String rating, String vote, String date, String imgUrl) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.vote = vote;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}


