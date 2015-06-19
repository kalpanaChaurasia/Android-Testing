package com.rajeshbatth.android_testing.api;

import com.rajeshbatth.android_testing.model.HomeDataModel;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by rajesh.j on 6/19/2015.
 */
public interface HomeApi {
    @GET("/api/json/get/LWUSdaa")
    void getHomeDataAsync(Callback<HomeDataModel> callback);

    @GET("/api/json/get/LWUSdaa")
    HomeDataModel getHomeData();
}
