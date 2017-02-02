package mobtown.domain;

import org.glassfish.hk2.api.Factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class EntityManagerFactoryHK2Factory implements Factory<EntityManagerFactory> {

    private final String persistenceUnit;
    private final Properties properties;

    /**
     * create a new {@link EntityManagerFactory} configured to use the default, test MySQL database
     */
    public EntityManagerFactoryHK2Factory() {
        this(new Properties());
    }

    /**
     * create a new {@link EntityManagerFactory} configured to use the MySQL persistence unit with the given property overrides
     * @param properties property overrides
     */
    public EntityManagerFactoryHK2Factory(final Properties properties) {
        this("mobtown-mysql-pu", properties);
    }

    EntityManagerFactoryHK2Factory(final String persistenceUnit, final Properties properties) {
        this.persistenceUnit = persistenceUnit;
        this.properties = properties;
    }

    @Override
    public EntityManagerFactory provide() {
        return Persistence.createEntityManagerFactory(persistenceUnit, properties);
    }

    @Override
    public void dispose(final EntityManagerFactory emf) {
        emf.close();
    }
}
