package com.johnathangilday.models;

import io.reactivex.Observable;

/**
 * Repository which provides {@link SpecialEvent} data
 */
public interface SpecialEventRepository {

    void add(final SpecialEvent event);

    Observable<SpecialEvent> all();
}
