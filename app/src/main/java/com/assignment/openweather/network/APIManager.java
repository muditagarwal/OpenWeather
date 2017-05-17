package com.assignment.openweather.network;

import android.content.Context;

import com.assignment.openweather.R;
import com.assignment.openweather.model.pojo.DaysForecast;
import com.assignment.openweather.model.pojo.Forecast;
import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;
import com.assignment.openweather.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.assignment.openweather.utils.AppConstants.KEY_CITY_IDENTIFIER;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public class APIManager {

    private static final String API_ENDPOINT = "http://api.openweathermap.org/data/2.5/";

    private static APIManager apiManager;
    private OpenWeatherAPI openWeatherOpenWeatherAPI;

    public static APIManager getApiManager() {
        if (apiManager == null) {
            apiManager = new APIManager();
        }
        return apiManager;
    }

    private APIManager() {
        HttpLoggingInterceptor bodyLevelLoggingInterceptor = new HttpLoggingInterceptor();
        bodyLevelLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(false)
                .addInterceptor(bodyLevelLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openWeatherOpenWeatherAPI = retrofit.create(OpenWeatherAPI.class);
    }

    public void getWeatherForecast(final Map<String, String> queryMap, final int eventCode) throws IOException {
        Call<Forecast> response = openWeatherOpenWeatherAPI.getWeatherForecast(queryMap);
        response.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                EventBus.getDefault().post(new ForecastResponse(response.body(), eventCode, queryMap.get(KEY_CITY_IDENTIFIER)));
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                EventBus.getDefault().post(new ErrorResponse(t.getMessage(), eventCode, queryMap.get(KEY_CITY_IDENTIFIER)));
            }
        });
    }
}
