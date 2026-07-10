package com.weather.application.ports.in;

import com.weather.domain.model.GeoLocation;

import io.smallrye.mutiny.Uni;

@FunctionalInterface
public interface SearchCityWithCountryUseCase {
	Uni<GeoLocation> searchCityWithCountry(String query, String country);
}
