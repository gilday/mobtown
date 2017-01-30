package com.johnathangilday.jaxrs;

import com.johnathangilday.models.EntityManagerFactoryHK2Factory;
import com.johnathangilday.models.EntityManagerHK2Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * HK2 module which wires JPA in a per-Jersey-request configuration
 */
public class RequestScopedJPABinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(new EntityManagerFactoryHK2Factory("mobtown-pu"))
                .to(EntityManagerFactory.class)
                .in(Singleton.class);
        bindFactory(EntityManagerHK2Factory.class)
                .to(EntityManager.class)
                .in(RequestScoped.class);
    }
}
