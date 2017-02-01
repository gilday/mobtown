package mobtown.ingest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * HK2 binder for ingest components
 */
public class IngestBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindAsContract(OpenBaltimoreIngest.class);
        bindAsContract(OpenBaltimoreConsumer.class);
        bindAsContract(ArrestsConsumer.class);
        bindAsContract(SpecialEventPermitsConsumer.class);
    }
}
