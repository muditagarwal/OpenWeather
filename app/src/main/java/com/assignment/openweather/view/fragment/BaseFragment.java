package com.assignment.openweather.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.openweather.R;

import com.assignment.openweather.model.response.ErrorResponse;
import com.assignment.openweather.model.response.ForecastResponse;
import com.assignment.openweather.presenter.BasePresenter;
import com.assignment.openweather.view.adapters.ForecastListAdapter;
import com.assignment.openweather.view.contracts.BaseFragmentScreen;
import com.assignment.openweather.view.decorators.ItemOffsetDecoration;

public abstract class BaseFragment extends Fragment implements BaseFragmentScreen {

    private BasePresenter basePresenter;
    private ForecastListAdapter forecastListAdapter;

    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private RecyclerView forecastRecyclerView;
    private LinearLayout errorLinearLayout;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        basePresenter = new BasePresenter(this);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        errorLinearLayout = (LinearLayout) view.findViewById(R.id.error_layout);
        errorLinearLayout.findViewById(R.id.reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reRequestForecast();
            }
        });

        forecastRecyclerView = (RecyclerView) view.findViewById(R.id.forecast_list);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastRecyclerView.setItemAnimator(new DefaultItemAnimator());

        int gapDimen = getResources().getDimensionPixelOffset(R.dimen.gap_1);
        forecastRecyclerView.addItemDecoration(new ItemOffsetDecoration(gapDimen));

        forecastListAdapter = new ForecastListAdapter(getActivity(), null);
        forecastRecyclerView.setAdapter(forecastListAdapter);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);

        return view;
    }

    public void loadWeather(final ForecastResponse forecastResponse) {
        toolbar.setTitle(forecastResponse.getCityName());
        errorLinearLayout.setVisibility(View.GONE);
        forecastRecyclerView.setVisibility(View.VISIBLE);
        forecastListAdapter.setDaysForecasts(forecastResponse.getDaysForecasts());
    }

    public void failureWhileLoadingForecast(ErrorResponse errorResponse) {
        showError(errorResponse.getMessage());
    }

    @Override
    public void launchScreenForAddANewPlace() {

    }

    public void showError(String message) {
        errorLinearLayout.setVisibility(View.VISIBLE);
        forecastRecyclerView.setVisibility(View.GONE);
        ((TextView) errorLinearLayout.findViewById(R.id.error_text_view)).setText(message);
    }

    public void showProgress() {
        progressDialog.show();
    }

    public void hideProgress() {
        progressDialog.dismiss();
    }

    public abstract void reRequestForecast();
}