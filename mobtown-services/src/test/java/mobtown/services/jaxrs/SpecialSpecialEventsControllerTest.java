package mobtown.services.jaxrs;

import mobtown.domain.FakeSpecialEventRepository;
import mobtown.domain.SpecialEvent;
import mobtown.domain.SpecialEventRepository;
import mobtown.services.MobtownBinder;
import mobtown.services.dto.SpecialEventDTO;
import mobtown.services.dto.SpecialEventSummaryDTO;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link SpecialEventsController}
 */
public class SpecialSpecialEventsControllerTest extends MobtownJerseyTest {

    private SpecialEventRepository repository;

    @Override
    Binder[] createModules() {
        repository = new FakeSpecialEventRepository();
        return new Binder[] {
            new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(repository).to(SpecialEventRepository.class);
                }
            },
            MobtownBinder.createForTest()
        };
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
        final SpecialEvent event = createSpecialEvent();
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
        final SpecialEvent event = createSpecialEvent();
        repository.add(event);

        // WHEN list arrests for the event
        final SpecialEventDTO dto = target("/api/events/fake-permit-id")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(SpecialEventDTO.class);

        // THEN contains two arrests
        assertThat(dto.arrests).hasSize(2);
    }

    private SpecialEvent createSpecialEvent() {
        final SpecialEvent event = new SpecialEvent(
                "fake-permit-id",
                "Sole of the City",
                "run",
                LocalDateTime.of(2015, 4, 25, 12, 0),
                LocalDateTime.of(2015, 4, 25, 17, 0));
        event.addArrest(
                "1600 E 25TH ST",
                "Darley Park",
                LocalDateTime.of(2016, 12, 31, 23, 51),
                "(39.3042766852, -76.6356789283)");
        event.addArrest(
                "600 LAURENS ST",
                "Upton",
                LocalDateTime.of(2016, 12, 31, 23, 51),
                "(39.3166584878, -76.5953503430)");
        return event;
    }
}
