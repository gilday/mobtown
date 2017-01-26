package com.johnathangilday.ingest;

import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import com.sun.jersey.api.client.GenericType;

import java.util.List;

public class EventsConsumer {

    private final String resourceID = "yr2j-atgt";

    private static final GenericType<List<Event>> EVENTS_LIST_TYPE = new GenericType<List<Event>>() {};

    public List<Event> all() throws SodaError, InterruptedException {
        final Soda2Consumer consumer = Soda2Consumer.newConsumer("https://data.baltimorecity.gov");
        final SoqlQuery query = new SoqlQueryBuilder()
                .addSelectPhrase("permit_id")
                .setLimit(1)
                .build();
        return consumer.query(resourceID, query, EVENTS_LIST_TYPE);
    }
}
