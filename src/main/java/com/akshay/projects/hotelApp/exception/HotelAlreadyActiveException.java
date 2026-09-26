package com.akshay.projects.hotelApp.exception;

public class HotelAlreadyActiveException extends RuntimeException {
    public HotelAlreadyActiveException(String message) {
        super(message);
    }
}
