package mobtown.ingest;

import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.model.soql.SoqlQuery;
import com.sun.jersey.api.client.GenericType;
import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Returns an observable stream of Arrest records from Baltimore Open Data
 */
public class ArrestsConsumer {

    protected static final GenericType<List<Arrest>> ARRESTS_LIST_TYPE = new GenericType<List<Arrest>>() {};

    private final String resourceID;
    private final int limit;
    private final Soda2Consumer consumer;

    public ArrestsConsumer() {
        this("icjs-e3jg", 10, Soda2Consumer.newConsumer("https://data.baltimorecity.gov"));
    }

    public ArrestsConsumer(final String resourceID, final int limit, final Soda2Consumer consumer) {
        this.resourceID = resourceID;
        this.limit = limit;
        this.consumer = consumer;
    }

    /**
     * returns an observable stream of Arrest records which occurred on the given date
     * @param date date of the arrest
     */
    public Observable<Arrest> query(final LocalDate date) {
        final String iso8601 = date.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return RxPagingUtil.allPages(offset -> {
            final SoqlQuery query = new SoqlQueryBuilder()
                    .addSelectPhrases(Arrays.asList("arrestdate", "arrestlocation", "charge", "location_1", "name1"))
                    .setWhereClause(String.format("arrestdate='%s'", iso8601))
                    .setOffset(offset)
                    .setLimit(limit)
                    .build();
            return consumer.query(resourceID, query, ARRESTS_LIST_TYPE);
        });
    }
}
