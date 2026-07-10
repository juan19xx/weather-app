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
import jakarta.inject.Inject;

@QuarkusTest
class WeatherApiAdapterIT {
	@Inject
	WeatherApiAdapter weatherApiAdapter;
	
	@Test
	void shouldGetWeatherFromOpenWeatherApi() {
	
	    Uni<Weather> result = weatherApiAdapter.getWeather("Monterrey");
	
	    Weather weather = result.await().indefinitely();
	
	    assertNotNull(weather);
	    assertEquals("Monterrey", weather.getName());
	}
	
	
	@Test
	void shouldSearchCities() {
	
	    Uni<List<GeoLocation>> result =
	            weatherApiAdapter.searchCity("Monterrey");
	
	    List<GeoLocation> cities =
	            result.await().indefinitely();
	
	    assertNotNull(cities);
	    assertFalse(cities.isEmpty());
	
	    assertEquals("MX", cities.get(0).getCountry());
	}
	
	
	@Test
	void shouldSearchCityByCountry() {
	
	    Uni<GeoLocation> result =
	            weatherApiAdapter.searchCityWithCountry("Monterrey", "MX");
	
	    GeoLocation location =
	            result.await().indefinitely();
	
	    assertNotNull(location);
	    assertEquals("MX", location.getCountry());
	}

}
