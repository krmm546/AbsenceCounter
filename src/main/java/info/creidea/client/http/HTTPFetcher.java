package info.creidea.client.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HTTPFetcher {
    public Optional<String> fetch(String url) {
        final var client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        final var request = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(response.body());
    }
}
