package com.weather.infrastructure.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.weather.domain.model.GeoLocation;
import com.weather.infrastructure.dto.GeoLocationResponse;

@Mapper(componentModel = "cdi")
public interface GeoLocationMapper {

    GeoLocation toDomain(GeoLocationResponse response);

    List<GeoLocation> toDomain(List<GeoLocationResponse> responses);

}