package com.johnathangilday.jaxrs;

import com.johnathangilday.MobtownBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use Jersey {@link ResourceConfig} to do application level component binding
 */
public class MobtownResourceConfig extends ResourceConfig {

    public MobtownResourceConfig() {
        register(new MobtownBinder());
        register(SpecialEventsController.class);
        register(EventNotFoundExceptionMapper.class);
        register(ObjectMapperProvider.class);
    }
}
