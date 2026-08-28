package com.weather.application.ports.in;

import java.util.List;

import com.weather.domain.model.GeoLocation;

import io.smallrye.mutiny.Uni;

@FunctionalInterface
public interface SearchCityUseCase {
	Uni<List<GeoLocation>> searchCity(String query);
}
