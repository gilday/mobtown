package mobtown.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * HK2 module for wiring mobtown-services components
 */
public class MobtownBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(ObjectMapperFactory.class).to(ObjectMapper.class).in(Singleton.class);
    }
}