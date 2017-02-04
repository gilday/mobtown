package mobtown.ingest;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import mobtown.domain.JPADomainBinder;
import mobtown.domain.EntityManagerFactoryHK2Factory;
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
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(final String[] argv) {
        final App app = new App();
        final JCommander jCommander = new JCommander(app, argv);
        if (app.help) {
            jCommander.usage();
            System.exit(1);
        }
        app.run();
    }

    @Parameter(names = "--connection-string", description = "MySQL JDBC connection string")
    private String connection = "jdbc:mysql://localhost/mobtown";

    @Parameter(names = { "-u", "--user" }, description = "MySQL user")
    private String user = "root";

    @Parameter(names = {"-p", "--password" }, description = "MySQL password")
    private String password = "password";

    @Parameter(names = "--drop", description = "if set, drop the database before ingest")
    private boolean drop = false;

    @Parameter(names = "--max", description = "maximum number of special events to ingest")
    private int max = 10000;

    @Parameter(names = "--help", description = "display usage then exit", help = true)
    private boolean help = false;


    private void run() {
        final Properties properties = new Properties();
        if (connection != null)
            properties.put("javax.persistence.jdbc.url", connection);
        if (user != null)
            properties.put("javax.persistence.jdbc.user", user);
        if (password != null)
            properties.put("javax.persistence.jdbc.password", password);
        final String action = drop ? "drop-and-create" : "create";
        Arrays.asList(
                "javax.persistence.schema-generation.database.action",
                "javax.persistence.schema-generation.scripts.action"
        ).forEach(p -> properties.put(p, action));

        final EntityManagerFactoryHK2Factory factory = new EntityManagerFactoryHK2Factory(properties);
        final ServiceLocator locator = ServiceLocatorUtilities.bind(JPADomainBinder.create(factory), new IngestBinder(max));

        // Perform ingest
        final OpenBaltimoreIngest ingest = locator.getService(OpenBaltimoreIngest.class);
        log.info("beginning ingest of at most {} special events to database {}", max, connection);
        ingest.execute();

        // close EntityManagerFactory singleton
        final EntityManagerFactory emf = locator.getService(EntityManagerFactory.class);
        emf.close();
        log.info("finished");
    }
}
