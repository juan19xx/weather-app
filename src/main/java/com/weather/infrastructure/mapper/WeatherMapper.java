package com.weather.infrastructure.mapper;

import com.weather.domain.model.Weather;
import com.weather.infrastructure.dto.WeatherResponse;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherMapper {
	
	public Weather toDomain(WeatherResponse response) {
		return Weather.builder()
				.base(response.getBase())
				.cod(response.getCod())
				.dt(response.getDt())
				.id(response.getId())
				.name(response.getName())
				.timezone(response.getTimezone())
				.visibility(response.getVisibility())
				.build();
	}
}
