package com.assignment.openweather.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.assignment.openweather.view.fragment.LocationBasedWeatherForecastFragment;

/**
 * Created by mudit-agarwal on 16-05-2017.
 */

public class PlacesPagerAdapter extends FragmentPagerAdapter {

    public PlacesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return LocationBasedWeatherForecastFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
