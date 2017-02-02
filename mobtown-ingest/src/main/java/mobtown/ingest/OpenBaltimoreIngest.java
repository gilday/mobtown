package mobtown.ingest;

import io.reactivex.Observable;
import mobtown.Pair;
import mobtown.domain.SpecialEvent;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Loads data from openbaltimore.org into the mobtown database
 */
@Named
public class OpenBaltimoreIngest {

    private final SpecialEventDatabase database;
    private final OpenBaltimoreConsumer consumer;
    private final int max;

    /**
     * @param max maximum number of special event items to ingest
     */
    @Inject
    public OpenBaltimoreIngest(
            final SpecialEventDatabase database,
            final OpenBaltimoreConsumer consumer,
            @Named("ingest.max") final int max) {
        this.database = database;
        this.consumer = consumer;
        this.max = max;
    }

    void execute() {
        final Observable<Pair<SpecialEventPermit, Observable<Arrest>>> all = consumer.all().take(max);
        execute(all);
    }

    void execute(final Observable<Pair<SpecialEventPermit, Observable<Arrest>>> events) {
        events.flatMap(pair -> {
            final SpecialEventPermit obEvent = pair.left;
            final Observable<Arrest> arrests = pair.right;

            final SpecialEvent event = new SpecialEvent(obEvent.id, obEvent.name, obEvent.type, obEvent.start, obEvent.end);
            return arrests
                    .filter(arrest -> arrest.location != null)
                    .take(64)
                    .reduce(event, (specialEvent, arrest) -> {
                        event.addArrest(
                                arrest.intersection,
                                arrest.neighborhood,
                                arrest.timestamp,
                                "(" + arrest.location.latitude + "," + arrest.location.longitude + ")");
                        return event;
                    }).toObservable();
        }).forEach(database::save);
    }
}