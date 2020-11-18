package com.Project.OMRReader.Services;

import com.Project.OMRReader.Models.RetrofitModels.AdminHomeResponse;
import com.Project.OMRReader.Models.RetrofitModels.QuizResponse;
import com.Project.OMRReader.Models.RetrofitModels.ResponseStatus;
import com.Project.OMRReader.Models.RetrofitModels.StudentInfoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("Adminlogin/")
    @FormUrlEncoded
    Call<ResponseStatus> adminLogin(@Field("username") String username,@Field("password") String password);

    @POST("login/")
    @FormUrlEncoded
    Call<StudentInfoResponse> studentLogin(@Field("email") String email, @Field("password") String password );

    @GET("allQuizInfo/")
    Call<ArrayList<QuizResponse>> getAllQuiz();

    @GET("adminHomeInfo/")
    Call<AdminHomeResponse> adminHomeInfo();

    @POST("createQuiz/")
    @FormUrlEncoded
    Call<ResponseStatus> createQuiz(@Field("answer") String answer,@Field("answerType") String answerType,@Field("negative") boolean negative,@Field("description") String description,@Field("topic") String topic,@Field("initiatedBy") String initiatedBy);
}
