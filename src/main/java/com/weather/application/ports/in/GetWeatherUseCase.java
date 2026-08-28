package com.weather.application.ports.in;

import com.weather.domain.model.Weather;

import io.smallrye.mutiny.Uni;

@FunctionalInterface
public interface GetWeatherUseCase {
	Uni<Weather> getWeather(String query);
}
