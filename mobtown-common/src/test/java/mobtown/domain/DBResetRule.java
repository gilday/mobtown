package mobtown.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class DBResetRule extends ExternalResource {

    private Logger log = LoggerFactory.getLogger(DBResetRule.class);

    private EntityManagerFactory factory;
    private EntityManager em;

    @Before
    public void before() {
        log.debug("creating entity manager");
        factory = Persistence.createEntityManagerFactory("mobtown-test-pu");
        em = factory.createEntityManager();
        em.getTransaction().begin();
        if (em.getTransaction().getRollbackOnly()) {
            em.getTransaction().rollback();
        } else {
            em.getTransaction().commit();
        }
    }

    @After
    public void after() {
        try {
            log.debug("tear down started, em=" + em);
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
                em.getTransaction().commit();
            } else if (!em.getTransaction().getRollbackOnly()) {
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
            em.close();
            log.debug("tear down complete, em=" + em);
        } catch (final RuntimeException ex) {
            log.error("tear down failed", ex);
            throw ex;
        }
        factory.close();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}