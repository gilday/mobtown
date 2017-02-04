package mobtown.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mobtown.domain.SpecialEvent;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * data transfer representation of {@link SpecialEvent} entity
 */
public class SpecialEventDTO {

    public static SpecialEventDTO fromEntity(final SpecialEvent entity) {
        return new SpecialEventDTO(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getStart(),
                entity.getEnd(),
                entity.getArrestsCount());
    }

    @JsonCreator
    public static SpecialEventDTO of(
            final @JsonProperty("permitID") String permitID,
            final @JsonProperty("name") String name,
            final @JsonProperty("type") String type,
            final @JsonProperty("start") LocalDateTime start,
            final @JsonProperty("end") LocalDateTime end,
            final @JsonProperty("citationsCount") int citationsCount) {
        return new SpecialEventDTO(
                permitID,
                name,
                type,
                start,
                end,
                citationsCount);
    }

    private SpecialEventDTO(
            final String permitID,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end,
            final int citationsCount) {
        this.permitID = permitID;
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.citationsCount = citationsCount;
    }

    public final String permitID;
    public final String name;
    public final String type;
    public final LocalDateTime start;
    public final LocalDateTime end;
    public final int citationsCount;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialEventDTO)) return false;
        final SpecialEventDTO that = (SpecialEventDTO) o;
        return citationsCount == that.citationsCount &&
                Objects.equals(permitID, that.permitID) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permitID, name, type, start, end, citationsCount);
    }
}
