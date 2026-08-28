package com.weather.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoLocation {
	private String name;
    private double lat;
    private double lon;
    private String country;
    private String state;
}
