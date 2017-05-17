package com.assignment.openweather.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.assignment.openweather.R;
import com.assignment.openweather.model.pojo.Forecast;
import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStreamReader;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.assignment.openweather.utils.AppConstants.EVENT_LOCATION_BASED_REQ;
import static com.assignment.openweather.utils.AppConstants.KEY_CITY_IDENTIFIER;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by mudit-agarwal on 17-05-2017.
 */
@RunWith(AndroidJUnit4.class)
public class WeatherActivityTest {

    @Rule
    public ActivityTestRule<WeatherActivity> rule = new ActivityTestRule<>(WeatherActivity.class);

    @Test
    public void testShouldShowForecast() throws IOException {
        Gson gson = new Gson();
        Forecast forecast = gson.fromJson(new InputStreamReader(rule.getActivity().getAssets().open("sample_response.json")), Forecast.class);
        EventBus.getDefault().post(new ForecastResponse(forecast, EVENT_LOCATION_BASED_REQ, null));

        onView(withId(R.id.forecast_list)).check(matches(isDisplayed()));
        onView(withId(R.id.error_layout)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testShouldShowError() {
        EventBus.getDefault().post(new ErrorResponse("Error", EVENT_LOCATION_BASED_REQ, null));

        onView(withId(R.id.error_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.forecast_list)).check(matches(not(isDisplayed())));
    }
}