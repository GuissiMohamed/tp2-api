package tp2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class Numero {

    private Carnet carnet;
    private String nom;
    private int indice;

    public Numero(Carnet carnet, String nom, int indice) {
        this.carnet = carnet;
        this.nom = nom;
        this.indice = indice;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String trouverNumero() {
        return carnet.chercherNumeroParIndice(nom, indice);
    }
}
