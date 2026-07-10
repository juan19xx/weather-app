package com.weather.application.ports.in;

import com.weather.domain.model.Weather;

public interface GetWeatherUseCase {
	Weather getWeather(String query);
}
