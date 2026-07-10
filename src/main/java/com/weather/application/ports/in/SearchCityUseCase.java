package com.weather.application.ports.in;

import java.util.List;

import com.weather.domain.model.GeoLocation;

public interface SearchCityUseCase {
	List<GeoLocation> searchCity(String query);
	GeoLocation searchCity(String query, String country);
}
