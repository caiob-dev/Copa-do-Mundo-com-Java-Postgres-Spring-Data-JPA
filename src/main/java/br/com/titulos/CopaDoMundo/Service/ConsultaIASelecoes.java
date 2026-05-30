package br.com.titulos.CopaDoMundo.Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaIASelecoes {

    public static String obterInformacao(String texto)
            throws IOException, InterruptedException {

        String body = """
        {
          "model": "llama3",
          "prompt": "Me fale sobre a seleção na linguagem português brasil: %s",
          "stream": false
        }
        """.formatted(texto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString((response.body())).getAsJsonObject();

        return json.get("response").getAsString();
    }
}

