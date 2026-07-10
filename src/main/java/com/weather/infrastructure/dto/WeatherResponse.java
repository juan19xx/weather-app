package com.weather.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Builder
public class WeatherResponse {
	private String base;
	private int visibility;
	private long dt;
	private int timezone;
	private long id;
	private String name;
	private int cod;
}
