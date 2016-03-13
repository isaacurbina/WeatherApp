package com.mobileappsco.training.weatherapp.retrofit;

import com.mobileappsco.training.weatherapp.entities.APIObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("weather?appid=b1b15e88fa797225412429c1c50c122a")
    Call<APIObj> getCurrentWeather(@Query("q") String q);
}
