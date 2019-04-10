package com.sid.moviedatabase.RetrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

public class CastModels {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("cast")
    @Expose
    private List<Cast> casts = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("crew")
    @Expose
    private List<Crew> crew = new ArrayList<>();

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public class Cast {

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("character")
        @Expose
        private String character;

        @SerializedName("profile_path")
        @Expose
        private String imgProfile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getImgProfile() {
            return imgProfile;
        }

        public void setImgProfile(String imgProfile) {
            this.imgProfile = imgProfile;
        }
    }


    public class Crew{

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("job")
        @Expose
        private String job;

        @SerializedName("profile_path")
        @Expose
        private String profile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }

}
