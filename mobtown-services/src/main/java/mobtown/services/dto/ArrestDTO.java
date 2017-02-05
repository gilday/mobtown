package mobtown.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mobtown.domain.Arrest;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * data transfer representation of {@link Arrest} value object
 */
public class ArrestDTO {

    public static ArrestDTO fromModel(final Arrest arrest) {
        return new ArrestDTO(
                arrest.getIntersection(),
                arrest.getNeighborhood(),
                arrest.getTimestamp(),
                arrest.getLocation());
    }

    @JsonCreator
    public static ArrestDTO of(
            final @JsonProperty("intersection") String intersection,
            final @JsonProperty("neighborhood") String neighborhood,
            final @JsonProperty("timestamp") LocalDateTime timestamp,
            final @JsonProperty("location") String location) {
        return new ArrestDTO(intersection, neighborhood, timestamp, location);
    }

    public final String intersection;
    public final String neighborhood;
    public final LocalDateTime timestamp;
    public final String location;

    private ArrestDTO(final String intersection, final String neighborhood, final LocalDateTime timestamp, final String location) {
        this.intersection = intersection;
        this.neighborhood = neighborhood;
        this.timestamp = timestamp;
        this.location = location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrestDTO)) return false;
        final ArrestDTO arrestDTO = (ArrestDTO) o;
        return Objects.equals(intersection, arrestDTO.intersection) &&
                Objects.equals(neighborhood, arrestDTO.neighborhood) &&
                Objects.equals(timestamp, arrestDTO.timestamp) &&
                Objects.equals(location, arrestDTO.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intersection, neighborhood, timestamp, location);
    }
}
