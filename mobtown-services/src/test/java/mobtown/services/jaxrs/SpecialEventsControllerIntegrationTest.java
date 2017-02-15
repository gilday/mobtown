package mobtown.services.jaxrs;

import mobtown.services.dto.SpecialEventDTO;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SpecialEventsController} which use Hibernate with a test database
 */
public class SpecialEventsControllerIntegrationTest extends MobtownJerseyTest {

    @Override
    MobtownResourceConfig configureMobtownResourceConfig() {
        return MobtownResourceConfig.createForIntegrationTest();
    }

    @Test
    public void it_returns_bad_request_when_new_event_does_not_contain_required_fields() {
        // GIVEN an event that is missing required fields
        final SpecialEventDTO event = SpecialEventDTO.of("fake-permit-id", "", "", null, null, null);

        // WHEN save event
        final Response response = target("/api/events/fake-permit-id")
                .request()
                .buildPut(Entity.entity(event, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        // THEN returns 400 Bad Request
        assertThat(response.getStatus()).isEqualTo(400);
    }
}
