package tp2;

import java.util.ArrayList;
import java.util.List;

public class Carnet {

    private List<Contact> contacts;

    public Carnet() {
        contacts = new ArrayList<>();

        contacts.add(new Contact("Sophie", "123"));
        contacts.add(new Contact("Ali", "456"));
        contacts.add(new Contact("Marie", "789"));
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

    public boolean ajouterContact(String nom, String numero) {
        Contact contactExistant = chercherContact(nom);

        if (contactExistant != null) {
            return false;
        }

        contacts.add(new Contact(nom, numero));
        return true;
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

        contactExistant.setNumero(contact.getNumero());
        return true;
    }
}
