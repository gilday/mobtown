package mobtown.services.jaxrs;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JerseyEntityManagerFactory implements Factory<EntityManager> {

    private final CloseableService cs;
    private final EntityManagerFactory factory;

    @Inject
    public JerseyEntityManagerFactory(final CloseableService cs, final EntityManagerFactory factory) {
        this.cs = cs;
        this.factory = factory;
    }

    @Override
    public EntityManager provide() {
        final EntityManager em = factory.createEntityManager();
        cs.add(() -> dispose(em));
        return em;
    }

    @Override
    public void dispose(final EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
