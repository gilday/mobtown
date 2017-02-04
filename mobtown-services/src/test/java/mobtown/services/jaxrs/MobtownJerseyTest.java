package mobtown.services.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import mobtown.services.ObjectMapperFactory;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.ws.rs.core.Application;

/**
 * Mobtown base class for Jersey Test Framework tests
 */
public abstract class MobtownJerseyTest extends JerseyTest {

    abstract Binder[] createModules();

    @Override
    protected Application configure() {
        // set the port to 0 so that it will pick next available
        // this allows the build system to run parallel tests
        forceSet(TestProperties.CONTAINER_PORT, "0");
        return new MobtownResourceConfig(createModules());
    }

    @Override
    protected void configureClient(final ClientConfig config) {
        // configure test client's Jackson ObjectMapper
        final ObjectMapper mapper = new ObjectMapperFactory().provide();
        final ObjectMapperProvider provider = new ObjectMapperProvider(mapper);
        config.register(provider);
    }
}
