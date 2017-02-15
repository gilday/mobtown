package mobtown.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * HK2 binder for the domain services backed by JPA
 */
public class JPADomainBinder extends AbstractBinder implements DomainBinder {

    /**
     * @param factory pre-configured factory for creating the {@link EntityManagerFactory}
     * @return DomainBinder which uses the given scope for each {@link EntityManager} it creates
     */
    public static JPADomainBinder create(final EntityManagerFactoryHK2Factory factory) {
        return new JPADomainBinder(factory);
    }

    /**
     * @return a DomainBinder backed by an in-memory HSQLDB database for testing
     */
    public static JPADomainBinder createForTest() {
        final EntityManagerFactoryHK2Factory factory = new EntityManagerFactoryHK2Factory("mobtown-test-pu");
        return JPADomainBinder.create(factory);
    }

    private JPADomainBinder(final EntityManagerFactoryHK2Factory factory) {
        this.factory = factory;
    }

    private final EntityManagerFactoryHK2Factory factory;

    protected void configure() {
        bindFactory(factory)
                .to(EntityManagerFactory.class)
                .in(Singleton.class);
        bind(SpecialEventRepositoryImpl.class).to(SpecialEventRepository.class);
    }
}
