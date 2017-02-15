package mobtown.services.jaxrs;

import mobtown.domain.SpecialEventRepository;
import mobtown.services.EventNotFoundException;
import mobtown.services.dto.SpecialEventDTO;
import mobtown.services.dto.SpecialEventSummaryDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/api/events")
@Produces(MediaType.APPLICATION_JSON)
public class SpecialEventsController {

    private final SpecialEventRepository repository;

    @Inject
    public SpecialEventsController(final SpecialEventRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<SpecialEventSummaryDTO> all() {
        // TODO replace naive implementation with a streaming implementation
        return repository.summaries()
                .map(SpecialEventSummaryDTO::fromModel)
                .toList()
                .blockingGet();
    }

    @GET
    @Path("/{permitID}")
    public SpecialEventDTO arrests(@PathParam("permitID") final String permitID) {
        final Optional<SpecialEventDTO> event = repository.get(permitID).map(SpecialEventDTO::fromModel);
        if (event.isPresent()) {
            return event.get();
        }
        throw new EventNotFoundException(permitID);
    }
}
