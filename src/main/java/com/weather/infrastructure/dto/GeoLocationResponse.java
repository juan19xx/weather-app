package com.weather.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class GeoLocationResponse {
	private String name;
    private double lat;
    private double lon;
    private String country;
    private String state;
}
