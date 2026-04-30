package tp2;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ListeNoms {

    private List<String> noms;

    public ListeNoms() {
        this.noms = new ArrayList<>();
    }

    public ListeNoms(List<String> noms) {
        this.noms = noms;
    }

    public List<String> getNoms() {
        return noms;
    }

    public void setNoms(List<String> noms) {
        this.noms = noms;
    }
}
