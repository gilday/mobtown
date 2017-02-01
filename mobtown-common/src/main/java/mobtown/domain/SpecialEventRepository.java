package mobtown.domain;

import io.reactivex.Observable;
import org.jvnet.hk2.annotations.Contract;

/**
 * Repository which provides {@link SpecialEvent} data
 */
@Contract
public interface SpecialEventRepository {

    void add(final SpecialEvent event);

    Observable<SpecialEvent> all();
}
