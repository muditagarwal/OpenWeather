package com.assignment.openweather.view.contracts;

import com.assignment.openweather.model.pojo.Forecast;
import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;

import java.util.List;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public interface LocationFragmentScreen {

    void showProgress();

    void hideProgress();

    void showWeatherForecast(ForecastResponse forecastResponse);

    void failureWhileLoadingForecast(ErrorResponse errorResponse);

}
