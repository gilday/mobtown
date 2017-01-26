package com.johnathangilday.ingest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

public final class Event {

    public static TypeReference<List<Event>> LIST_TYPE = new TypeReference<List<Event>>() {};

    @JsonCreator
    public static Event of(
            @JsonProperty("permit_id") final String permitID) {
        return new Event(permitID);
    }

    public final String permitID;

    private Event(final String permitID) {
        this.permitID = permitID;
    }

    @Override
    public int hashCode() {
        return permitID.hashCode(); }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        return permitID.equals(other.permitID);
    }

    @Override
    public String toString() {
        return "Event{" +
                "permitID='" + permitID + '\'' +
                '}';
    }
}
