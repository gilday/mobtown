package com.johnathangilday.jaxrs;

import com.johnathangilday.EventNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps custom exceptions which do not extend {@link javax.ws.rs.WebApplicationException}
 */
@Provider
public class EventNotFoundExceptionMapper implements ExceptionMapper<EventNotFoundException> {

    @Override
    public Response toResponse(final EventNotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .type(MediaType.TEXT_PLAIN)
                .entity("There exists no event with the special event permit id " + e.getId())
                .build();
    }
}
