package com.weather.infrastructure.mapper;

import java.util.List;

import com.weather.domain.model.GeoLocation;
import com.weather.infrastructure.dto.GeoLocationResponse;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeoLocationMapper {
	public GeoLocation toDomain(GeoLocationResponse response) {
		return GeoLocation.builder()
				.country(response.getCountry())
				.lat(response.getLat())
				.lon(response.getLon())
				.name(response.getName())
				.state(response.getState())
				.build();
    }
	
	public List<GeoLocation> toDomain(List<GeoLocationResponse> responses) {
        return responses.stream()
                .map(this::toDomain)
                .toList();
    }
}
