package com.sid.moviedatabase.Model;

public class RecyclerTopRatedModel {
    String id,name,rating,imgUrl;

    public RecyclerTopRatedModel(String id, String name, String rating, String imgUrl) {
        this.id = id;
        this.name = name;
        this.rating = rating;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
