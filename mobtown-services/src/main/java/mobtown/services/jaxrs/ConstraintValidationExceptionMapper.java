package mobtown.services.jaxrs;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps javax.validation constraint violations to HTTP 400 Bad Request
 */
@Provider
public class ConstraintValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        // TODO include validation error messages in the response body for debugging
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
