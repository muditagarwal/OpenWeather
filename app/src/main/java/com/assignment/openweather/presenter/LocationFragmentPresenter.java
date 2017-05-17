package com.assignment.openweather.presenter;

import com.assignment.openweather.model.GetForecastModelService;
import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;
import com.assignment.openweather.view.contracts.LocationFragmentScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public class LocationFragmentPresenter {

    private LocationFragmentScreen locationFragmentScreen;

    public LocationFragmentPresenter(LocationFragmentScreen locationFragmentScreen) {
        this.locationFragmentScreen = locationFragmentScreen;
        EventBus.getDefault().register(this);
    }

    public void getWeatherForecast(double latitude, double longitude) {
        locationFragmentScreen.showProgress();
        new GetForecastModelService().getForecastBasedOnLocation(latitude, longitude);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleForecastSuccessResult(ForecastResponse forecastResponse) {
        locationFragmentScreen.hideProgress();
        locationFragmentScreen.showWeatherForecast(forecastResponse);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleForecastFailureResult(ErrorResponse errorResponse) {
        locationFragmentScreen.hideProgress();
        locationFragmentScreen.failureWhileLoadingForecast(errorResponse);
    }

    public void deregisterForEvents() {
        EventBus.getDefault().unregister(this);
    }
}

