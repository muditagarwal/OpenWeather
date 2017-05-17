package com.assignment.openweather.model.response;

/**
 * Created by mudit-agarwal on 15-05-2017.
 */

public class ErrorResponse {

    String message;
    private int eventCode;
    private String cityIdentifier;

    public ErrorResponse(String message, int eventCode, String cityIdentifier) {
        this.message = message;
        this.eventCode = eventCode;
        this.cityIdentifier = cityIdentifier;
    }

    public String getMessage() {
        return message;
    }

    public int getEventCode() {
        return eventCode;
    }

    public String getCityIdentifier() {
        return cityIdentifier;
    }
}
