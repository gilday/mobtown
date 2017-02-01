package mobtown.services.jaxrs;

import mobtown.domain.EntityManagerFactoryHK2Factory;
import mobtown.domain.EntityManagerHK2Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * HK2 module which wires JPA in a per-Jersey-request configuration
 */
public class RequestScopedJPABinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(new EntityManagerFactoryHK2Factory("mobtown-pu"))
                .to(EntityManagerFactory.class)
                .in(Singleton.class);
        bindFactory(EntityManagerHK2Factory.class)
                .to(EntityManager.class)
                .in(RequestScoped.class);
    }
}
