package tp2;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Contact {

    private String nom;
    private List<String> numeros;

    public Contact() {
        this.numeros = new ArrayList<>();
    }

    public Contact(String nom, String numero) {
        this.nom = nom;
        this.numeros = new ArrayList<>();
        this.numeros.add(numero);
    }

    public Contact(String nom, List<String> numeros) {
        this.nom = nom;
        this.numeros = numeros;
    }

    public String getNom() {
        return nom;
    }

    public List<String> getNumeros() {
        return numeros;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumeros(List<String> numeros) {
        this.numeros = numeros;
    }

    public String getNumero() {
        if (numeros == null || numeros.isEmpty()) {
            return "";
        }

        return numeros.get(0);
    }

    public void setNumero(String numero) {
        if (numeros == null) {
            numeros = new ArrayList<>();
        }

        if (numeros.isEmpty()) {
            numeros.add(numero);
        } else {
            numeros.set(0, numero);
        }
    }

    public void ajouterNumero(String numero) {
        if (numeros == null) {
            numeros = new ArrayList<>();
        }

        numeros.add(numero);
    }

    @Override
    public String toString() {
        return nom + " : " + numeros;
    }
}