package tp2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQuestion8 {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String xml = """
                <contact>
                    <nom>Sophie</nom>
                    <numero>999</numero>
                </contact>
                """;

        HttpRequest modification = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet"))
                .header("Content-Type", "application/xml")
                .PUT(HttpRequest.BodyPublishers.ofString(xml))
                .build();

        HttpResponse<String> reponseModification = client.send(
                modification,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status modification : " + reponseModification.statusCode());
        System.out.println("Reponse modification : " + reponseModification.body());

        HttpRequest recherche = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Sophie"))
                .GET()
                .build();

        HttpResponse<String> reponseRecherche = client.send(
                recherche,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status recherche : " + reponseRecherche.statusCode());
        System.out.println("Nouveau numero : " + reponseRecherche.body());

        String xmlInconnu = """
                <contact>
                    <nom>Inconnu</nom>
                    <numero>000</numero>
                </contact>
                """;

        HttpRequest modificationInconnue = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet"))
                .header("Content-Type", "application/xml")
                .PUT(HttpRequest.BodyPublishers.ofString(xmlInconnu))
                .build();

        HttpResponse<String> reponseInconnue = client.send(
                modificationInconnue,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status modification contact inconnu : " + reponseInconnue.statusCode());
        System.out.println("Body contact inconnu : " + reponseInconnue.body());
    }
}
