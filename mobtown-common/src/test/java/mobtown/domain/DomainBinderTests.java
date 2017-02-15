package mobtown.domain;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JPADomainBinder}
 */
public class DomainBinderTests {

    private ServiceLocator locator;

    @Before
    public void before() {
        locator = ServiceLocatorUtilities.bind(JPADomainBinder.createForTest(), new NormalScopedEntityManagerBinder());
    }

    @Test
    public void it_produces_entity_manager() {
        final EntityManager em = locator.getService(EntityManager.class);
        assertThat(em).isNotNull();
    }
}
