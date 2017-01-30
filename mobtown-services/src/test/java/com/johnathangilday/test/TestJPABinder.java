package com.johnathangilday.test;

import com.johnathangilday.models.EntityManagerFactoryHK2Factory;
import com.johnathangilday.models.EntityManagerHK2Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * HK2 module which wires JPA in unit test configuration
 */
public class TestJPABinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(new EntityManagerFactoryHK2Factory("mobtown-test-pu"))
                .to(EntityManagerFactory.class)
                .in(Singleton.class);
        bindFactory(EntityManagerHK2Factory.class)
                .to(EntityManager.class);
    }
}
