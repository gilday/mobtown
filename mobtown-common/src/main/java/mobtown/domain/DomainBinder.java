package mobtown.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * HK2 binder for the domain services
 */
public class DomainBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(SpecialEventRepositoryImpl.class).to(SpecialEventRepository.class);
    }
}
