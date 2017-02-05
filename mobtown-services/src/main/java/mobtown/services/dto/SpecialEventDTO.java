package mobtown.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mobtown.domain.SpecialEvent;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * data transfer representation of {@link SpecialEvent} entity which does not include arrests value objects
 */
public class SpecialEventDTO {

    public static SpecialEventDTO fromModel(final SpecialEvent event) {
        return new SpecialEventDTO(
                event.getId(),
                event.getName(),
                event.getType(),
                event.getStart(),
                event.getEnd(),
                event.getArrests()
                        .stream()
                        .map(ArrestDTO::fromModel)
                        .collect(Collectors.toSet()));
    }

    @JsonCreator
    public static SpecialEventDTO of (
            @JsonProperty("permitID") final String permitID,
            @JsonProperty("name") final String name,
            @JsonProperty("type") final String type,
            @JsonProperty("start") final LocalDateTime start,
            @JsonProperty("end") final LocalDateTime end,
            @JsonProperty("arrests") final Set<ArrestDTO> arrests) {
        return new SpecialEventDTO(permitID, name, type, start, end, arrests);
    }

    public final String permitID;
    public final String name;
    public final String type;
    public final LocalDateTime start;
    public final LocalDateTime end;
    public final Set<ArrestDTO> arrests;

    private SpecialEventDTO(
            final String permitID,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end,
            final Set<ArrestDTO> arrests) {
        this.permitID = permitID;
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.arrests = arrests;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialEventDTO)) return false;
        final SpecialEventDTO that = (SpecialEventDTO) o;
        return Objects.equals(permitID, that.permitID) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(arrests, that.arrests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permitID, name, type, start, end, arrests);
    }
}
