import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class MyHttps {

    /**
     * 覆盖java默认的证书验证
     */
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }};

    /**
     * 设置不验证主机
     */
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 信任所有
     *
     * @param connection
     * @return
     */
    private static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(sslSocketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }

    private static final TrustManager[] trustedCerts = new TrustManager[]{new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            System.out.println("clint:" + chain[0].getIssuerDN());
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

            TrustManagerFactory tmf = null;
            X509TrustManager x509Tm = null;
            try {
                tmf = TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
                // Using null here initialises the TMF with the default trust store.
                tmf.init((KeyStore) null);
                // Get hold of the default trust manager
                for (TrustManager tm : tmf.getTrustManagers()) {
                    if (tm instanceof X509TrustManager) {
                        x509Tm = (X509TrustManager) tm;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                tmf = TrustManagerFactory.getInstance("X509");
                tmf.init((KeyStore) null);
                for (TrustManager trustManager : tmf.getTrustManagers()) {
                    ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                }
            } catch (Exception e) {
                throw new CertificateException(e);
            }
            System.out.println("server dm1:" + chain[0].getIssuerDN());
            System.out.println("server dm1:" + chain[0].getSubjectDN());
            System.out.println("server dm:" + chain[0].getIssuerDN().getName());
            System.out.println("server dm:" + chain[0].getSubjectDN().getName());
            System.out.println("server pk:" + chain[0].getPublicKey());

            String pk = chain[0].getPublicKey().toString();
            System.out.println("pk:" + pk);
            try {
                String asB64 = Base64.getEncoder().encodeToString(pk.getBytes("utf-8"));
                System.out.println("64:" + asB64);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (!pk.equals("123")) {
                throw new CertificateException();
            }
        }
    }};

    private static SSLSocketFactory setTrustHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustedCerts, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(sslSocketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }


    public static void sendRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        SSLSocketFactory oldSocketFactory = null;
        HostnameVerifier oldHostnameVerifier = null;

        boolean useHttps = urlString.startsWith("https");
        if (useHttps) {
            HttpsURLConnection https = (HttpsURLConnection) connection;
            oldSocketFactory = setTrustHosts(https);
            oldHostnameVerifier = https.getHostnameVerifier();
            https.setHostnameVerifier(DO_NOT_VERIFY);
        }

        //设置请求方式
        connection.setRequestMethod("GET");
        //连接
        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            //得到响应流
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String str = reader.readLine();
            while (str != null) {
                System.out.println(str);
                str = reader.readLine();
            }
        }
    }

}
