package com.assignment.openweather.presenter;

import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;
import com.assignment.openweather.view.contracts.LocationFragmentScreen;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;

import static com.assignment.openweather.utils.AppConstants.EVENT_LOCATION_BASED_REQ;
import static com.assignment.openweather.utils.AppConstants.KEY_CITY_IDENTIFIER;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by mudit-agarwal on 17-05-2017.
 */
public class LocationFragmentPresenterTest {

    private LocationFragmentPresenter locationFragmentPresenter;
    private LocationFragmentScreen locationFragmentScreen;

    @Before
    public void setUp() throws Exception {
        locationFragmentScreen = mock(LocationFragmentScreen.class);
        locationFragmentPresenter = new LocationFragmentPresenter(locationFragmentScreen);
    }

    @Test
    public void getWeatherForecast() throws Exception {
        locationFragmentPresenter.getWeatherForecast(12.33, 77.68);
        verify(locationFragmentScreen).showProgress();
    }

    @Test
    public void handleForecastSuccessResult() throws Exception {
        ForecastResponse forecastResponse = mock(ForecastResponse.class);
        EventBus.getDefault().post(forecastResponse);
        verify(locationFragmentScreen).showWeatherForecast(forecastResponse);
    }

    @Test
    public void handleForecastFailureResult() throws Exception {
        ErrorResponse errorResponse = mock(ErrorResponse.class);
        EventBus.getDefault().post(errorResponse);
        verify(locationFragmentScreen).failureWhileLoadingForecast(errorResponse);
    }

    @Test
    public void deregisterForEvents() throws Exception {
        locationFragmentPresenter.deregisterForEvents();
        ErrorResponse errorResponse = mock(ErrorResponse.class);
        EventBus.getDefault().post(errorResponse);
        verify(locationFragmentScreen, never()).failureWhileLoadingForecast(errorResponse);
    }

}