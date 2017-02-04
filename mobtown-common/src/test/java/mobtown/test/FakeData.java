package mobtown.test;

import mobtown.domain.SpecialEvent;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Static factory for fake data
 */
public class FakeData {

    public static SpecialEvent specialEvent() {
        final SpecialEvent event = new SpecialEvent(
                "fake-permit-id",
                "Sole of the City",
                "run",
                LocalDateTime.of(2015, Month.APRIL, 25, 12, 0),
                LocalDateTime.of(2015, Month.APRIL, 25, 17, 0));
        event.addArrest(
                "1600 E 25TH ST",
                "Darley Park",
                LocalDateTime.of(2016, Month.DECEMBER, 31, 23,51),
                "(39.3042766852, -76.6356789283)");
        return event;
    }
}
