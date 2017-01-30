package com.johnathangilday.test;

import com.johnathangilday.models.SpecialEvent;
import com.johnathangilday.models.SpecialEventRepository;
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
