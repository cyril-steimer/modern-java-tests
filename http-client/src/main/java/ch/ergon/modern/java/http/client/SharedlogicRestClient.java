package ch.ergon.modern.java.http.client;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;

public class SharedlogicRestClient {

    private final Host host;
    private final User user;

    private final CookieManager cookieManager = new CookieManager();

    private final HttpClient client = HttpClient.newBuilder()
            .sslContext(new TrustEverythingSslContextFactory().create())
            // Note: Setting an Authenticator would be the standard way, but we don't send a Basic Auth challenge
//                .authenticator(new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(user.username(), user.password().toCharArray());
//                    }
//                })
            .cookieHandler(cookieManager)
            .build();

    public SharedlogicRestClient(Host host, User user) {
        this.host = host;
        this.user = user;
    }

    public static void main(String[] args) throws Exception {
        var client = new SharedlogicRestClient(new Host(Scheme.HTTPS, "evng11", 443), new User("developer", "REDACTED"));
        var location = "/api/v1/datapoints/bacnet/device/location";
        var now = LocalDateTime.now();
        client.setDatapointValue(location, now.format(DateTimeFormatter.ISO_DATE_TIME));
        var response = client.getStringResponse(location);
        System.out.println(response);

        client.setDatapointValue(location, "Reset...");
    }

    public String getStringResponse(String path) throws IOException, InterruptedException {
        var builder = HttpRequest.newBuilder()
                .uri(URI.create(host.getFullUri(path)))
                .GET();
        authenticate(builder);
        var response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        var statusCode = response.statusCode();
        if (statusCode == 200) {
            return response.body();
        }
        throw new IOException("Unexpected response code: " + statusCode);
    }

    public void setDatapointValue(String path, String value) throws IOException, InterruptedException {
        var body = """
                {
                    "value": "%s"
                }
                """.formatted(value);
        var builder = HttpRequest.newBuilder()
                .uri(URI.create(host.getFullUri(path)))
                .PUT(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8));
        authenticate(builder);
        var response = client.send(builder.build(), HttpResponse.BodyHandlers.discarding());
        var statusCode = response.statusCode();
        if (statusCode != 200) {
            throw new IOException("Unexpected response code: " + statusCode);
        }
    }

    private String getBasicAuthHeader() {
        // Unlike the HTTP Client from Apache there seems to be no other way than setting the Basic Auth header ourselves
        var usernamePassword = user.username() + ":" + user.password();
        return "Basic " + Base64.getEncoder().encodeToString(usernamePassword.getBytes(StandardCharsets.UTF_8));
    }

    private void authenticate(HttpRequest.Builder requestBuilder) {
        // Note: Using cookies here only serves as an example (always using Basic Auth would be easier)
        Optional<String> xsrfToken = getCookieValue("XSRF-TOKEN");
        if (xsrfToken.isPresent()) {
            requestBuilder.header("X-XSRF-TOKEN", xsrfToken.get());
        } else {
            requestBuilder.header("Authorization", getBasicAuthHeader());
        }
    }

    private Optional<String> getCookieValue(String name) {
        return cookieManager.getCookieStore().getCookies().stream()
                .filter(cookie -> name.equals(cookie.getName()))
                .map(HttpCookie::getValue)
                .findAny();
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
