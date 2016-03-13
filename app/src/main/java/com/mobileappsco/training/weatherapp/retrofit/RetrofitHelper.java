package com.mobileappsco.training.weatherapp.retrofit;

import android.util.Log;

import com.mobileappsco.training.weatherapp.entities.APIObj;
import com.mobileappsco.training.weatherapp.entities.Clouds;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static void main(String[] args) {
        Retrofit rtBuilder = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface rfInterface = rtBuilder.create(RetrofitInterface.class);
        Call<APIObj> requestCall = rfInterface.getCurrentWeather("Wellington,NZ");
        APIObj apiObj = null;
        try {
            apiObj = requestCall.execute().body();
            System.out.println(apiObj.getCoord().getLat().toString());
            System.out.println(apiObj.getCoord().getLon().toString());
            System.out.println(apiObj.getWeather().get(0).getMain());
            System.out.println(apiObj.getWeather().get(0).getDescription());
            System.out.println(apiObj.getWeather().get(0).getIcon());
            System.out.println(apiObj.getBase());
            System.out.println(apiObj.getMain().getTemp().toString());
            System.out.println(apiObj.getMain().getPressure().toString());
            System.out.println(apiObj.getMain().getHumidity().toString());
            System.out.println(apiObj.getMain().getTempMin().toString());
            System.out.println(apiObj.getMain().getTempMax().toString());
            System.out.println(apiObj.getMain().getSeaLevel().toString());
            System.out.println(apiObj.getMain().getGrndLevel().toString());
            System.out.println(apiObj.getWind().getSpeed().toString());
            System.out.println(apiObj.getWind().getDeg().toString());
            System.out.println(apiObj.getClouds().getAll().toString());
            System.out.println(apiObj.getDt().toString());
            System.out.println(apiObj.getSys().getMessage().toString());
            System.out.println(apiObj.getSys().getCountry());
            System.out.println(apiObj.getSys().getSunrise().toString());
            System.out.println(apiObj.getSys().getSunset().toString());
            System.out.println(apiObj.getId().toString());
            System.out.println(apiObj.getName());
            System.out.println(apiObj.getCod().toString());
        } catch (Exception e) {
            Log.e("WeatherApp", "Error on execute: "+e.getMessage());
        }
    }
}
