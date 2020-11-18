package com.Project.OMRReader.Models.RetrofitModels;

import com.google.gson.annotations.SerializedName;

public class StudentInfoResponse {
    @SerializedName("token")
    private String token;
    @SerializedName("id")
    private int id;

    public void setToken(String token){ this.token=token; }

    public void setId(int id) { this.id = id; }

    public String getToken(){ return token; }

    public int getId() {
        return id;
    }
}
