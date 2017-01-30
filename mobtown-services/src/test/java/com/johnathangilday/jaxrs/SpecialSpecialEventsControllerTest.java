package com.johnathangilday.jaxrs;

import com.johnathangilday.MobtownBinder;
import com.johnathangilday.models.SpecialEvent;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.core.Application;

public class SpecialSpecialEventsControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        // set the port to 0 so that it will pick next available
        // this allows us to run parallel tests
        forceSet(TestProperties.CONTAINER_PORT, "0");

        return ResourceConfig.forApplication(new MobtownResourceConfig(new MobtownBinder()));
    }

    @Test
    public void it_returns_default_greeting() {
        final SpecialEvent greeting = target("events").request().get(SpecialEvent.class);
    }
}
