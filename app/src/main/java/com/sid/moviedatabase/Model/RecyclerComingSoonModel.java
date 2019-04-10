package com.sid.moviedatabase.Model;

public class RecyclerComingSoonModel {

    String id,name,releaseDate,imgUrl;

    public RecyclerComingSoonModel(String id, String name, String releaseDate, String imgUrl) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
