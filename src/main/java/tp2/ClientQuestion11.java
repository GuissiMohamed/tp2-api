package tp2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQuestion11 {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String json = """
                {
                    "nom": "Yasmine",
                    "numero": "777"
                }
                """;

        HttpRequest creationJsonReceptionXml = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/xml")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> reponse1 = client.send(
                creationJsonReceptionXml,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("POST JSON / réponse XML");
        System.out.println("Status : " + reponse1.statusCode());
        System.out.println(reponse1.body());

        String xml = """
                <contact>
                    <nom>Bilal</nom>
                    <numero>888</numero>
                </contact>
                """;

        HttpRequest creationXmlReceptionJson = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet"))
                .header("Content-Type", "application/xml")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(xml))
                .build();

        HttpResponse<String> reponse2 = client.send(
                creationXmlReceptionJson,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("POST XML / réponse JSON");
        System.out.println("Status : " + reponse2.statusCode());
        System.out.println(reponse2.body());

        HttpRequest rechercheXml = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Bilal"))
                .header("Accept", "application/xml")
                .GET()
                .build();

        HttpResponse<String> reponse3 = client.send(
                rechercheXml,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("GET / réponse XML");
        System.out.println("Status : " + reponse3.statusCode());
        System.out.println(reponse3.body());

        HttpRequest rechercheJson = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/carnet/Yasmine"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> reponse4 = client.send(
                rechercheJson,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("GET / réponse JSON");
        System.out.println("Status : " + reponse4.statusCode());
        System.out.println(reponse4.body());
    }
}
