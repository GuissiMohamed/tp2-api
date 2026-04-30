package tp2;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Provider
public class FiltreAuthentBasic implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString("Authorization");

        if (authorization == null || !authorization.startsWith("Basic ")) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Header manquant ou invalide")
                            .build()
            );
            return;
        }

        String chaineBase64 = authorization.substring("Basic ".length());

        String chaineDecodee;

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(chaineBase64);
            chaineDecodee = new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                            .entity("Acces interdit")
                            .build()
            );
            return;
        }

        if (!chaineDecodee.equals("user:passwd")) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                            .entity("Acces interdit")
                            .build()
            );
        }
    }
}
