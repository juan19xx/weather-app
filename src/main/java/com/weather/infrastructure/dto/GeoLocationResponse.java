package com.weather.infrastructure.dto;

import lombok.Data;

@Data
public class GeoLocationResponse {
	private String name;
    private double lat;
    private double lon;
    private String country;
    private String state;
}
