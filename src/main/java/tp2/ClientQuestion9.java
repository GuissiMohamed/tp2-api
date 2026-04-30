package tp2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQuestion9 {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest creation = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Tom/777"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> reponseCreation = client.send(
                creation,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status creation : " + reponseCreation.statusCode());
        System.out.println("Reponse creation : " + reponseCreation.body());

        HttpRequest rechercheAvant = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Tom"))
                .GET()
                .build();

        HttpResponse<String> reponseRechercheAvant = client.send(
                rechercheAvant,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Avant suppression : " + reponseRechercheAvant.body());

        HttpRequest suppression = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Tom/777"))
                .DELETE()
                .build();

        HttpResponse<String> reponseSuppression = client.send(
                suppression,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status suppression : " + reponseSuppression.statusCode());
        System.out.println("Reponse suppression : " + reponseSuppression.body());

        HttpRequest rechercheApres = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Tom"))
                .GET()
                .build();

        HttpResponse<String> reponseRechercheApres = client.send(
                rechercheApres,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Apres suppression : " + reponseRechercheApres.body());
    }
}
