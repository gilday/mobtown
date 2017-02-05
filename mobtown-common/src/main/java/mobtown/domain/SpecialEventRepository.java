package mobtown.domain;

import io.reactivex.Observable;
import org.jvnet.hk2.annotations.Contract;

import java.util.Optional;

/**
 * Repository which provides {@link SpecialEvent} data
 */
@Contract
public interface SpecialEventRepository {

    void add(final SpecialEvent event);

    Observable<SpecialEvent> all();

    Optional<SpecialEvent> get(final String permitID);
}
