package mobtown.domain;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DomainBinder}
 */
public class DomainBinderTests {

    private ServiceLocator locator;

    @Before
    public void before() {
        final EntityManagerFactoryHK2Factory factory = new EntityManagerFactoryHK2Factory("mobtown-test-pu", new Properties());
        locator = ServiceLocatorUtilities.bind(new DomainBinder(factory));
    }

    @Test
    public void it_produces_entity_manager() {
        final EntityManager em = locator.getService(EntityManager.class);
        assertThat(em).isNotNull();
    }
}
