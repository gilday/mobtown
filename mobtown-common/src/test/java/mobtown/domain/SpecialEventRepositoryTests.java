package mobtown.domain;

import mobtown.test.FakeData;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;

public class SpecialEventRepositoryTests {

    @Rule
    public final DBResetRule reset = new DBResetRule();

    private EntityManager em;
    private SpecialEventRepositoryImpl repository;
    private SpecialEvent event;

    @Before
    public void before() {
        em = reset.getEntityManager();
        repository = new SpecialEventRepositoryImpl(em);

        // add a SpecialEvent to the repository
        event = FakeData.specialEvent();
        em.getTransaction().begin();
        repository.add(event);
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void it_persists_special_events() {
        // WHEN query for all events
        em.getTransaction().begin();
        final Observable<SpecialEvent> all = repository.all();

        // THEN the one expected event is returned
        all.test().assertValue(persisted ->
                persisted.getId().equals(event.getId()) &&
                persisted.getName().equals(event.getName()) &&
                persisted.getStart().equals(event.getStart()) &&
                persisted.getEnd().equals(event.getEnd()) &&
                persisted.getType().equals(event.getType()) &&
                persisted.getArrestsCount() == event.getArrestsCount() &&
                persisted.getArrests().iterator().next().equals(event.getArrests().iterator().next()));
        em.getTransaction().commit();
    }

    @Test
    public void it_retrieves_special_event_summaries() {
        // WHEN query for all events
        em.getTransaction().begin();
        final Observable<SpecialEventSummary> all = repository.summaries();

        // THEN the one expected event is returned
        all.test().assertValue(persisted ->
                persisted.permitID.equals(event.getId()) &&
                        persisted.name.equals(event.getName()) &&
                        persisted.start.equals(event.getStart()) &&
                        persisted.end.equals(event.getEnd()) &&
                        persisted.type.equals(event.getType()) &&
                        persisted.arrestsCount == event.getArrestsCount());
        em.getTransaction().commit();
    }
}
