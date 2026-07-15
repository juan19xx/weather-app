package com.weather.infrastructure.client;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.weather.application.ports.out.WeatherApiPort;
import com.weather.domain.exceptions.NotFoundException;
import com.weather.domain.exceptions.WeatherProviderUnavailableException;
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
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class WeatherApiAdapter implements WeatherApiPort{
	private final WeatherRestClient client;
    private final WeatherMapper weatherMapper;
    private final GeoLocationMapper geoLocationMapper;
    private final String apiKey;
    private static final int MAX_NUMBER_OF_GEOLOCATION_API_RESULTS = 5;

    @Inject
    public WeatherApiAdapter(
            @RestClient WeatherRestClient client,
            WeatherMapper weatherMapper,
            GeoLocationMapper geoLocationMapper,
            @ConfigProperty(name = "openweather.api.key") String apiKey) {

        this.client = client;
        this.weatherMapper = weatherMapper;
        this.geoLocationMapper = geoLocationMapper;
        this.apiKey = apiKey;
    }
	
	@Override
	public Uni<Weather> getWeather(String query) {
		// TODO Auto-generated method stub
		return client.getWeather(query, apiKey)
				.onFailure(WebApplicationException.class)
		        .transform(t -> {
		        	WebApplicationException ex = (WebApplicationException) t;
		            if (ex.getResponse().getStatus() == 404) {
		                return new NotFoundException("This city is not found: "+ query);
		            } else if(ex.getResponse().getStatus() == 500) {
		            	return new WeatherProviderUnavailableException("Internal Server error: " + ex.getMessage());
		            }

		            return ex;
		        })
	            .map(weatherMapper::toDomain);
	}

	@Override
	public Uni<List<GeoLocation>> searchCity(String query) {
		// TODO Auto-generated method stub
		return client.searchCity(query, MAX_NUMBER_OF_GEOLOCATION_API_RESULTS, apiKey)
				.onFailure(WebApplicationException.class)
				.transform(t -> {
		        	WebApplicationException ex = (WebApplicationException) t;
		            if(ex.getResponse().getStatus() == 500) {
		            	return new WeatherProviderUnavailableException("Internal Server error: " + ex.getMessage());
		            }
		            return ex;
		        })
				.map(geoLocationMapper::toDomain);
	}

	@Override
	public Uni<GeoLocation> searchCityWithCountry(String query, String country) {
		// TODO Auto-generated method stub
		Uni<List<GeoLocation>> cities = searchCity(query);
		return cities.map(locations -> locations.stream()
                .filter(g -> country.equals(g.getCountry()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("City: " + query + " with country: " + country + " not found")));
	}

}
