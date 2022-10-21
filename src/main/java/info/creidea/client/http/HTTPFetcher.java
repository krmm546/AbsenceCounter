package info.creidea.client.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HTTPFetcher {
    /**
     * 指定されたURLのHTMLを取得する
     * @param url URL
     * @return HTML
     */
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
