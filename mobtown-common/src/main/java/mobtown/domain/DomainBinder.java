package mobtown.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Scope;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * HK2 binder for the domain services backed by JPA
 */
public class DomainBinder extends AbstractBinder {

    private final EntityManagerFactoryHK2Factory factory;
    private final Class<Scope> entityManagerScope;

    public DomainBinder(final EntityManagerFactoryHK2Factory factory) {
        this(factory, null);
    }

    /**
     *
     * @param factory pre-configured factory for creating the {@link EntityManagerFactory}
     * @param entityManagerScope scope to which {@link EntityManager} instances are bound
     */
    public DomainBinder(final EntityManagerFactoryHK2Factory factory, final Class<Scope> entityManagerScope) {
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
