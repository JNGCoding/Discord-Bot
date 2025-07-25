package org.example.API_Interface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GeminiInterface {
    private final String Key;
    private final String Tokens;

    public GeminiInterface(String key, String tokens) {
        Key = key;
        Tokens = tokens;
    }

    public String Query(String question) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + Key;
            String payload = "{\"contents\": [{\"parts\":[{\"text\":\"" + question + "\"}]}],\"generationConfig\": {\"maxOutputTokens\": " + Tokens + "}}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200 || response.statusCode() == 301) {
                String jsonResponse = response.body();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

                return jsonObject
                        .getAsJsonArray("candidates")
                        .get(0)
                        .getAsJsonObject()
                        .getAsJsonObject("content")
                        .getAsJsonArray("parts")
                        .get(0)
                        .getAsJsonObject()
                        .get("text")
                        .getAsString();
            } else {
                return "[HTTP] GET... failed, error: " + response.statusCode();
            }

        } catch (RuntimeException | IOException | URISyntaxException | InterruptedException e) {
            return "Failed to connect. Error: " + e.getMessage();
        }
    }
}
