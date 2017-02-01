package mobtown.services.jaxrs;

import mobtown.domain.SpecialEvent;
import io.reactivex.Observable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class SpecialEventsController {

    @GET
    public Observable<SpecialEvent> all() {
        final SpecialEvent event = new SpecialEvent("foobar", "Sole of the City", "run", LocalDateTime.now(), LocalDateTime.now());
        return Observable.just(event);
    }
}
