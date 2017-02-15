package mobtown.services.jaxrs;

import mobtown.domain.FakeSpecialEventRepository;
import mobtown.domain.InMemoryDomainBinder;
import mobtown.domain.SpecialEvent;
import mobtown.domain.SpecialEventRepository;
import mobtown.services.FakeData;
import mobtown.services.dto.SpecialEventDTO;
import mobtown.services.dto.SpecialEventSummaryDTO;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link SpecialEventsController}
 */
public class SpecialEventsControllerTest extends MobtownJerseyTest {

    private SpecialEventRepository repository;

    @Override
    protected MobtownResourceConfig configureMobtownResourceConfig() {
        repository = new FakeSpecialEventRepository();
        return new MobtownResourceConfig(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(repository).to(SpecialEventRepository.class);
            }
        }, new InMemoryDomainBinder());
    }

    @Test
    public void it_returns_empty_list_of_special_events() {
        // GIVEN the repository contains no events

        // WHEN list events
        final List<SpecialEventSummaryDTO> events = target("/api/events")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(new GenericType<List<SpecialEventSummaryDTO>>() { });

        // THEN returns empty list
        assertThat(events).isEmpty();
    }

    @Test
    public void it_returns_list_of_special_events() {
        // GIVEN the repository contain an event
        final SpecialEvent event = FakeData.createSpecialEvent();
        repository.add(event);

        // WHEN list events
        final List<SpecialEventSummaryDTO> events = target("/api/events")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(new GenericType<List<SpecialEventSummaryDTO>>() {});

        // THEN returns events
        assertThat(events).isNotEmpty();
    }

    @Test
    public void it_returns_a_special_event_with_list_of_arrests_for_a_given_id() {
        // GIVEN the repository contains an event with two arrests
        final SpecialEvent event = FakeData.createSpecialEvent();
        repository.add(event);

        // WHEN list arrests for the event
        final SpecialEventDTO dto = target("/api/events/fake-permit-id")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(SpecialEventDTO.class);

        // THEN contains two arrests
        assertThat(dto.arrests).hasSize(2);
    }

    @Test
    public void it_creates_a_new_special_event() {
        // GIVEN an empty repository
        assertThat(repository.all().toList().blockingGet()).isEmpty();

        // WHEN request a new special event be created
        final SpecialEventDTO event = SpecialEventDTO.fromModel(FakeData.createSpecialEvent());
        final Response response = target("/api/events/fake-permit-id")
                .request()
                .buildPut(Entity.entity(event, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        // THEN responds with HTTP 204 No Content
        assertThat(response.getStatus()).isEqualTo(204);
        // AND repository contains one new event
        final List<SpecialEvent> events = repository.all().toList().blockingGet();
        assertThat(events).hasSize(1);
        final SpecialEvent persisted = events.get(0);
        assertThat(persisted.getId()).isEqualTo(event.permitID);
        assertThat(persisted.getName()).isEqualTo(event.name);
        assertThat(persisted.getType()).isEqualTo(event.type);
        assertThat(persisted.getStart()).isEqualTo(event.start);
        assertThat(persisted.getEnd()).isEqualTo(event.end);
    }
}
