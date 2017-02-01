package mobtown.ingest;

import mobtown.Pair;
import mobtown.domain.SpecialEvent;
import mobtown.domain.SpecialEventRepository;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Loads data from openbaltimore.org into the mobtown database
 */
@Named
public class OpenBaltimoreIngest {

    private final SpecialEventRepository repository;
    private final OpenBaltimoreConsumer consumer;

    @Inject
    public OpenBaltimoreIngest(final SpecialEventRepository repository, final OpenBaltimoreConsumer consumer) {
        this.repository = repository;
        this.consumer = consumer;
    }

    public void execute() {
        final Observable<Pair<SpecialEventPermit, Observable<Arrest>>> all = consumer.all();
        execute(all);
    }

    void execute(final Observable<Pair<SpecialEventPermit, Observable<Arrest>>> events) {
        events.map(pair -> {
            final SpecialEventPermit obEvent = pair.left;
            final SpecialEvent event = new SpecialEvent(obEvent.id, obEvent.name, obEvent.type, obEvent.start, obEvent.end);
            pair.right
                    .take(64)
                    .forEach(arrest -> event.addArrest(
                            arrest.id,
                            arrest.intersection,
                            arrest.neighborhood,
                            arrest.timestamp,
                            "(" + arrest.location.latitude + "," + arrest.location.longitude + ")"));
            return event;
        }).forEach(repository::add);
    }
}
