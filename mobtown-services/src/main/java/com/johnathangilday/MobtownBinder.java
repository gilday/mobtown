package com.johnathangilday;

import com.johnathangilday.ingest.ArrestsConsumer;
import com.johnathangilday.ingest.OpenBaltimoreConsumer;
import com.johnathangilday.ingest.OpenBaltimoreIngest;
import com.johnathangilday.ingest.SpecialEventPermitsConsumer;
import com.johnathangilday.models.SpecialEventRepository;
import com.johnathangilday.models.SpecialEventRepositoryImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * HK2 module for wiring mobtown components
 */
public class MobtownBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(SpecialEventRepositoryImpl.class).to(SpecialEventRepository.class);
        bindAsContract(OpenBaltimoreIngest.class);
        bindAsContract(OpenBaltimoreConsumer.class);
        bindAsContract(ArrestsConsumer.class);
        bindAsContract(SpecialEventPermitsConsumer.class);
        bindFactory(ObjectMapperFactory.class).to(ObjectMapper.class).in(Singleton.class);
    }

}