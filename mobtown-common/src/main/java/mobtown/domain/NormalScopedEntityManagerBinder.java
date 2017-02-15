package mobtown.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.persistence.EntityManager;

/**
 * provides a normal scoped binding for {@link EntityManager}
 */
public class NormalScopedEntityManagerBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(EntityManagerHK2Factory.class).to(EntityManager.class);
    }
}
