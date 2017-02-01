package mobtown.ingest;

import mobtown.domain.InMemoryDomainBinder;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IngestBinderTests {

    private ServiceLocator locator;

    @Before
    public void before() {
        locator = ServiceLocatorUtilities.bind(new InMemoryDomainBinder(), new IngestBinder());
    }

    @Test
    public void it_produces_open_baltimore_ingest_service() {
        final OpenBaltimoreIngest service = locator.getService(OpenBaltimoreIngest.class);
        assertThat(service).isNotNull();
    }
}
