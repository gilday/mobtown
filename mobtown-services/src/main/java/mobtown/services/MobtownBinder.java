package mobtown.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import mobtown.domain.DomainBinder;
import mobtown.domain.EntityManagerFactoryHK2Factory;
import mobtown.domain.InMemoryDomainBinder;
import mobtown.domain.JPADomainBinder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Singleton;

/**
 * HK2 module for wiring mobtown-services components
 */
public class MobtownBinder extends AbstractBinder {

    /**
     * @return binder configured to use JPA backed by MySQL
     */
    public static MobtownBinder create() {
        final JPADomainBinder domain = JPADomainBinder.create(new EntityManagerFactoryHK2Factory(), RequestScoped.class);
        return new MobtownBinder(domain);
    }

    /**
     * @return binder configured to use JPA backed by an in-memory Derby database
     */
    public static MobtownBinder createForIntegrationTest() {
        final JPADomainBinder domain = JPADomainBinder.createForTest();
        return new MobtownBinder(domain);
    }

    /**
     * @return binder configured to use in-memory collections
     */
    public static MobtownBinder createForTest() {
        final InMemoryDomainBinder domain = new InMemoryDomainBinder();
        return new MobtownBinder(domain);
    }

    private MobtownBinder(final DomainBinder domain) {
        this.domain = domain;
    }

    private final DomainBinder domain;

    @Override
    protected void configure() {
        install(domain);
        bindFactory(ObjectMapperFactory.class).to(ObjectMapper.class).in(Singleton.class);
    }
}