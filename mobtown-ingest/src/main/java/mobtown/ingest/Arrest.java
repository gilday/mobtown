package mobtown.ingest;

import com.socrata.model.Location;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

/**
 * Immutable value object representing an Arrest result from Baltimore Open Data
 *
 * Uses Jackson 1.x annotations because the socrata-java API uses Jackson 1.x
 */
public class Arrest {

    @JsonCreator
    public static Arrest of(@JsonProperty("arrestdate") final Date arrestdate,
                            @JsonProperty("charge") final String charge,
                            @JsonProperty("arrestlocation") final String intersection,
                            @JsonProperty("name1") final String neighborhood,
                            @JsonProperty("location_1") final Location location) {
        final LocalDateTime timestamp = LocalDateTime.ofInstant(arrestdate.toInstant(), ZoneOffset.systemDefault());
        return Arrest.of(timestamp, charge, intersection, neighborhood, location);
    }

    public static Arrest of(final LocalDateTime timestamp,
                            final String charge,
                            final String intersection,
                            final String neighborhood,
                            final Location location) {
        return new Arrest(timestamp, charge, intersection, neighborhood, location);
    }

    public final LocalDateTime timestamp;
    public final String charge;
    public final String intersection;
    public final String neighborhood;
    public final Location location;

    private Arrest(final LocalDateTime timestamp,
                   final String charge,
                   final String intersection,
                   final String neighborhood,
                   final Location location) {
        this.timestamp = timestamp;
        this.charge = charge;
        this.intersection = intersection;
        this.neighborhood = neighborhood;
        this.location = location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Arrest arrest = (Arrest) o;
        return Objects.equals(timestamp, arrest.timestamp) &&
                Objects.equals(charge, arrest.charge) &&
                Objects.equals(intersection, arrest.intersection) &&
                Objects.equals(neighborhood, arrest.neighborhood) &&
                Objects.equals(location, arrest.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, charge, intersection, neighborhood, location);
    }

    @Override
    public String toString() {
        return "Arrest{" +
                "timestamp='" + timestamp + '\'' +
                ", charge='" + charge + '\'' +
                ", intersection'" + intersection + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", location=" + location +
                '}';
    }
}
