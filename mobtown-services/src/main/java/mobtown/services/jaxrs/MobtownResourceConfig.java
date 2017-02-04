package mobtown.services.jaxrs;

import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use Jersey {@link ResourceConfig} to do application level component binding
 */
public class MobtownResourceConfig extends ResourceConfig {

    public MobtownResourceConfig(final Binder... modules) {
        for (Binder module : modules) {
            register(module);
        }
        register(SpecialEventsController.class);
        register(EventNotFoundExceptionMapper.class);
        register(ObjectMapperProvider.class);
    }
}
