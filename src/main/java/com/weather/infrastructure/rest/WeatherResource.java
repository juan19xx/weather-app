package com.weather.infrastructure.rest;

import java.util.List;

import com.weather.application.ports.in.GetWeatherUseCase;
import com.weather.application.ports.in.SearchCityUseCase;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {
	
	@Inject
	GetWeatherUseCase weatherUseCase;
	
	@Inject
	SearchCityUseCase searchCityUseCase;
	
	@GET
	@Path("/{query}")
	public Weather getWeather(@PathParam("query") String query) {
		return weatherUseCase.getWeather(query);
	}
	
	@GET
	@Path("/geolocation/{query}/{country}")
	public GeoLocation searchCity(@PathParam("query") String query, @PathParam("country") String country) {
	    return searchCityUseCase.searchCity(query, country);
	}
	
	@GET
	@Path("/geolocation/{query}")
	public List<GeoLocation> searchCity(@PathParam("query") String query) {
	    return searchCityUseCase.searchCity(query);
	}
	
	
}
