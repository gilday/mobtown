package com.johnathangilday.jaxrs;

import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use Jersey {@link ResourceConfig} to do application level component binding
 */
public class MobtownResourceConfig extends ResourceConfig {

    public MobtownResourceConfig(final Binder module) {
        register(module);
        register(SpecialEventsController.class);
        register(EventNotFoundExceptionMapper.class);
        register(ObjectMapperProvider.class);
        register(DatabaseInitializer.class);
    }
}
