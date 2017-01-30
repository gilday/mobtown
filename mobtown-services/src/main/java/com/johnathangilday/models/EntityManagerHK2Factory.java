package com.johnathangilday.models;

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
        return emf.createEntityManager();
    }

    @Override
    public void dispose(final EntityManager em) {
        em.close();
    }
}
