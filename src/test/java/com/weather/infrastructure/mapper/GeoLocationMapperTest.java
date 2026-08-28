package com.weather.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.weather.domain.model.GeoLocation;
import com.weather.infrastructure.dto.GeoLocationResponse;

public class GeoLocationMapperTest {
	private final GeoLocationMapper mapper =
            Mappers.getMapper(GeoLocationMapper.class);
	@Test
	void shouldReturnNullWhenGeoLocationResponseIsNull() {

	    GeoLocation result = mapper.toDomain((GeoLocationResponse) null);

	    assertNull(result);
	}
	
	@Test
	void shouldReturnNullWhenGeoLocationListIsNull() {

	    List<GeoLocation> result = mapper.toDomain((List<GeoLocationResponse>)null);

	    assertNull(result);
	}
}
