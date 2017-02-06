package mobtown.ingest;

import com.beust.jcommander.Parameter;
import mobtown.AbstractApp;
import mobtown.domain.EntityManagerFactoryHK2Factory;
import mobtown.domain.JPADomainBinder;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.Properties;

/**
 * Runs the ingest process
 */
public class App extends AbstractApp {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(final String[] argv) {
        final App app = new App();
        AbstractApp.run(app, argv);
    }

    @Parameter(names = "--drop", description = "if set, drop the database before ingest")
    private boolean drop = false;

    @Parameter(names = "--max", description = "maximum number of special events to ingest")
    private int max = 10000;

    @Override
    public void run() {
        final Properties properties = createEntityManagerFactoryProperties();
        final EntityManagerFactoryHK2Factory factory = new EntityManagerFactoryHK2Factory(properties);
        final ServiceLocator locator = ServiceLocatorUtilities.bind(JPADomainBinder.create(factory), new IngestBinder(max));

        final OpenBaltimoreIngest ingest = locator.getService(OpenBaltimoreIngest.class);
        log.info("beginning ingest of at most {} special events to database {}", max, connection);
        ingest.execute();
        log.info("finished");

        // close EntityManagerFactory singleton
        final EntityManagerFactory emf = locator.getService(EntityManagerFactory.class);
        emf.close();
    }

    /**
     * add additional EntityManagerFactory properties if the user wants to drop the tables before
     * ingest
     */
    @Override
    protected Properties createEntityManagerFactoryProperties() {
        final Properties properties = super.createEntityManagerFactoryProperties();
        final String action = drop ? "drop-and-create" : "create";
        Arrays.asList(
                "javax.persistence.schema-generation.database.action",
                "javax.persistence.schema-generation.scripts.action"
        ).forEach(p -> properties.put(p, action));
        return properties;
    }
}
