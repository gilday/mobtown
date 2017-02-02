package mobtown.ingest;

import mobtown.domain.SpecialEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * JPA implementation of {@link SpecialEventDatabase}
 */
public class SpecialEventDatabaseImpl implements SpecialEventDatabase {

    private static final Logger log = LoggerFactory.getLogger(SpecialEventDatabaseImpl.class);

    private final EntityManagerFactory factory;

    @Inject
    public SpecialEventDatabaseImpl(final EntityManagerFactory factory) {
        this.factory = factory;
    }

    /**
     * uses one {@link EntityManager} to save the given {@link SpecialEvent}
     */
    @Override
    public void save(final SpecialEvent event) {
        log.info("saving special event {} with {} arrests", event.getId(), event.getArrests().size());
        final EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        } catch (Exception e) {
            log.error("failed to save event " + event.getId(), e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().setRollbackOnly();
            }
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
