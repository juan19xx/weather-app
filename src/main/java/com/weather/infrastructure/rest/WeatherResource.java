package com.weather.infrastructure.rest;

import java.util.List;

import com.weather.application.ports.in.GetWeatherUseCase;
import com.weather.application.ports.in.SearchCityUseCase;
import com.weather.application.ports.in.SearchCityWithCountryUseCase;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/weather")
@AllArgsConstructor
public class WeatherResource {
	private final GetWeatherUseCase weatherUseCase;
	private final SearchCityUseCase searchCityUseCase;
	private final SearchCityWithCountryUseCase searchCityWithCountryUseCase;
	
	@GET
	@Path("/{query}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Weather> getWeather(@PathParam("query") String query) {
		return weatherUseCase.getWeather(query);
	}
	
	@GET
	@Path("/geolocation/{query}/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<GeoLocation> searchCityWithCountry(@PathParam("query") String query, @PathParam("country") String country) {
	    return searchCityWithCountryUseCase.searchCityWithCountry(query, country);
	}
	
	@GET
	@Path("/geolocation/{query}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<List<GeoLocation>> searchCity(@PathParam("query") String query) {
	    return searchCityUseCase.searchCity(query);
	}
	
}
