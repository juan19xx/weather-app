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

import io.smallrye.mutiny.Uni;
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
	public Uni<Weather> getWeather(String query) {
		// TODO Auto-generated method stub
		return client.getWeather(query, apiKey)
	            .map(weatherMapper::toDomain);
	}

	@Override
	public Uni<List<GeoLocation>> searchCity(String query) {
		// TODO Auto-generated method stub
		return client.searchCity(query, 5, apiKey)
				.map(geoLocationMapper::toDomain);
	}

	@Override
	public Uni<GeoLocation> searchCity(String query, String country) {
		// TODO Auto-generated method stub
		Uni<List<GeoLocation>> cities = searchCity(query);
		return cities.map(locations -> locations.stream()
                .filter(g -> country.equals(g.getCountry()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró una ubicación de US")));
	}

}
