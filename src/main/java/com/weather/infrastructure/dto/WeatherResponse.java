package com.weather.infrastructure.dto;

import lombok.Data;

@Data
public class WeatherResponse {
	private String base;
	private int visibility;
	private long dt;
	private int timezone;
	private long id;
	private String name;
	private int cod;
}
