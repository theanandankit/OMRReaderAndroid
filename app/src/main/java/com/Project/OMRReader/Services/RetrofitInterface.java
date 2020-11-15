package com.Project.OMRReader.Services;

import com.Project.OMRReader.Models.RetrofitModels.QuizResponse;
import com.Project.OMRReader.Models.RetrofitModels.ResponseStatus;

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

    @GET("allQuizInfo/")
    Call<ArrayList<QuizResponse>> getAllQuiz();
}
