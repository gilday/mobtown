package mobtown.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialEventsTest {

    @Rule
    public final DBResetRule reset = new DBResetRule();

    private EntityManager em;

    @Before
    public void before() {
        em = reset.getEntityManager();
    }

    @Test
    public void save_special_events() {
        final SpecialEvent event = new SpecialEvent("foo", "bar", "baz", LocalDateTime.now(), LocalDateTime.now());
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();

        em.getTransaction().begin();
        final SpecialEvent persisted = em.find(SpecialEvent.class, "foo");
        assertThat(persisted.getId()).isEqualTo("foo");
        assertThat(persisted.getName()).isEqualTo("bar");
        assertThat(persisted.getType()).isEqualTo("baz");
        System.out.println(persisted);
        em.getTransaction().commit();
    }
}
