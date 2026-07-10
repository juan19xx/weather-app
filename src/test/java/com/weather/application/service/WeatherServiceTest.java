package com.weather.application.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.weather.application.ports.out.WeatherApiPort;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import io.smallrye.mutiny.Uni;

class WeatherServiceTest {

    private WeatherApiPort api;
    private WeatherService service;

    @BeforeEach
    void setUp() {
        api = Mockito.mock(WeatherApiPort.class);
        service = new WeatherService(api);
    }

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

        when(api.searchCity("Monterrey", "MX"))
                .thenReturn(expected);

        Uni<GeoLocation> result =
                service.searchCity("Monterrey", "MX");

        assertSame(expected, result);

        verify(api).searchCity("Monterrey", "MX");
    }

}