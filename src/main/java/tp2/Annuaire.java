package tp2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("/carnet")
public class Annuaire {

    private static Carnet carnet = new Carnet();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listerContacts() {
        if (carnet.getContacts().isEmpty()) {
            return "Liste vide";
        }

        StringBuilder resultat = new StringBuilder();

        for (Contact contact : carnet.getContacts()) {
            resultat.append(contact.toString()).append("\n");
        }

        return resultat.toString();
    }

    @GET
    @Path("/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public String trouverNumero(@PathParam("nom") String nom) {
        Contact contact = carnet.chercherContact(nom);

        if (contact == null) {
            return "Inconnu";
        }

        return contact.getNumero();
    }

    @POST
    @Path("/{nom}/{numero}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response creerContact(@PathParam("nom") String nom,
                                 @PathParam("numero") String numero,
                                 @Context UriInfo uriInfo) {
        boolean ajoute = carnet.ajouterContact(nom, numero);

        if (ajoute) {
            URI uri = uriInfo.getBaseUriBuilder()
                    .path(Annuaire.class)
                    .path(nom)
                    .build();

            return Response.created(uri)
                    .entity(uri.toString())
                    .build();
        }

        return Response.ok("Contact " + nom + " déjà existant").build();
    }
}
