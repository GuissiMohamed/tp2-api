package tp2;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ContactNotFoundException extends WebApplicationException {

    @Override
    public Response getResponse() {
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.TEXT_PLAIN)
                .entity("Ce contact est inconnu")
                .build();
    }
}
