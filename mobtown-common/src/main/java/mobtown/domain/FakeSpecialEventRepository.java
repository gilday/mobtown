package mobtown.domain;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

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
}
