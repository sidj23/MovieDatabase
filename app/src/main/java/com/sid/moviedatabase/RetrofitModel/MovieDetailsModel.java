package com.sid.moviedatabase.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
