package tp2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
}
