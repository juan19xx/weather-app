package com.weather.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public abstract class BaseWireMock {

    protected void stubWeatherOk(String city) {

        WireMockTestResource.server().stubFor(

                get(urlPathEqualTo("/data/2.5/weather"))
                        .withQueryParam("q", equalTo(city))
                        .withQueryParam("appid", equalTo("dummy-key"))
                        .willReturn(okJson("""
                        {
                          "coord":{
                             "lon":-100.31,
                             "lat":25.67
                          },
                          "weather":[
                            {
                              "main":"Clear",
                              "description":"clear sky"
                            }
                          ],
                          "main":{
                             "temp":301.15,
                             "humidity":40
                          },
                          "name":"%s"
                        }
                        """.formatted(city)))

        );

    }

    protected void stubWeather404() {

        WireMockTestResource.server()
                .stubFor(get(urlPathEqualTo("/data/2.5/weather"))
                        .willReturn(aResponse().withStatus(404)));

    }

    protected void stubWeather500() {

        WireMockTestResource.server()
                .stubFor(get(urlPathEqualTo("/data/2.5/weather"))
                        .willReturn(serverError()));

    }

    protected void verifyWeatherCalled(String city) {

        WireMockTestResource.server().verify(

                getRequestedFor(urlPathEqualTo("/data/2.5/weather"))
                        .withQueryParam("q", equalTo(city)));

    }
    
    protected void stubGeoLocationOk(String city) {

        WireMockTestResource.server().stubFor(

                get(urlPathEqualTo("/geo/1.0/direct"))
                        .withQueryParam("q", equalTo(city))
                        .withQueryParam("appid", equalTo("dummy-key"))
                        .withQueryParam("limit", equalTo("5"))
                        .willReturn(okJson("""
                        [
						    {
						        "name": "Monterrey",
						        "lat": 25.6802019,
						        "lon": -100.315258,
						        "country": "MX",
						        "state": "Nuevo León"
						    },
						    {
						        "name": "Monterey",
						        "lat": 36.600256,
						        "lon": -121.8946388,
						        "country": "US",
						        "state": "California"
						    }
						]
                        """))

        );

    }
    
    protected void stubGeoLocation500() {

        WireMockTestResource.server()
                .stubFor(get(urlPathEqualTo("/geo/1.0/direct"))
                        .willReturn(serverError()));

    }

}