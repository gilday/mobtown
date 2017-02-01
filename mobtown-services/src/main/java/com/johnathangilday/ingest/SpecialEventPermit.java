package com.johnathangilday.ingest;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

/**
 * Immutable value object representing a Special SpecialEventPermit Permit from Baltimore Open Data
 *
 * Uses Jackson 1.x annotations because the socrata-java API uses Jackson 1.x
 */
public final class SpecialEventPermit {

    @JsonCreator
    public static SpecialEventPermit of(
            @JsonProperty("permit_id") final String permitID,
            @JsonProperty("permit_name_full") final String name,
            @JsonProperty("facility_type") final String type,
            @JsonProperty("start_date") final Date startDate,
            @JsonProperty("end_date") final Date endDate) {
        final LocalDateTime start = LocalDateTime.ofInstant(startDate.toInstant(), ZoneOffset.systemDefault());
        final LocalDateTime end = LocalDateTime.ofInstant(endDate.toInstant(), ZoneOffset.systemDefault());
        return SpecialEventPermit.of(permitID, name, type, start, end);
    }

    public static SpecialEventPermit of(
            final String permitID,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end) {
        return new SpecialEventPermit(permitID, name, type, start, end);
    }

    public final String id;
    public final String name;
    public final String type;
    public final LocalDateTime start;
    public final LocalDateTime end;

    public SpecialEventPermit(
            final String id,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SpecialEventPermit that = (SpecialEventPermit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, start, end);
    }
}
