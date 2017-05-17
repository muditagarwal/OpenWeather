package com.assignment.openweather.presenter;

import com.assignment.openweather.model.pojo.DaysForecast;
import com.assignment.openweather.model.response.ForecastResponse;
import com.assignment.openweather.view.contracts.BaseFragmentScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public class BasePresenter {

    private BaseFragmentScreen baseFragmentScreen;

    public BasePresenter(BaseFragmentScreen baseFragmentScreen) {
        this.baseFragmentScreen = baseFragmentScreen;

    }

    public void launchScreenForAddANewPlace() {
        baseFragmentScreen.launchScreenForAddANewPlace();
    }

}
