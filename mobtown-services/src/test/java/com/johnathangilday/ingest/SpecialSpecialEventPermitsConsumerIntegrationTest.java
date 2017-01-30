package com.johnathangilday.ingest;


import com.socrata.exceptions.SodaError;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tests for {@link SpecialEventPermitsConsumer} which make real requests to Open Baltimore
 */
public class SpecialSpecialEventPermitsConsumerIntegrationTest {

    private SpecialEventPermitsConsumer consumer;

    @Before
    public void before() {
        consumer = new SpecialEventPermitsConsumer();
    }

    @Test
    public void it_retrieves_events() throws SodaError, InterruptedException {
        final Observable<SpecialEventPermit> events = consumer.all();
        final SpecialEventPermit specialEventPermit = events.take(1).blockingFirst();
        assertThat(specialEventPermit.id).isNotBlank();
    }
}
