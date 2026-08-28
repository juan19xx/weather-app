package com.weather.test;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockTestResource
        implements QuarkusTestResourceLifecycleManager {

    private static WireMockServer server;

    @Override
    public Map<String, String> start() {

        server = new WireMockServer(options().dynamicPort());

        server.start();

        return Map.of(
                "quarkus.rest-client.openweather.url", server.baseUrl(),
                "openweather.api.key", "dummy-key"
        );
    }

    @Override
    public void stop() {

        if (server != null) {
            server.stop();
        }

    }

    public static WireMockServer server() {
        return server;
    }

}