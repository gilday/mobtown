package mobtown.ingest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * HK2 binder for ingest components
 */
public class IngestBinder extends AbstractBinder {

    private final int max;

    /**
     * @param max maximum number of special events to ingest
     */
    IngestBinder(final int max) {
        this.max = max;
    }

    @Override
    protected void configure() {
        bind(max).to(Integer.class).named("ingest.max");
        bind(SpecialEventDatabaseImpl.class).to(SpecialEventDatabase.class);
        bindAsContract(OpenBaltimoreIngest.class);
        bindAsContract(OpenBaltimoreConsumer.class);
        bindAsContract(ArrestsConsumer.class);
        bindAsContract(SpecialEventPermitsConsumer.class);
    }
}
