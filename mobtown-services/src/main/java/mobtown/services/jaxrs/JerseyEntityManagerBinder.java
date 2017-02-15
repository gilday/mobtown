package mobtown.services.jaxrs;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.persistence.EntityManager;

/**
 * Binds an {@link EntityManager} to the Jersey request scope
 */
public class JerseyEntityManagerBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(JerseyEntityManagerFactory.class)
                .to(EntityManager.class)
                .in(RequestScoped.class)
                .proxy(true);
    }
}
