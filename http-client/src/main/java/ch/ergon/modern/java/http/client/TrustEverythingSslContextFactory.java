package ch.ergon.modern.java.http.client;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.net.Socket;
import java.security.cert.X509Certificate;

public class TrustEverythingSslContextFactory {

    // There are two options to disable hostname verification:
    // 1) Supply an X509ExtendedTrustManager (not an X509TrustManager) (https://stackoverflow.com/a/70741993)
    // 2) Set a system property (https://www.baeldung.com/java-httpclient-ssl or https://stackoverflow.com/a/53086587)
    private static final TrustManager TRUST_EVERY_CERTIFICATE = new X509ExtendedTrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) {
            // NOP. Trust everything
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) {
            // NOP. Trust everything
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
            // NOP. Trust everything
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
            // NOP. Trust everything
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            // NOP. Trust everything
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            // NOP. Trust everything
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    public SSLContext create() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{TRUST_EVERY_CERTIFICATE}, null);
            return context;
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected exception getting a SSLContext to trust every certificate", e);
        }
    }
}
