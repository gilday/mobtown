package com.johnathangilday.ingest;

import com.johnathangilday.Pair;
import com.johnathangilday.models.SpecialEvent;
import com.johnathangilday.models.SpecialEventRepository;
import io.reactivex.Observable;

/**
 * Loads data from openbaltimore.org into the mobtown database
 */
public class OpenBaltimoreIngest {

    private final SpecialEventRepository repository;

    public OpenBaltimoreIngest(final SpecialEventRepository repository) {
        this.repository = repository;
    }

    public void execute(final Observable<Pair<SpecialEventPermit, Observable<Arrest>>> events) {
        events.map(pair -> {
            final SpecialEventPermit obEvent = pair.left;
            final SpecialEvent event = new SpecialEvent(obEvent.id, obEvent.name, obEvent.type, obEvent.start, obEvent.end);
            pair.right
                    .take(1000)
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
