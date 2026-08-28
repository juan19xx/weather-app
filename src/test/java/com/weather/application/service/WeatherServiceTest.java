package com.weather.application.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weather.application.ports.out.WeatherApiPort;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import io.smallrye.mutiny.Uni;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

	@Mock
    private WeatherApiPort api;

    @InjectMocks
    private WeatherService service;

    @Test
    void shouldGetWeather() {

        Weather weather = Weather.builder()
                .name("Monterrey")
                .build();

        Uni<Weather> expected = Uni.createFrom().item(weather);

        when(api.getWeather("Monterrey")).thenReturn(expected);

        Uni<Weather> result = service.getWeather("Monterrey");

        assertSame(expected, result);

        verify(api).getWeather("Monterrey");
    }

    @Test
    void shouldSearchCity() {

        List<GeoLocation> cities = List.of(
                GeoLocation.builder().name("Monterrey").build());

        Uni<List<GeoLocation>> expected =
                Uni.createFrom().item(cities);

        when(api.searchCity("Monterrey"))
                .thenReturn(expected);

        Uni<List<GeoLocation>> result =
                service.searchCity("Monterrey");

        assertSame(expected, result);

        verify(api).searchCity("Monterrey");
    }

    @Test
    void shouldSearchCityByCountry() {

        GeoLocation city = GeoLocation.builder()
                .name("Monterrey")
                .country("MX")
                .build();

        Uni<GeoLocation> expected =
                Uni.createFrom().item(city);

        when(api.searchCityWithCountry("Monterrey", "MX"))
                .thenReturn(expected);

        Uni<GeoLocation> result =
                service.searchCityWithCountry("Monterrey", "MX");

        assertSame(expected, result);

        verify(api).searchCityWithCountry("Monterrey", "MX");
    }

}