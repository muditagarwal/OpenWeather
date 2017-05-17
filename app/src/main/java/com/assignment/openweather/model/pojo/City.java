package com.assignment.openweather.model.pojo;

public class City {

    private Coordinate coordinate;
    private String id;
    private String name;
    private String country;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "ClassPojo [coordinate = " + coordinate + ", id = " + id + ", name = " + name + ", country = " + country + "]";
    }
}