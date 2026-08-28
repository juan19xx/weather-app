package com.weather.application.ports.out;

import java.util.List;

import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import io.smallrye.mutiny.Uni;

public interface WeatherApiPort {
	Uni<Weather> getWeather(String query);
	Uni<List<GeoLocation>> searchCity(String query);
	Uni<GeoLocation> searchCityWithCountry(String query, String country);
}
