package com.weather.infrastructure.rest;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.weather.infrastructure.dto.GeoLocationResponse;
import com.weather.infrastructure.dto.WeatherResponse;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "openweather")
public interface WeatherRestClient {
	@GET
	@Path("/data/2.5/weather")
	@Produces(MediaType.APPLICATION_JSON)
    Uni<WeatherResponse> getWeather(
            @QueryParam("q") String query,
            @QueryParam("appid") String apiKey);
	
	@GET
	@Path("/geo/1.0/direct")
	@Produces(MediaType.APPLICATION_JSON)
	Uni<List<GeoLocationResponse>> searchCity(
	        @QueryParam("q") String query,
	        @QueryParam("limit") Integer limit,
	        @QueryParam("appid") String apiKey);
}
