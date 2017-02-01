package mobtown.domain;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DomainBinder}
 */
public class DomainBinderTests {

    private ServiceLocator locator;

    @Before
    public void before() {
        locator = ServiceLocatorUtilities.bind(new DomainBinder(), new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(new EntityManagerFactoryHK2Factory("mobtown-test-pu"))
                        .to(EntityManagerFactory.class)
                        .in(Singleton.class);
                bindFactory(EntityManagerHK2Factory.class)
                        .to(EntityManager.class);
            }
        });
    }

    @Test
    public void it_produces_entity_manager() {
        final EntityManager em = locator.getService(EntityManager.class);
        assertThat(em).isNotNull();
    }
}
