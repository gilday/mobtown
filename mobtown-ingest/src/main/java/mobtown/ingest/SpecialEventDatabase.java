package mobtown.ingest;

import mobtown.domain.SpecialEvent;

/**
 * abstracts the action of saving a {@link SpecialEvent} so that {@link OpenBaltimoreIngest} may
 * have the simplest possible dependencies for mocking
 */
interface SpecialEventDatabase {

    /**
     * saves the given {@link SpecialEvent} to the database
     */
    void save(final SpecialEvent event);
}