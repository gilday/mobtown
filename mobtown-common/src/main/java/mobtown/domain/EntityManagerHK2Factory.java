package mobtown.domain;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * HK2 factory which creates an entity manager
 */
public class EntityManagerHK2Factory implements Factory<EntityManager> {

    private final EntityManagerFactory emf;

    @Inject
    public EntityManagerHK2Factory(final EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public EntityManager provide() {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    @Override
    public void dispose(final EntityManager em) {
        if (em.getTransaction().getRollbackOnly()) {
            em.getTransaction().rollback();
        } else if (em.getTransaction().isActive()){
            em.getTransaction().commit();
        }
        if (em.isOpen()) {
            em.close();
        }
    }
}
