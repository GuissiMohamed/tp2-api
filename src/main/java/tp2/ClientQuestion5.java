package tp2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQuestion5 {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest creation = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Nora/333"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> reponseCreation = client.send(
                creation,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status creation : " + reponseCreation.statusCode());
        System.out.println("URI retournee : " + reponseCreation.body());

        String uriContact = reponseCreation.body();

        HttpRequest recherche = HttpRequest.newBuilder()
                .uri(URI.create(uriContact))
                .GET()
                .build();

        HttpResponse<String> reponseRecherche = client.send(
                recherche,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status recherche : " + reponseRecherche.statusCode());
        System.out.println("Numero du contact : " + reponseRecherche.body());
    }
}
