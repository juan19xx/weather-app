package com.weather.application.service;

import java.util.List;

import com.weather.application.ports.in.GetWeatherUseCase;
import com.weather.application.ports.in.SearchCityUseCase;
import com.weather.application.ports.out.WeatherApiPort;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WeatherService implements GetWeatherUseCase, SearchCityUseCase{

	private final WeatherApiPort api;
	
	@Inject
	public WeatherService(WeatherApiPort api) {
		this.api = api;
	}


	@Override
	public Weather getWeather(String query) {
		// TODO Auto-generated method stub
		return api.getWeather(query);
	}


	@Override
	public List<GeoLocation> searchCity(String query) {
		// TODO Auto-generated method stub
		return api.searchCity(query);
	}


	@Override
	public GeoLocation searchCity(String query, String country) {
		// TODO Auto-generated method stub
		return api.searchCity(query, country);
	}

}
