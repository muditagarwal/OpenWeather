package com.assignment.openweather.model.response;

import android.util.Log;

import com.assignment.openweather.model.pojo.DaysForecast;
import com.assignment.openweather.model.pojo.Forecast;
import com.assignment.openweather.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ForecastResponse {

    private List<DaysForecast> daysForecasts;
    private Forecast forecast;
    private int eventCode;
    private String cityIdentifier;

    public ForecastResponse(Forecast forecast, int eventCode, String cityIdentifier) {
        this.eventCode = eventCode;
        this.forecast = forecast;
        this.cityIdentifier = cityIdentifier;
    }

    public int getEventCode() {
        return eventCode;
    }

    public List<DaysForecast> getDaysForecasts() {
        if (daysForecasts == null) {
            daysForecasts = new ArrayList<DaysForecast>();
            Calendar calendar = Calendar.getInstance();
            String today = Utils.getDateVerbose(calendar.getTime());
            for (int i = 0; i < forecast.getList().length; i++) {
                DaysForecast daysForecast = forecast.getList()[i];
                if (daysForecast.getDateVerbose().startsWith(today)) {
                    Log.d("Mudit", daysForecast.toString());
                    daysForecasts.add(daysForecast);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    today = Utils.getDateVerbose(calendar.getTime());
                }
            }
        }
        return daysForecasts;
    }

    public String getCityIdentifier() {
        return cityIdentifier;
    }

    public String getCityName() {
        return forecast.getCity().getName() + ", " + forecast.getCity().getCountry();
    }
}