package com.johnathangilday.ingest;


import com.socrata.exceptions.SodaError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tests for {@link EventsConsumer}
 */
public class EventsConsumerTest {

    private EventsConsumer consumer;

    @Before
    public void before() {
        consumer = new EventsConsumer();
    }

    @Test
    public void it_retrieves_events() throws SodaError, InterruptedException {
        final List<Event> all = consumer.all();
        System.out.println(all);
        assertThat(all).hasSize(1);
        assertThat(all).first().hasFieldOrProperty("permitID");
    }
}
