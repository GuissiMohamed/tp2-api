package tp2;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/carnet")
public class Annuaire {

    private static Carnet carnet = new Carnet();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listerContactsTexte(@QueryParam("lettre") String lettre) {
        if (lettre != null) {
            List<String> noms = carnet.nomsCommencantPar(lettre);

            if (noms.isEmpty()) {
                return "Aucun contact";
            }

            StringBuilder resultat = new StringBuilder();

            for (String nom : noms) {
                resultat.append(nom).append("\n");
            }

            return resultat.toString();
        }

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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listerContacts(@QueryParam("lettre") String lettre) {
        if (lettre != null) {
            List<String> noms = carnet.nomsCommencantPar(lettre);
            return Response.ok(new ListeNoms(noms)).build();
        }

        if (carnet.getContacts().isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(carnet.getContacts()).build();
    }

    @GET
    @Path("/{nom}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response trouverContact(@PathParam("nom") String nom) {
        Contact contact = carnet.chercherContact(nom);

        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(contact).build();
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
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response creerContact(Contact contact) {
        boolean ajoute = carnet.ajouterContact(contact);

        if (ajoute) {
            URI uri = URI.create("/carnet/" + contact.getNom());

            return Response.created(uri)
                    .entity(contact)
                    .build();
        }

        return Response.status(Response.Status.CONFLICT)
                .entity(contact)
                .build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modifierContact(Contact contact) {
        boolean modifie = carnet.modifierContact(contact);

        if (!modifie) {
            return Response.noContent().build();
        }

        return Response.ok(contact).build();
    }

    @DELETE
    @Path("/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public String supprimerContact(@PathParam("nom") String nom) {
        boolean supprime = carnet.supprimerContact(nom);

        if (supprime) {
            return "Contact " + nom + " supprimé";
        }

        return "Contact " + nom + " inconnu";
    }
}