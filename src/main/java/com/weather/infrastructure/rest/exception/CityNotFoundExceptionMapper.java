package com.weather.infrastructure.rest.exception;

import com.weather.domain.exceptions.CityNotFoundException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CityNotFoundExceptionMapper
        implements ExceptionMapper<CityNotFoundException> {

    @Override
    public Response toResponse(CityNotFoundException ex) {

        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(ex.getMessage()))
                .build();
    }
}
