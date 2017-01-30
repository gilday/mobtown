package com.johnathangilday.ingest;

import com.johnathangilday.Strings;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Tests for {@link ArrestsConsumer} which make real requests to Open Baltimore
 */
public class ArrestsConsumerIntegrationTest {

    private ArrestsConsumer consumer;

    @Before
    public void before() {
        consumer = new ArrestsConsumer();
    }

    @Test
    public void it_gets_arrests_by_date() {
        // GIVEN search for arrests on a date known to have arrests
        final LocalDate date = LocalDate.of(2016, 12, 31);

        // WHEN query
        final Observable<Arrest> results = consumer.query(date);

        // THEN returns results
        results.take(1).test()
                .assertValue(arrest ->
                        arrest.timestamp.toLocalDate().equals(date) &&
                        Strings.isNotEmpty(arrest.charge) &&
                        Strings.isNotEmpty(arrest.id))
                .assertNoErrors();
    }
}
