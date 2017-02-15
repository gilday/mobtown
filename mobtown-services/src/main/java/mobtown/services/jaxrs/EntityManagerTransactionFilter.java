package mobtown.services.jaxrs;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Opens and closes transactions for the request
 */
@Provider
public class EntityManagerTransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private final EntityManager em;

    @Inject
    public EntityManagerTransactionFilter(final EntityManager em) {
        this.em = em;
    }

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
        if (em.getTransaction().isActive()) {
            try {
                if (em.getTransaction().getRollbackOnly()) {
                    em.getTransaction().rollback();
                } else {
                    em.getTransaction().commit();
                }
            } catch (RollbackException rbe) {
                final Throwable cause = rbe.getCause();
                if (cause instanceof Error) {
                    throw (Error) cause;
                } else if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                } else {
                    throw new RuntimeException(cause);
                }
            } finally {
                if (em.isOpen()) {
                    em.close();
                }
            }
        } else if (em.isOpen()) {
            em.close();
        }
    }
}
