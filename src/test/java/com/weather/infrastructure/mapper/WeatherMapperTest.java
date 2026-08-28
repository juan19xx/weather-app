package com.weather.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.weather.domain.model.Weather;

public class WeatherMapperTest {
	private final WeatherMapper mapper =
            Mappers.getMapper(WeatherMapper.class);
	@Test
	void shouldReturnNullWhenWeatherResponseIsNull() {

	    Weather result = mapper.toDomain(null);

	    assertNull(result);
	}
}
