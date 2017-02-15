package mobtown.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * HK2 binder for registering the Jackson {@link ObjectMapper}
 */
public class ObjectMapperBinder extends AbstractBinder {

    private static ObjectMapperBinder instance;

    public static ObjectMapperBinder get() {
        if (instance == null)
            instance = new ObjectMapperBinder();
        return instance;
    }

    @Override
    protected void configure() {
        bindFactory(ObjectMapperFactory.class).to(ObjectMapper.class).in(Singleton.class);
    }

    private ObjectMapperBinder() { }
}