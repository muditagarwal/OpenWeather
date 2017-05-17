package com.assignment.openweather.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.openweather.R;
import com.assignment.openweather.model.pojo.DaysForecast;
import com.assignment.openweather.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {

    private static final String WEATHER_STATUS_URL = "http://openweathermap.org/img/w/%s.png";

    private List<DaysForecast> daysForecasts;
    private String[] days;
    private Picasso picasso;

    public ForecastListAdapter(Context context, List<DaysForecast> daysForecasts) {
        this.daysForecasts = daysForecasts;
        days = context.getResources().getStringArray(R.array.days);
        picasso = Picasso.with(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_day_forecast, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DaysForecast forecast = daysForecasts.get(position);

        //doing a minus one because DAY_OF_WEEK starts with SUNDAY(1)
        holder.dayNameTextView.setText(days[Utils.getDayOfWeekFromDate(forecast.getDaysTimeInMs()) - 1]);
        holder.minTempTextView.setText(String.valueOf((int) Math.floor(forecast.getMain().getTempMin())) + "\u00B0");
        holder.maxTempTextView.setText(String.valueOf((int) Math.floor(forecast.getMain().getTempMax())) + "\u00B0");
        picasso.load(String.format(WEATHER_STATUS_URL, forecast.getWeatherStatusIcon())).into(holder.cloudStatusImageView);
    }

    @Override
    public int getItemCount() {
        return daysForecasts != null ? daysForecasts.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayNameTextView, minTempTextView, maxTempTextView;
        private ImageView cloudStatusImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            dayNameTextView = (TextView) itemView.findViewById(R.id.day_name);
            minTempTextView = (TextView) itemView.findViewById(R.id.min_temp);
            maxTempTextView = (TextView) itemView.findViewById(R.id.max_temp);

            cloudStatusImageView = (ImageView) itemView.findViewById(R.id.weather_status);
        }
    }

    public void setDaysForecasts(List<DaysForecast> daysForecasts) {
        this.daysForecasts = daysForecasts;
        notifyDataSetChanged();
    }
}
