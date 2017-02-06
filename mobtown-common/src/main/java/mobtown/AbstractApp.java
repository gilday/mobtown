package mobtown;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Base for mobtown main application classes which handles common command line argument parsing
 */
public abstract class AbstractApp {

    /**
     * reusable static void main(string[]) runner
     * @param app instance of {@link AbstractApp} to run
     * @param argv cli arguments from public static void main
     */
    public static void run(final AbstractApp app, final String[] argv) {
        final JCommander jCommander = new JCommander(app, argv);
        if (app.help) {
            jCommander.usage();
            System.exit(1);
        }
        app.run();
    }

    @Parameter(names = "--connection-string", description = "MySQL JDBC connection string")
    protected String connection = "jdbc:mysql://localhost/mobtown";

    @Parameter(names = { "-u", "--user" }, description = "MySQL user")
    protected String user = "root";

    @Parameter(names = {"-p", "--password" }, description = "MySQL password")
    protected String password = "password";

    @Parameter(names = "--help", description = "display usage then exit", help = true)
    protected boolean help = false;

    /**
     * @return {@link Properties} for creating the {@link EntityManagerFactory} whose values come
     * from processing the cli arguments
     */
    protected Properties createEntityManagerFactoryProperties() {
        final Properties properties = new Properties();
        if (connection != null)
            properties.put("javax.persistence.jdbc.url", connection);
        if (user != null)
            properties.put("javax.persistence.jdbc.user", user);
        if (password != null)
            properties.put("javax.persistence.jdbc.password", password);
        return properties;
    }

    public abstract void run();
}
