package com.johnathangilday.ingest;

import com.johnathangilday.Pair;
import io.reactivex.Observable;

/**
 * Queries {@link SpecialEventPermit} data and associated {@link Arrest} data from Open Baltimore
 */
public class OpenBaltimoreConsumer {

    private final SpecialEventPermitsConsumer specialEventPermitsConsumer;
    private final ArrestsConsumer arrestsConsumer;

    public OpenBaltimoreConsumer(
            final SpecialEventPermitsConsumer specialEventPermitsConsumer,
            final ArrestsConsumer arrestsConsumer) {
        this.specialEventPermitsConsumer = specialEventPermitsConsumer;
        this.arrestsConsumer = arrestsConsumer;
    }

    public Observable<Pair<SpecialEventPermit, Observable<Arrest>>> all() {
        return specialEventPermitsConsumer.all().map(sep -> {
            final Observable<Arrest> arrests = arrestsConsumer.query(sep.start.toLocalDate());
            return Pair.of(sep, arrests);
        });
    }
}
