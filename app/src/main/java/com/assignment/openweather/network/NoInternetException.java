package com.assignment.openweather.network;

import java.io.IOException;

/**
 * Created by muditagarwal on 13/07/16.
 */
public class NoInternetException extends IOException {

    /**
     * Constructs a new {@code NoInternetException} with its stack trace filled in.
     */
    public NoInternetException() {
    }

    /**
     * Constructs a new {@code NoInternetException} with its stack trace and detail
     * message filled in.
     *
     * @param detailMessage the detail message for this exception.
     */
    public NoInternetException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new instance of this class with detail message and cause
     * filled in.
     *
     * @param message The detail message for the exception.
     * @param cause   The detail cause for the exception.
     * @since 1.6
     */
    public NoInternetException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new instance of this class with its detail cause filled in.
     *
     * @param cause The detail cause for the exception.
     * @since 1.6
     */
    public NoInternetException(Throwable cause) {
        super(cause == null ? null : cause.toString(), cause);
    }
}
