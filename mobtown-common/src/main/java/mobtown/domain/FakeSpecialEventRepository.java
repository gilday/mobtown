package mobtown.domain;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeSpecialEventRepository implements SpecialEventRepository {

    private final List<SpecialEvent> events = new ArrayList<>();

    @Override
    public void add(final SpecialEvent event) {
        events.add(event);
    }

    @Override
    public Observable<SpecialEvent> all() {
        return Observable.fromIterable(events);
    }

    @Override
    public Optional<SpecialEvent> get(final String permitID) {
        return events.stream().filter(e -> e.getId().equals(permitID)).findFirst();
    }

    @Override
    public Observable<SpecialEventSummary> summaries() {
        return all().map(SpecialEventSummary::fromModel);
    }
}
