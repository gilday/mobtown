package mobtown.domain;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.validation.*;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialEventsTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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

    @Test
    public void it_uses_javax_validation_annotations() {
        final SpecialEvent event = new SpecialEvent("foo", "", "", LocalDateTime.now(), LocalDateTime.now());
        final Set<ConstraintViolation<SpecialEvent>> constraints = validator.validate(event);
        assertThat(constraints).hasSize(2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void it_throws_constraint_exception_on_save_invalid_event() throws Throwable {
        final SpecialEvent event = new SpecialEvent("", "", "", LocalDateTime.of(2001, 12, 1, 0, 0), LocalDateTime.of(2001, 12, 1, 0, 0));
        em.getTransaction().begin();
        em.persist(event);
        try {
            em.getTransaction().commit();
        } catch (final RollbackException re) {
            // unwrap RollbackException to see if it was caused by ConstraintViolationException
            throw re.getCause();
        }
    }
}
