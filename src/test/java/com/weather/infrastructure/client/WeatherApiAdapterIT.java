package com.weather.infrastructure.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.weather.domain.model.GeoLocation;
import com.weather.domain.model.Weather;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import jakarta.inject.Inject;

@QuarkusTest
class WeatherApiAdapterIT {
	@Inject
	WeatherApiAdapter weatherApiAdapter;
	
	@Test
	void shouldGetWeatherFromOpenWeatherApi() {
	
		UniAssertSubscriber<Weather> subscriber =
	            weatherApiAdapter.getWeather("Monterrey")
	                    .subscribe()
	                    .withSubscriber(UniAssertSubscriber.create());

	    subscriber.awaitItem();

	    Weather weather = subscriber.getItem();

	    assertNotNull(weather);
	    assertEquals("Monterrey", weather.getName());
	}
	
	
	@Test
	void shouldSearchCities() {

	    UniAssertSubscriber<List<GeoLocation>> subscriber =
	            weatherApiAdapter.searchCity("Monterrey")
	                    .subscribe()
	                    .withSubscriber(UniAssertSubscriber.create());

	    subscriber.awaitItem().assertCompleted();

	    List<GeoLocation> cities = subscriber.getItem();

	    assertNotNull(cities);
	    assertFalse(cities.isEmpty());
	    assertEquals("MX", cities.getFirst().getCountry());
	}
	
	
	@Test
	void shouldSearchCityByCountry() {

	    UniAssertSubscriber<GeoLocation> subscriber =
	            weatherApiAdapter.searchCityWithCountry("Monterrey", "MX")
	                    .subscribe()
	                    .withSubscriber(UniAssertSubscriber.create());

	    subscriber.awaitItem().assertCompleted();

	    GeoLocation location = subscriber.getItem();

	    assertNotNull(location);
	    assertEquals("MX", location.getCountry());
	}

}
