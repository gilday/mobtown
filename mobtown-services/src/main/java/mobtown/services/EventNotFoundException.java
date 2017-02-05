package mobtown.services;

import mobtown.services.jaxrs.EventNotFoundExceptionMapper;

/**
 * JAX-RS knows how to map this exception with the {@link EventNotFoundExceptionMapper}
 */
public class EventNotFoundException extends RuntimeException {

    private final String id;

    public EventNotFoundException(final String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
