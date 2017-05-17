package com.assignment.openweather.model.pojo;

public class Coordinate {

    private String lon;
    private String lat;

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "ClassPojo [lon = " + lon + ", lat = " + lat + "]";
    }
}
