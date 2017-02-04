package mobtown.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * HK2 binder which provides a fake in-memory domain services for testing
 */
public class InMemoryDomainBinder extends AbstractBinder implements DomainBinder {
    @Override
    protected void configure() {
        bind(FakeSpecialEventRepository.class).to(SpecialEventRepository.class);
    }
}
