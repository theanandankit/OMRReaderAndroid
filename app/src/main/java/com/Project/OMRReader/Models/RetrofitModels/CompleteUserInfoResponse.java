package com.Project.OMRReader.Models.RetrofitModels;

public class CompleteUserInfoResponse {

    String about;

    Integer contactNo;

    String address;

    String batch;

    UserInfoResponse user_info;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getContactNo() {
        return contactNo;
    }

    public void setContactNo(Integer contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public UserInfoResponse getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoResponse user_info) {
        this.user_info = user_info;
    }
}
