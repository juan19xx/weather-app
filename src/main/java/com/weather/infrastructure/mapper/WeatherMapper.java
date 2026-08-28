package com.weather.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.weather.domain.model.Weather;
import com.weather.infrastructure.dto.WeatherResponse;

@Mapper(componentModel = "cdi")
public interface WeatherMapper {

    Weather toDomain(WeatherResponse response);

}
