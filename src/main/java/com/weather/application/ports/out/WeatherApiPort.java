package com.weather.application.ports.out;

import java.util.List;

import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

public interface WeatherApiPort {
	Weather getWeather(String query);
	List<GeoLocation> searchCity(String query);
}
