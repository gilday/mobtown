package com.johnathangilday.ingest;

import com.johnathangilday.test.FakeData;
import com.socrata.api.Soda2Consumer;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * tests for {@link SpecialEventPermitsConsumer}
 */
public class SpecialSpecialEventPermitsConsumerTest {

    private static final String FAKE_RESOURCE_ID = "fake-resource-id";

    private Soda2Consumer mockSodaConsumer;
    private SpecialEventPermitsConsumer consumer;

    @Before
    public void before() {
        mockSodaConsumer = mock(Soda2Consumer.class);
        consumer = new SpecialEventPermitsConsumer(FAKE_RESOURCE_ID, 1, mockSodaConsumer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void it_requests_more_results_on_demand() throws SodaError, InterruptedException {
        // GIVEN the soda consumer returns one page then empty result set
        whenQuery().thenReturn(Collections.singletonList(FakeData.openBaltimoreSpecialEventPermit()), Collections.emptyList());

        // WHEN request at most two items
        final Observable<SpecialEventPermit> events = consumer.all().take(2);

        // THEN returns one event in the stream
        events.test().assertValueCount(1);
        // AND consumer calls underlying soda consumer twice before completing
        verify(mockSodaConsumer, times(2))
                .query(eq(FAKE_RESOURCE_ID), any(SoqlQuery.class), eq(SpecialEventPermitsConsumer.PERMITS_LIST_TYPE));
    }

    @Test
    public void it_calls_error_when_query_fails() throws SodaError, InterruptedException {
        // GIVEN the soda consumer throw an Exception
        whenQuery().thenThrow(new SodaError("boom!"));

        // WHEN request items
        final Observable<SpecialEventPermit> events = consumer.all();

        // THEN error
        events.take(1).test().assertError(SodaError.class);

    }

    private OngoingStubbing<List<SpecialEventPermit>> whenQuery() throws SodaError, InterruptedException {
        return when(mockSodaConsumer.query(eq(FAKE_RESOURCE_ID), any(SoqlQuery.class), eq(SpecialEventPermitsConsumer.PERMITS_LIST_TYPE)));
    }
}
