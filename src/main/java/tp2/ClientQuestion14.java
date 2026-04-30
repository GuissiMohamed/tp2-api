package tp2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQuestion14 {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest contactExistant = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Sophie"))
                .header("Accept", "text/plain")
                .GET()
                .build();

        HttpResponse<String> reponseExistante = client.send(
                contactExistant,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Test contact existant");
        System.out.println("Status : " + reponseExistante.statusCode());
        System.out.println("Body : " + reponseExistante.body());

        HttpRequest contactInconnu = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Toto"))
                .header("Accept", "text/plain")
                .GET()
                .build();

        HttpResponse<String> reponseInconnue = client.send(
                contactInconnu,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Test contact inconnu");
        System.out.println("Status : " + reponseInconnue.statusCode());
        System.out.println("Body : " + reponseInconnue.body());
    }
}
