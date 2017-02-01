package mobtown.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A Baltimore Police Department arrest event
 */
@Embeddable
public class Arrest {

    @NotNull
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "INTERSECTION")
    private String intersection;

    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    @Column(name = "LOCATION")
    private String location;

    /**
     * no-args ctor for JPA
     */
    protected Arrest() { }

    Arrest(
            final String id,
            final String intersection,
            final String neighborhood,
            final LocalDateTime timestamp,
            final String location) {
        this.id = id;
        this.intersection = intersection;
        this.neighborhood = neighborhood;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getIntersection() {
        return intersection;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Arrest)) return false;
        final Arrest arrest = (Arrest) o;
        return Objects.equals(id, arrest.id) &&
                Objects.equals(intersection, arrest.intersection) &&
                Objects.equals(neighborhood, arrest.neighborhood) &&
                Objects.equals(timestamp, arrest.timestamp) &&
                Objects.equals(location, arrest.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, intersection, neighborhood, timestamp, location);
    }

    @Override
    public String toString() {
        return "Arrest{" +
                "id='" + id + '\'' +
                ", intersection='" + intersection + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", timestamp=" + timestamp +
                ", location='" + location + '\'' +
                '}';
    }
}
