package com.weather.infrastructure.dto;

public record GeoLocationResponse(
	    String name,
	    double lat,
	    double lon,
	    String country,
	    String state
	) {
	}
