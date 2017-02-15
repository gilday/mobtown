package mobtown.services.jaxrs;

import mobtown.domain.EntityManagerFactoryHK2Factory;
import mobtown.domain.JPADomainBinder;
import mobtown.services.ObjectMapperBinder;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.Properties;

/**
 * Use Jersey {@link ResourceConfig} to do application level component binding
 */
public class MobtownResourceConfig extends ResourceConfig {

    /**
     * @param properties property overrides for creating an EntityManagerFactory
     */
    public static MobtownResourceConfig create(final Properties properties) {
        final EntityManagerFactoryHK2Factory emfFactory = new EntityManagerFactoryHK2Factory(properties);
        final JPADomainBinder domain = JPADomainBinder.create(emfFactory);
        return new MobtownResourceConfig(domain, new JerseyEntityManagerBinder())
                .registerJPAProviders();
    }

    static MobtownResourceConfig createForIntegrationTest() {
        return new MobtownResourceConfig(JPADomainBinder.createForTest(), new JerseyEntityManagerBinder())
                .registerJPAProviders();
    }

    MobtownResourceConfig(final Binder... modules) {
        for (Binder module : modules) {
            register(module);
        }
        register(ObjectMapperBinder.get());
        register(SpecialEventsController.class);
        register(EventNotFoundExceptionMapper.class);
        register(ConstraintValidationExceptionMapper.class);
        register(ObjectMapperProvider.class);
    }

    /**
     * registers the {@link EntityManagerTransactionFilter}
     */
    private MobtownResourceConfig registerJPAProviders() {
        register(EntityManagerTransactionFilter.class);
        return this;
    }
}
