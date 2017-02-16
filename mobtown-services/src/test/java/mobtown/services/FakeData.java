package mobtown.services;

import mobtown.domain.SpecialEvent;
import mobtown.services.dto.SpecialEventDTO;

import java.time.LocalDateTime;

/**
 * static factory methods for creating fake data for tests
 */
public class FakeData {

    public static SpecialEvent createSpecialEvent() {
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

    public static SpecialEventDTO createSpecialEventDTO() {
        return SpecialEventDTO.fromModel(createSpecialEvent());
    }
}
