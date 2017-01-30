package com.johnathangilday.jaxrs;

import com.johnathangilday.ingest.OpenBaltimoreIngest;
import com.johnathangilday.models.SpecialEventRepository;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

import javax.inject.Inject;

/**
 * Initializes the mobtown database on application startup
 */
public class DatabaseInitializer implements ContainerLifecycleListener {

    private final SpecialEventRepository repository;
    private final OpenBaltimoreIngest ingest;

    @Inject
    public DatabaseInitializer(final SpecialEventRepository repository, final OpenBaltimoreIngest ingest) {
        this.repository = repository;
        this.ingest = ingest;
    }

    @Override
    public void onStartup(final Container container) {
        if (repository.all().isEmpty().blockingGet())
            ingest.execute();
    }

    @Override
    public void onReload(final Container container) { }

    @Override
    public void onShutdown(final Container container) { }
}
