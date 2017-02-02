package mobtown.ingest;

import io.reactivex.Observable;
import mobtown.Pair;
import mobtown.domain.SpecialEvent;
import mobtown.ingest.test.FakeData;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenBaltimoreIngestTests {

    private OpenBaltimoreIngest ingest;
    private FakeSpecialEventDatabase database;

    @Before
    public void before() {
        database = new FakeSpecialEventDatabase();
        ingest = new OpenBaltimoreIngest(database, null /* consumer */, 0);
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
        assertThat(database.events).hasSize(1);
        final SpecialEvent persisted = database.events.iterator().next();
        assertThat(persisted.getId()).isEqualTo(event.id);
        assertThat(persisted.getName()).isEqualTo(event.name);
        assertThat(persisted.getType()).isEqualTo(event.type);
        assertThat(persisted.getStart()).isEqualTo(event.start);
        assertThat(persisted.getEnd()).isEqualTo(event.end);
        assertThat(persisted.getArrests()).hasSize(1);
    }

    private static class FakeSpecialEventDatabase implements SpecialEventDatabase {

        private final HashSet<SpecialEvent> events = new HashSet<>();

        @Override
        public void save(final SpecialEvent event) {
            events.add(event);
        }
    }
}
