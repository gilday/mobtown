package mobtown.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mobtown.domain.SpecialEventSummary;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * data transfer object representation of {@link SpecialEventSummary}
 */
public class SpecialEventSummaryDTO {

    public static SpecialEventSummaryDTO fromModel(final SpecialEventSummary model) {
        return new SpecialEventSummaryDTO(
                model.permitID,
                model.name,
                model.type,
                model.start,
                model.end,
                model.arrestsCount);
    }

    @JsonCreator
    public static SpecialEventSummaryDTO of(
            final @JsonProperty("permitID") String permitID,
            final @JsonProperty("name") String name,
            final @JsonProperty("type") String type,
            final @JsonProperty("start") LocalDateTime start,
            final @JsonProperty("end") LocalDateTime end,
            final @JsonProperty("arrestsCount") long arrestsCount) {
        return new SpecialEventSummaryDTO(
                permitID,
                name,
                type,
                start,
                end,
                arrestsCount);
    }

    private SpecialEventSummaryDTO(
            final String permitID,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end,
            final long arrestsCount) {
        this.permitID = permitID;
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.arrestsCount = arrestsCount;
    }

    public final String permitID;
    public final String name;
    public final String type;
    public final LocalDateTime start;
    public final LocalDateTime end;
    public final long arrestsCount;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialEventSummaryDTO)) return false;
        final SpecialEventSummaryDTO that = (SpecialEventSummaryDTO) o;
        return arrestsCount == that.arrestsCount &&
                Objects.equals(permitID, that.permitID) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permitID, name, type, start, end, arrestsCount);
    }
}
