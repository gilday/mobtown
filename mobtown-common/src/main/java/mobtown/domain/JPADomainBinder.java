package mobtown.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.annotation.Annotation;
import java.util.Properties;

/**
 * HK2 binder for the domain services backed by JPA
 */
public class JPADomainBinder extends AbstractBinder implements DomainBinder {

    /**
     * @param factory pre-configured factory for creating the {@link EntityManagerFactory}
     * @return DomainBinder which creates a new {@link EntityManager} each time one is needed (default scope)
     */
    public static JPADomainBinder create(final EntityManagerFactoryHK2Factory factory) {
        return JPADomainBinder.create(factory, null);
    }

    /**
     * @param factory pre-configured factory for creating the {@link EntityManagerFactory}
     * @param entityManagerScope scope to which {@link EntityManager} instances are bound
     * @return DomainBinder which uses the given scope for each {@link EntityManager} it creates
     */
    public static JPADomainBinder create(final EntityManagerFactoryHK2Factory factory, final Class<? extends Annotation> entityManagerScope) {
        return new JPADomainBinder(factory, entityManagerScope);
    }

    /**
     * @return a DomainBinder backed by an in-memory derby database for testing
     */
    public static JPADomainBinder createForTest() {
        final EntityManagerFactoryHK2Factory factory = new EntityManagerFactoryHK2Factory("mobtown-test-pu", new Properties());
        return JPADomainBinder.create(factory);
    }

    private final EntityManagerFactoryHK2Factory factory;
    private final Class<? extends Annotation> entityManagerScope;

    private JPADomainBinder(final EntityManagerFactoryHK2Factory factory, final Class<? extends Annotation> entityManagerScope) {
        this.factory = factory;
        this.entityManagerScope = entityManagerScope;
    }

    protected void configure() {
        bindFactory(factory)
                .to(EntityManagerFactory.class)
                .in(Singleton.class);
        bindFactory(EntityManagerHK2Factory.class)
                .to(EntityManager.class)
                .in(entityManagerScope);
        bind(SpecialEventRepositoryImpl.class).to(SpecialEventRepository.class);
    }
}
