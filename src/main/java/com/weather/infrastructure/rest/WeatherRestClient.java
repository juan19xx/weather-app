package com.weather.infrastructure.rest;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.weather.infrastructure.dto.GeoLocationResponse;
import com.weather.infrastructure.dto.WeatherResponse;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient(configKey = "openweather")
public interface WeatherRestClient {
	@GET
	@Path("/data/2.5/weather")
    Uni<WeatherResponse> getWeather(
            @QueryParam("q") String query,
            @QueryParam("appid") String apiKey);
	
	@GET
	@Path("/geo/1.0/direct")
	Uni<List<GeoLocationResponse>> searchCity(
	        @QueryParam("q") String query,
	        @QueryParam("limit") Integer limit,
	        @QueryParam("appid") String apiKey);
}
