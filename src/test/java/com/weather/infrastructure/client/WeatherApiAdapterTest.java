package com.weather.infrastructure.client;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weather.domain.exceptions.NotFoundException;
import com.weather.domain.exceptions.WeatherProviderUnavailableException;
import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;
import com.weather.infrastructure.dto.GeoLocationResponse;
import com.weather.infrastructure.dto.WeatherResponse;
import com.weather.infrastructure.mapper.GeoLocationMapper;
import com.weather.infrastructure.mapper.WeatherMapper;
import com.weather.infrastructure.rest.WeatherRestClient;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ExtendWith(MockitoExtension.class)
class WeatherApiAdapterTest {

    @Mock
    @RestClient
    WeatherRestClient client;

    @Mock
    WeatherMapper weatherMapper;

    @Mock
    GeoLocationMapper geoLocationMapper;

    private WeatherApiAdapter adapter;

    @BeforeEach
    void setup() {
        adapter = new WeatherApiAdapter(
                client,
                weatherMapper,
                geoLocationMapper,
                "api-key");
    }

    @Test
    void shouldReturnWeather() {

        WeatherResponse response = mock(WeatherResponse.class);
        Weather weather = mock(Weather.class);

        when(client.getWeather("Monterrey", "api-key"))
                .thenReturn(Uni.createFrom().item(response));

        when(weatherMapper.toDomain(response))
                .thenReturn(weather);

        UniAssertSubscriber<Weather> subscriber = adapter.getWeather("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitItem()
                .assertCompleted()
                .assertItem(weather);

        verify(weatherMapper).toDomain(response);
    }

    @Test
    void shouldThrowNotFoundWhenWeather404() {

        Response response = Response.status(404).build();

        when(client.getWeather(anyString(), anyString()))
                .thenReturn(Uni.createFrom()
                        .failure(new WebApplicationException(response)));

        UniAssertSubscriber<Weather> subscriber = adapter.getWeather("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitFailure()
                .assertFailedWith(NotFoundException.class);
    }

    @Test
    void shouldThrowServerErrorWhenWeather500() {

        Response response = Response.status(500).build();

        when(client.getWeather(anyString(), anyString()))
                .thenReturn(Uni.createFrom()
                        .failure(new WebApplicationException(response)));

        UniAssertSubscriber<Weather> subscriber = adapter.getWeather("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitFailure()
                .assertFailedWith(WeatherProviderUnavailableException.class);
    }

    @Test
    void shouldReturnCities() {

        List<GeoLocationResponse> responses = List.of(mock(GeoLocationResponse.class));
        List<GeoLocation> cities = List.of(mock(GeoLocation.class));

        when(client.searchCity(anyString(), anyInt(), anyString()))
                .thenReturn(Uni.createFrom().item(responses));

        when(geoLocationMapper.toDomain(responses))
                .thenReturn(cities);

        UniAssertSubscriber<List<GeoLocation>> subscriber = adapter.searchCity("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitItem()
                .assertCompleted()
                .assertItem(cities);

        verify(geoLocationMapper).toDomain(responses);
    }

    @Test
    void shouldThrowServerErrorWhenSearchCity500() {

        Response response = Response.status(500).build();

        when(client.searchCity(anyString(), anyInt(), anyString()))
                .thenReturn(Uni.createFrom()
                        .failure(new WebApplicationException(response)));

        UniAssertSubscriber<List<GeoLocation>> subscriber = adapter.searchCity("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitFailure()
                .assertFailedWith(WeatherProviderUnavailableException.class);
    }

    @Test
    void shouldReturnCityByCountry() {

        GeoLocation mexico = GeoLocation.builder().country("MX").build();
        GeoLocation usa = GeoLocation.builder().country("US").build();

        when(client.searchCity(anyString(), anyInt(), anyString()))
                .thenReturn(Uni.createFrom().item(List.of()));

        when(geoLocationMapper.toDomain(anyList()))
                .thenReturn(List.of(usa, mexico));

        UniAssertSubscriber<GeoLocation> subscriber = adapter
                .searchCityWithCountry("Monterrey", "MX")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitItem()
                .assertCompleted()
                .assertItem(mexico);
    }

    @Test
    void shouldPropagateWebApplicationExceptionWhenStatusIs401() {

        Response response = Response.status(401).build();

        when(client.getWeather(anyString(), anyString()))
                .thenReturn(Uni.createFrom()
                        .failure(new WebApplicationException(response)));

        UniAssertSubscriber<Weather> subscriber = adapter.getWeather("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitFailure()
                .assertFailedWith(WebApplicationException.class);
    }

    @Test
    void shouldPropagateWebApplicationExceptionWhenSearchCityReturns401() {

        Response response = Response.status(401).build();

        when(client.searchCity(anyString(), anyInt(), anyString()))
                .thenReturn(Uni.createFrom()
                        .failure(new WebApplicationException(response)));

        UniAssertSubscriber<List<GeoLocation>> subscriber = adapter.searchCity("Monterrey")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitFailure()
                .assertFailedWith(WebApplicationException.class);
    }

    @Test
    void shouldThrowNotFoundWhenCountryDoesNotExist() {

        GeoLocation usa = GeoLocation.builder().country("US").build();

        when(client.searchCity(anyString(), anyInt(), anyString()))
                .thenReturn(Uni.createFrom().item(List.of()));

        when(geoLocationMapper.toDomain(anyList()))
                .thenReturn(List.of(usa));

        UniAssertSubscriber<GeoLocation> subscriber = adapter
                .searchCityWithCountry("Monterrey", "MX")
                .subscribe()
                .withSubscriber(UniAssertSubscriber.create());

        subscriber
                .awaitFailure()
                .assertFailedWith(NotFoundException.class);
    }
}