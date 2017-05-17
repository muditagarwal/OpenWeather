package com.assignment.openweather.model;

import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.network.APIManager;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.assignment.openweather.utils.AppConstants.APP_ID;
import static com.assignment.openweather.utils.AppConstants.EVENT_LOCATION_BASED_REQ;
import static com.assignment.openweather.utils.AppConstants.PARAM_APP_ID;
import static com.assignment.openweather.utils.AppConstants.PARAM_LAT;
import static com.assignment.openweather.utils.AppConstants.PARAM_LON;
import static com.assignment.openweather.utils.AppConstants.PARAM_UNITS;
import static com.assignment.openweather.utils.AppConstants.UNIT_METRIC;

/**
 * Created by mudit-agarwal on 16-05-2017.
 */

public class GetForecastModelService {

    public void getForecastBasedOnLocation(double lat, double lon) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(PARAM_LAT, String.valueOf(lat));
        queryMap.put(PARAM_LON, String.valueOf(lon));
        queryMap.put(PARAM_APP_ID, APP_ID);
        queryMap.put(PARAM_UNITS, UNIT_METRIC);

        try {
            APIManager.getApiManager().getWeatherForecast(queryMap, EVENT_LOCATION_BASED_REQ);
        } catch (IOException e) {
            EventBus.getDefault().post(new ErrorResponse(e.getMessage(), EVENT_LOCATION_BASED_REQ, null));
        }
    }

}
