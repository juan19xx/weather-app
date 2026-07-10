package com.weather.infrastructure.client;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.weather.application.ports.out.WeatherApiPort;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;
import com.weather.infrastructure.dto.GeoLocationResponse;
import com.weather.infrastructure.dto.WeatherResponse;
import com.weather.infrastructure.mapper.GeoLocationMapper;
import com.weather.infrastructure.mapper.WeatherMapper;
import com.weather.infrastructure.rest.WeatherRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WeatherApiAdapter implements WeatherApiPort{

	@Inject
	@RestClient
	WeatherRestClient client;
	
	@Inject
	WeatherMapper weatherMapper;
	
	@Inject
	GeoLocationMapper geoLocationMapper;
	
	@ConfigProperty(name = "openweather.api.key")
	String apiKey;
	
	@Override
	public Weather getWeather(String query) {
		// TODO Auto-generated method stub
		WeatherResponse response = client.getWeather(query, apiKey);
		return weatherMapper.toDomain(response);
	}

	@Override
	public List<GeoLocation> searchCity(String query) {
		// TODO Auto-generated method stub
		List<GeoLocationResponse> response =
	            client.searchCity(query, 5, apiKey);

	    return geoLocationMapper.toDomain(response);
	}

	@Override
	public GeoLocation searchCity(String query, String country) {
		// TODO Auto-generated method stub
		List<GeoLocation> cities = searchCity(query);
		return cities.stream()
		        .filter(g -> g.getCountry().equals(country))
		        .findFirst()
		        .orElse(null);
	}

}
