package com.weather.application.ports.in;

import java.util.List;

import com.weather.domain.model.GeoLocation;

import io.smallrye.mutiny.Uni;

public interface SearchCityUseCase {
	Uni<List<GeoLocation>> searchCity(String query);
	Uni<GeoLocation> searchCity(String query, String country);
}
