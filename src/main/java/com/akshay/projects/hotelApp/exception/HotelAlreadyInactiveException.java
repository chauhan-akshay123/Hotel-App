package com.akshay.projects.hotelApp.exception;

public class HotelAlreadyInactiveException extends RuntimeException {
    public HotelAlreadyInactiveException(String message) {
        super(message);
    }
}
