package com.assignment.openweather.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.assignment.openweather.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;
import okio.Buffer;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public class Utils {

    private Utils() {

    }

    public static int getDayOfWeekFromDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean hasInternetConnection(Context context) {
        return true;
    }

    public static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null) copy.writeTo(buffer);
            else return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public static boolean hasLocationPermission(Activity activity) {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    public static String getDateVerbose(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static boolean isNotEmptyArray(Object[] array) {
        return array != null && array.length > 0;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmptyList(List list) {
        return list != null && list.size() > 0;
    }
}
