package com.johnathangilday;

import com.johnathangilday.ingest.OpenBaltimoreIngest;
import com.johnathangilday.test.TestJPABinder;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests that {@link MobtownBinder} is properly configured
 */
public class MobtownBinderTest {

    private ServiceLocator locator;

    @Before
    public void before() {
        locator = ServiceLocatorUtilities.bind(new TestJPABinder(), new MobtownBinder());
    }

    @Test
    public void it_binds_objectmapper_to_singleton_scope() {
        final ObjectMapper first = locator.getService(ObjectMapper.class);
        final ObjectMapper second = locator.getService(ObjectMapper.class);

        assertThat(first).isNotNull();
        assertThat(second).isNotNull();
        assertThat(first).isSameAs(second);
    }

    @Test
    public void it_produces_entity_manager() {
        final EntityManager em = locator.getService(EntityManager.class);
        assertThat(em).isNotNull();
    }

    @Test
    public void it_produces_open_baltimore_ingest_service() {
        final OpenBaltimoreIngest service = locator.getService(OpenBaltimoreIngest.class);
        assertThat(service).isNotNull();
    }

}
