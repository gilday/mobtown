package mobtown.ingest.test;

import mobtown.ingest.Arrest;
import mobtown.ingest.SpecialEventPermit;
import mobtown.domain.SpecialEvent;
import com.socrata.model.Location;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Static factory for fake data
 */
public class FakeData {

    public static SpecialEventPermit openBaltimoreSpecialEventPermit() {
        return SpecialEventPermit.of(
                "fake-permit-ID",
                "Sole of the City",
                "run",
                LocalDateTime.of(2015, Month.APRIL, 25, 12, 0, 0),
                LocalDateTime.of(2015, Month.APRIL, 25, 17, 0, 0));
    }

    public static Arrest openBaltimoreArrest() {
        return Arrest.of(
                "fake-arrest-id",
                LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 51),
                "4 3550",
                "100 S. CLINTON",
                "Southeastern",
                new Location(39.3031563513, -76.6342260745, null)
        );
    }

    public static SpecialEvent specialEvent() {
        final SpecialEvent event = new SpecialEvent(
                "fake-permit-id",
                "Sole of the City",
                "run",
                LocalDateTime.of(2015, Month.APRIL, 25, 12, 0),
                LocalDateTime.of(2015, Month.APRIL, 25, 17, 0));
        event.addArrest(
                "fake-arrest-id",
                "1600 E 25TH ST",
                "Darley Park",
                LocalDateTime.of(2016, Month.DECEMBER, 31, 23,51),
                "(39.3042766852, -76.6356789283)");
        return event;
    }
}
