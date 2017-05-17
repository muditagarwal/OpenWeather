package com.assignment.openweather.network;

import com.assignment.openweather.model.pojo.Forecast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

interface OpenWeatherAPI {

    @GET("forecast")
    Call<Forecast> getWeatherForecast(@QueryMap Map<String, String> options);

}