package mobtown.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * read-only summary representation of {@link SpecialEvent} entity which does not include arrests value objects
 */
public class SpecialEventSummary {

    public static SpecialEventSummary fromModel(final SpecialEvent entity) {
        return new SpecialEventSummary(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getStart(),
                entity.getEnd(),
                entity.getArrestsCount());
    }

    public static SpecialEventSummary of(
            final String permitID,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end,
            final long arrestsCount) {
        return new SpecialEventSummary(
                permitID,
                name,
                type,
                start,
                end,
                arrestsCount);
    }

    public SpecialEventSummary(
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
        if (!(o instanceof SpecialEventSummary)) return false;
        final SpecialEventSummary that = (SpecialEventSummary) o;
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
