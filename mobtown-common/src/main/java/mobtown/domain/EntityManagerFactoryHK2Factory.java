package mobtown.domain;

import org.glassfish.hk2.api.Factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryHK2Factory implements Factory<EntityManagerFactory> {

    private final String persistenceUnit;

    public EntityManagerFactoryHK2Factory(final String persistenceUnit) {
        this.persistenceUnit = persistenceUnit;
    }

    @Override
    public EntityManagerFactory provide() {
        return Persistence.createEntityManagerFactory(persistenceUnit);
    }

    @Override
    public void dispose(final EntityManagerFactory emf) {
        emf.close();
    }
}
