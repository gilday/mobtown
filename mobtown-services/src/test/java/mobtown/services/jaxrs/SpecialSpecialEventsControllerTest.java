package mobtown.services.jaxrs;

import mobtown.domain.FakeSpecialEventRepository;
import mobtown.domain.SpecialEvent;
import mobtown.domain.SpecialEventRepository;
import mobtown.services.MobtownBinder;
import mobtown.services.dto.SpecialEventDTO;
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
        final List<SpecialEventDTO> events = target("/events")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(new GenericType<List<SpecialEventDTO>>() { });

        // THEN returns empty list
        assertThat(events).isEmpty();
    }

    @Test
    public void it_returns_list_of_special_events() {
        // GIVEN the repository contain an event
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
        repository.add(event);

        // WHEN list events
        final List<SpecialEventDTO> events = target("/events")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .buildGet()
                .invoke(new GenericType<List<SpecialEventDTO>>() {});

        // THEN returns events
        assertThat(events).isNotEmpty();
    }
}
