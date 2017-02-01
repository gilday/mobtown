package mobtown.ingest;

import mobtown.Pair;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Queries {@link SpecialEventPermit} data and associated {@link Arrest} data from Open Baltimore
 */
@Named
public class OpenBaltimoreConsumer {

    private final SpecialEventPermitsConsumer specialEventPermitsConsumer;
    private final ArrestsConsumer arrestsConsumer;

    @Inject
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
