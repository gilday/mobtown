package mobtown.services.jaxrs;

import mobtown.domain.SpecialEventRepository;
import mobtown.services.dto.SpecialEventDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class SpecialEventsController {

    private final SpecialEventRepository repository;

    @Inject
    public SpecialEventsController(final SpecialEventRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<SpecialEventDTO> all() {
        return repository.all().map(SpecialEventDTO::fromEntity).toList().blockingGet();
    }
}
