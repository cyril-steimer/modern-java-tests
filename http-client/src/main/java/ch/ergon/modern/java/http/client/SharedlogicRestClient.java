package ch.ergon.modern.java.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SharedlogicRestClient {

    private final Host host;
    private final User user;

    public SharedlogicRestClient(Host host, User user) {
        this.host = host;
        this.user = user;
    }

    public static void main(String[] args) throws Exception {
        var client = new SharedlogicRestClient(new Host(Scheme.HTTPS, "evng11", 443), new User("developer", "REDACTED"));
        var response = client.getStringResponse("/api/v1/datapoints/ip/address/host");
        System.out.println(response);
    }

    public String getStringResponse(String path) throws IOException, InterruptedException {
        var client = getHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create(host.getFullUri(path))).GET().header("Authorization", getBasicAuthHeader()).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        var statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            return response.body();
        }
        throw new IOException("Unexpected response code: " + statusCode);
    }

    private String getBasicAuthHeader() {
        // Unlike the HTTP Client from Apache there seems to be no other way than setting the Basic auth header ourselves
        var usernamePassword = user.username() + ":" + user.password();
        return "Basic " + Base64.getEncoder().encodeToString(usernamePassword.getBytes(StandardCharsets.UTF_8));
    }

    private HttpClient getHttpClient() {
        return HttpClient.newBuilder().sslContext(new TrustEverythingSslContextFactory().create())
                // Note: Setting an Authenticator would be the standard way, but we don't send a Basic Auth challenge
//                .authenticator(new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(user.username(), user.password().toCharArray());
//                    }
//                })
                .build();
    }

    public enum Scheme {
        HTTPS, HTTP
    }

    public record User(String username, String password) {
    }

    public record Host(Scheme scheme, String hostname, int port) {

        public String getFullUri(String path) {
            return scheme.name().toLowerCase() + "://" + hostname + ":" + port + path;
        }
    }
}
