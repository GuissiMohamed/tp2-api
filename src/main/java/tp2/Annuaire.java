package tp2;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carnet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Annuaire {

    private static Carnet carnet = new Carnet();

    @GET
    public Response listerContacts() {
        if (carnet.getContacts().isEmpty()) {
            return Response.ok("Liste vide").build();
        }

        return Response.ok(carnet.getContacts()).build();
    }

    @GET
    @Path("/{nom}")
    public Response trouverContact(@PathParam("nom") String nom) {
        Contact contact = carnet.chercherContact(nom);

        if (contact == null) {
            return Response.ok("Inconnu").build();
        }

        return Response.ok(contact).build();
    }

    @POST
    public Response creerContact(Contact contact) {
        boolean ajoute = carnet.ajouterContact(contact);

        if (ajoute) {
            return Response.status(Response.Status.CREATED)
                    .entity(contact)
                    .build();
        }

        return Response.ok("Contact " + contact.getNom() + " déjà existant").build();
    }

    @PUT
    public Response modifierContact(Contact contact) {
        boolean modifie = carnet.modifierContact(contact);

        if (!modifie) {
            return Response.noContent().build();
        }

        return Response.ok(contact).build();
    }

    @DELETE
    @Path("/{nom}")
    public Response supprimerContact(@PathParam("nom") String nom) {
        boolean supprime = carnet.supprimerContact(nom);

        if (supprime) {
            return Response.ok("Contact " + nom + " supprimé").build();
        }

        return Response.ok("Contact " + nom + " inconnu").build();
    }
}