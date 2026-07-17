package com.weather.infrastructure.rest.exception;

import com.weather.domain.exceptions.WeatherProviderUnavailableException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class WeatherProviderUnavailableExceptionMapper implements ExceptionMapper<WeatherProviderUnavailableException>{

	@Override
	public Response toResponse(WeatherProviderUnavailableException ex) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(ex.getMessage()))
                .build();
	}

}
