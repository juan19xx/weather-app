package com.weather.domain.exceptions;

public class WeatherProviderUnavailableException extends RuntimeException {
	public WeatherProviderUnavailableException(String message) {
		super(message);
	}
}
