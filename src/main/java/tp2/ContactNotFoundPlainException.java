package tp2;

public class ContactNotFoundPlainException extends RuntimeException {

    public ContactNotFoundPlainException() {
        super("Contact inconnu");
    }
}