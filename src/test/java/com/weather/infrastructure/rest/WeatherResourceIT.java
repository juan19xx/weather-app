package com.weather.infrastructure.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import com.weather.test.BaseWireMock;
import com.weather.test.WireMockTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(WireMockTestResource.class)
public class WeatherResourceIT extends BaseWireMock {

    @Test
    void shouldReturnWeather() {

        stubWeatherOk("Monterrey");

        given()
                .when()
                .get("/weather/Monterrey")
                .then()
                .statusCode(200)
                .body("name", equalTo("Monterrey"));

        verifyWeatherCalled("Monterrey");

    }
    
    @Test
    void shouldReturnWeatherNotFoundWeather() {

    	stubWeather404();

        given()
                .when()
                .get("/weather/NotValidCity")
                .then()
                .statusCode(404);
    }
    
    @Test
    void shouldReturnWeatherInternalServerError() {

    	stubWeather500();

        given()
                .when()
                .get("/weather/AnyCity")
                .then()
                .statusCode(500);
    }
    
    @Test
    void shouldReturnGeoLocation() {

    	stubGeoLocationOk("Monterrey");

        given()
                .when()
                .get("/weather/geolocation/Monterrey")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Monterrey"));

    }
    
    @Test
    void shouldReturnGeoLocationServerError() {

    	stubGeoLocation500();

        given()
                .when()
                .get("/weather/geolocation/StrangeCity")
                .then()
                .statusCode(500);

    }
    
    @Test
    void shouldReturnGeoLocationWithCountry() {

    	stubGeoLocationOk("Monterrey");

        given()
                .when()
                .get("/weather/geolocation/Monterrey/MX")
                .then()
                .statusCode(200)
                .body("name", equalTo("Monterrey"));

    }
    
    @Test
    void shouldNotReturnGeoLocationWithCountry() {

    	stubGeoLocationOk("Monterrey");

        given()
                .when()
                .get("/weather/geolocation/Monterrey/NOT_VALID_COUNTRY")
                .then()
                .statusCode(404);

    }

}