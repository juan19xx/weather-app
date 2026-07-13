package com.weather.domain.exceptions;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String city, String country) {
        super("City not found, city = " + city + " country = " + country);
    }
}
