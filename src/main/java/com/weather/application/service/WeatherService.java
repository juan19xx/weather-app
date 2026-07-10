package com.weather.application.service;

import java.util.List;

import com.weather.application.ports.in.GetWeatherUseCase;
import com.weather.application.ports.in.SearchCityUseCase;
import com.weather.application.ports.in.SearchCityWithCountryUseCase;
import com.weather.application.ports.out.WeatherApiPort;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WeatherService implements GetWeatherUseCase, SearchCityUseCase, SearchCityWithCountryUseCase{

	private final WeatherApiPort api;
	
	@Inject
	public WeatherService(WeatherApiPort api) {
		this.api = api;
	}


	@Override
	public Uni<Weather> getWeather(String query) {
		// TODO Auto-generated method stub
		return api.getWeather(query);
	}


	@Override
	public Uni<List<GeoLocation>> searchCity(String query) {
		// TODO Auto-generated method stub
		return api.searchCity(query);
	}


	@Override
	public Uni<GeoLocation> searchCityWithCountry(String query, String country) {
		// TODO Auto-generated method stub
		return api.searchCityWithCountry(query, country);
	}

}
