package mobtown.domain;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Optional;

/**
 * implementation of {@link SpecialEventRepository} backed by JPA
 */
@Named
public class SpecialEventRepositoryImpl implements SpecialEventRepository {

    private static final Logger log = LoggerFactory.getLogger(SpecialEventRepositoryImpl.class);

    private final EntityManager em;
    private final TypedQuery<SpecialEvent> allQuery;
    private final TypedQuery<SpecialEventSummary> summaryQuery;

    @Inject
    public SpecialEventRepositoryImpl(final EntityManager em) {
        this.em = em;
        final CriteriaQuery<SpecialEvent> criteria = em.getCriteriaBuilder().createQuery(SpecialEvent.class);
        criteria.from(SpecialEvent.class);
        allQuery = em.createQuery(criteria);
        summaryQuery = em.createQuery("" +
                "select new mobtown.domain.SpecialEventSummary(se.id, se.name, se.type, se.start, se.end, count(a.location)) " +
                "from SpecialEvent se left join se.arrests a " +
                "group by se.id", SpecialEventSummary.class);
    }

    @Override
    public void add(final SpecialEvent event) {
        em.persist(event);
    }

    @Override
    public Observable<SpecialEvent> all() {
        return Observable.fromIterable(allQuery.getResultList());
    }

    @Override
    public Optional<SpecialEvent> get(final String permitID) {
        return Optional.ofNullable(em.find(SpecialEvent.class, permitID));
    }

    @Override
    public Observable<SpecialEventSummary> summaries() {
        return Observable.fromIterable(summaryQuery.getResultList());
    }
}
