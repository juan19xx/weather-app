package com.weather.infrastructure.rest.exception;

import com.weather.domain.exceptions.ServerErrorException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServerErrorExceptionMapper implements ExceptionMapper<ServerErrorException>{

	@Override
	public Response toResponse(ServerErrorException ex) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(ex.getMessage()))
                .build();
	}

}
