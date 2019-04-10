package com.sid.moviedatabase.Model;

public class RecyclerCrewModel {

    String name,job,profUrl;

    public RecyclerCrewModel(String name, String job, String profUrl) {
        this.name = name;
        this.job = job;
        this.profUrl = profUrl;
    }

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

    public String getProfUrl() {
        return profUrl;
    }

    public void setProfUrl(String profUrl) {
        this.profUrl = profUrl;
    }
}
