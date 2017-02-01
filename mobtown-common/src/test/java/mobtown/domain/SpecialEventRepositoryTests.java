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

    @Before
    public void before() {
        em = reset.getEntityManager();
        repository = new SpecialEventRepositoryImpl(em);
    }

    @Test
    public void it_persists_special_events() {
        // GIVEN special event added to the repository
        final SpecialEvent event = FakeData.specialEvent();
        em.getTransaction().begin();
        repository.add(event);
        em.getTransaction().commit();
        em.clear();

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
                persisted.getArrests().size() == event.getArrests().size() &&
                persisted.getArrests().get(0).equals(event.getArrests().get(0)));
        em.getTransaction().commit();
    }
}
