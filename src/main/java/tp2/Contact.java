package tp2;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {

    private String nom;
    private String numero;

    public Contact() {
    }

    public Contact(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return nom + " : " + numero;
    }
}