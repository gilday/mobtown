package mobtown.ingest;

import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.model.soql.SoqlQuery;
import com.sun.jersey.api.client.GenericType;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Returns an observable stream of all Special SpecialEvent Permit records from Baltimore Open Data
 */
public class SpecialEventPermitsConsumer {

    protected static final GenericType<List<SpecialEventPermit>> PERMITS_LIST_TYPE = new GenericType<List<SpecialEventPermit>>() {};

    private final String resourceID;
    private final int limit;
    private final Soda2Consumer consumer;

    public SpecialEventPermitsConsumer() {
        resourceID = "yr2j-atgt";
        limit = 10;
        consumer = Soda2Consumer.newConsumer("https://data.baltimorecity.gov");
    }

    public SpecialEventPermitsConsumer(final String resourceID, final int limit, final Soda2Consumer consumer) {
        this.resourceID = resourceID;
        this.limit = limit;
        this.consumer = consumer;
    }

    public Observable<SpecialEventPermit> all() {
        return RxPagingUtil.allPages(offset -> {
            final SoqlQuery query = new SoqlQueryBuilder()
                    .addSelectPhrases(Arrays.asList("permit_id", "permit_name_full", "facility_type", "start_date", "end_date"))
                    .setOffset(offset)
                    .setLimit(limit)
                    .build();
            return consumer.query(resourceID, query, PERMITS_LIST_TYPE);
        });
    }
}
