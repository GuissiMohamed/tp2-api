package tp2;

import java.util.ArrayList;
import java.util.List;

public class Carnet {

    private List<Contact> contacts;

    public Carnet() {
        contacts = new ArrayList<>();

        Contact sophie = new Contact("Sophie", "123");
        sophie.ajouterNumero("0612345678");

        Contact ali = new Contact("Ali", "456");
        ali.ajouterNumero("0699999999");

        Contact marie = new Contact("Marie", "789");
        marie.ajouterNumero("0677777777");

        contacts.add(sophie);
        contacts.add(ali);
        contacts.add(marie);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact chercherContact(String nom) {
        for (Contact contact : contacts) {
            if (contact.getNom().equalsIgnoreCase(nom.trim())) {
                return contact;
            }
        }

        return null;
    }

    public boolean ajouterContact(Contact contact) {
        Contact contactExistant = chercherContact(contact.getNom());

        if (contactExistant != null) {
            return false;
        }

        contacts.add(contact);
        return true;
    }

    public boolean modifierContact(Contact contact) {
        if (contacts.isEmpty()) {
            return false;
        }

        Contact contactExistant = chercherContact(contact.getNom());

        if (contactExistant == null) {
            return false;
        }

        contactExistant.setNumeros(contact.getNumeros());
        return true;
    }

    public boolean supprimerContact(String nom) {
        Contact contactExistant = chercherContact(nom);

        if (contactExistant == null) {
            return false;
        }

        contacts.remove(contactExistant);
        return true;
    }

    public List<String> nomsCommencantPar(String lettre) {
        List<String> noms = new ArrayList<>();

        if (lettre == null || lettre.isEmpty()) {
            return noms;
        }

        String debut = lettre.trim().toLowerCase();

        for (Contact contact : contacts) {
            if (contact.getNom().toLowerCase().startsWith(debut)) {
                noms.add(contact.getNom());
            }
        }

        return noms;
    }

    public String chercherNumeroParIndice(String nom, int indice) {
        Contact contact = chercherContact(nom);

        if (contact == null) {
            return "Contact inconnu";
        }

        if (contact.getNumeros() == null || contact.getNumeros().isEmpty()) {
            return "Aucun numéro";
        }

        if (indice < 0 || indice >= contact.getNumeros().size()) {
            return "Indice inconnu";
        }

        return contact.getNumeros().get(indice);
    }
}