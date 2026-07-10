package com.weather.domain.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Weather {
	private String base;
	private int visibility;
	private long dt;
	private int timezone;
	private long id;
	private String name;
	private int cod;
}
