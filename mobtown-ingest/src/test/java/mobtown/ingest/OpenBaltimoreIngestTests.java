package mobtown.ingest;

import mobtown.Pair;
import mobtown.domain.FakeSpecialEventRepository;
import mobtown.domain.SpecialEventRepository;
import mobtown.ingest.test.FakeData;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

public class OpenBaltimoreIngestTests {

    private OpenBaltimoreIngest ingest;
    private SpecialEventRepository repository;

    @Before
    public void before() {
        repository = new FakeSpecialEventRepository();
        ingest = new OpenBaltimoreIngest(repository, null /* consumer */);
    }

    @Test
    public void test_ingest() {
        // GIVEN an arbitrarily large stream of events and their arrests
        final SpecialEventPermit event = FakeData.openBaltimoreSpecialEventPermit();
        final Arrest arrest = FakeData.openBaltimoreArrest();
        final Observable<Arrest> arrests = Observable.just(arrest);
        final Pair<SpecialEventPermit, Observable<Arrest>> pair = Pair.of(event, arrests);
        final Observable<Pair<SpecialEventPermit, Observable<Arrest>>> observable = Observable.just(pair);

        // WHEN execute
        ingest.execute(observable);

        // THEN repository has one event record
        repository.all().test().assertValue(persisted ->
                persisted.getId().equals(event.id) &&
                        persisted.getName().equals(event.name) &&
                        persisted.getType().equals(event.type) &&
                        persisted.getStart().equals(event.start) &&
                        persisted.getEnd().equals(event.end) &&
                        persisted.getArrests().size() == 1);
    }
}
