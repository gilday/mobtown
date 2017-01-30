package com.johnathangilday.models;

import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 * implementation of {@link SpecialEventRepository} backed by JPA
 */
@Named
public class SpecialEventRepositoryImpl implements SpecialEventRepository {

    private final EntityManager em;
    private final TypedQuery<SpecialEvent> allQuery;

    @Inject
    public SpecialEventRepositoryImpl(final EntityManager em) {
        this.em = em;
        final CriteriaQuery<SpecialEvent> criteria = em.getCriteriaBuilder().createQuery(SpecialEvent.class);
        criteria.from(SpecialEvent.class);
        allQuery = em.createQuery(criteria);
    }

    @Override
    public void add(final SpecialEvent event) {
        em.persist(event);
    }

    @Override
    public Observable<SpecialEvent> all() {
        // TODO performance
        return Observable.fromIterable(allQuery.getResultList());
    }
}
