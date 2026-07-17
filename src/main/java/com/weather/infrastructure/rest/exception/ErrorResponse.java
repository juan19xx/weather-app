package com.weather.infrastructure.rest.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record ErrorResponse(String message) {

}
