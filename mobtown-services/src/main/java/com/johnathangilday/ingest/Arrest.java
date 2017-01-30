package com.johnathangilday.ingest;

import com.socrata.model.Location;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Immutable value object representing an Arrest result from Baltimore Open Data
 */
public class Arrest {

    @JsonCreator
    public static Arrest of(@JsonProperty("arrest") final String id,
                            @JsonProperty("arrestdate") final String arrestdate,
                            @JsonProperty("charge") final String charge,
                            @JsonProperty("location") final String intersection,
                            @JsonProperty("name1") final String neighborhood,
                            @JsonProperty("location_1") final Location location) {
//        final LocalDateTime timestamp = LocalDateTime.parse(arrestdate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime timestamp = LocalDateTime.now();
        return new Arrest(id, timestamp, charge, intersection, neighborhood, location);
    }

    public final String id;
    public final LocalDateTime timestamp;
    public final String charge;
    public final String intersection;
    public final String neighborhood;
    public final Location location;

    private Arrest(final String id,
                   final LocalDateTime timestamp,
                   final String charge,
                   final String intersection,
                   final String neighborhood,
                   final Location location) {
        this.id = id;
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
        return Objects.equals(id, arrest.id) &&
                Objects.equals(timestamp, arrest.timestamp) &&
                Objects.equals(charge, arrest.charge) &&
                Objects.equals(intersection, arrest.intersection) &&
                Objects.equals(neighborhood, arrest.neighborhood) &&
                Objects.equals(location, arrest.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, charge, intersection, neighborhood, location);
    }

    @Override
    public String toString() {
        return "Arrest{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", charge='" + charge + '\'' +
                ", intersection'" + intersection + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", location=" + location +
                '}';
    }
}
