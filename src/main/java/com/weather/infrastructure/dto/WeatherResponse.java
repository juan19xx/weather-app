package com.weather.infrastructure.dto;

public record WeatherResponse(
	String base,
	int visibility,
	long dt,
	int timezone,
	long id,
	String name,
	int cod
) {
	
}
