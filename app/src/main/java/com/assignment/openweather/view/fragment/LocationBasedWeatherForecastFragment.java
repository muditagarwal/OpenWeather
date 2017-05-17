package com.assignment.openweather.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;
import com.assignment.openweather.presenter.LocationFragmentPresenter;
import com.assignment.openweather.view.contracts.LocationFragmentScreen;

public class LocationBasedWeatherForecastFragment extends BaseFragment implements LocationFragmentScreen, LocationListener {

    private LocationManager locationManager;
    private LocationFragmentPresenter locationFragmentPresenter;

    public LocationBasedWeatherForecastFragment() {
        // Required empty public constructor
    }

    public static LocationBasedWeatherForecastFragment newInstance() {
        LocationBasedWeatherForecastFragment fragment = new LocationBasedWeatherForecastFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        locationFragmentPresenter = new LocationFragmentPresenter(this);
        getWeatherForecast();
        return view;
    }

    @SuppressWarnings("MissingPermission")
    private void getWeatherForecast() {
        Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if (location != null) {
            locationFragmentPresenter.getWeatherForecast(location.getLatitude(), location.getLongitude());
        } else {
            locationManager.requestSingleUpdate(LocationManager.PASSIVE_PROVIDER, this, null);
        }
    }

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void reRequestForecast() {
        getWeatherForecast();
    }

    @Override
    public void showWeatherForecast(ForecastResponse forecastResponse) {
        super.loadWeather(forecastResponse);
    }

    @Override
    public void failureWhileLoadingForecast(ErrorResponse errorResponse) {
        super.failureWhileLoadingForecast(errorResponse);
    }

    @Override
    public void showError(String message) {
        super.showError(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationFragmentPresenter.deregisterForEvents();
    }

    @Override
    public void onLocationChanged(Location location) {
        locationFragmentPresenter.getWeatherForecast(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
}
