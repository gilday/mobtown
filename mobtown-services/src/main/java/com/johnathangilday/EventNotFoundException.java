package com.johnathangilday;

import com.johnathangilday.jaxrs.EventNotFoundExceptionMapper;

/**
 * JAX-RS knows how to map this exception with the {@link EventNotFoundExceptionMapper}
 */
public class EventNotFoundException extends RuntimeException {

    private final int id;

    public EventNotFoundException(final int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
