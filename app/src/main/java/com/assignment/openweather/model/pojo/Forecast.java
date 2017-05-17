package com.assignment.openweather.model.pojo;

public class Forecast {

    private String message;
    private String cnt;
    private String cod;
    private DaysForecast[] list;
    private City city;

    public String getMessage() {
        return message;
    }

    public String getCnt() {
        return cnt;
    }

    public String getCod() {
        return cod;
    }

    public DaysForecast[] getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", cnt = " + cnt + ", cod = " + cod + ", list = " + list + ", city = " + city + "]";
    }


}

