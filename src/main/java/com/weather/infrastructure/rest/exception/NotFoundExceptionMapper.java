package com.weather.infrastructure.rest.exception;

import com.weather.domain.exceptions.NotFoundException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper
        implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {

        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(ex.getMessage()))
                .build();
    }
}
