package tp2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQuestion10 {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String jsonCreation = """
                {
                    "nom": "Nadia",
                    "numero": "555"
                }
                """;

        HttpRequest creation = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonCreation))
                .build();

        HttpResponse<String> reponseCreation = client.send(
                creation,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status creation : " + reponseCreation.statusCode());
        System.out.println("Reponse creation : " + reponseCreation.body());

        HttpRequest recherche = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Nadia"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> reponseRecherche = client.send(
                recherche,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status recherche : " + reponseRecherche.statusCode());
        System.out.println("Contact recherche : " + reponseRecherche.body());

        String jsonModification = """
                {
                    "nom": "Nadia",
                    "numero": "777"
                }
                """;

        HttpRequest modification = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonModification))
                .build();

        HttpResponse<String> reponseModification = client.send(
                modification,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status modification : " + reponseModification.statusCode());
        System.out.println("Reponse modification : " + reponseModification.body());

        HttpRequest suppression = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Nadia"))
                .header("Accept", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> reponseSuppression = client.send(
                suppression,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status suppression : " + reponseSuppression.statusCode());
        System.out.println("Reponse suppression : " + reponseSuppression.body());
    }
}
