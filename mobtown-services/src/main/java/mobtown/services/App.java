package mobtown.services;

import com.beust.jcommander.Parameter;
import mobtown.AbstractApp;
import mobtown.services.jaxrs.MobtownResourceConfig;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Properties;

/**
 * Point of entry. Configure and start mobtown-services
 */
public class App extends AbstractApp {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(final String[] argv) {
        final App app = new App();
        AbstractApp.run(app, argv);
    }

    @Parameter(names = "--port", description = "port on which to listen for HTTP requests")
    private int port = 8000;

    /**
     * runs JAX-RS {@link javax.ws.rs.core.Application} using Jersey in a Jetty container
     */
    @Override
    public void run() {
        final URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();

        // start jetty
        final Properties properties = createEntityManagerFactoryProperties();
        final MobtownResourceConfig app = MobtownResourceConfig.create(properties);
        logger.info("listening on port {}", port);
        final Server server = JettyHttpContainerFactory.createServer(baseUri, ResourceConfig.forApplication(app));
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}