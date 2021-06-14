import com.oracle.tools.packager.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        try {
            String urlStr = "https://www.baidu.com/";
            urlStr = "https://www.openaboc.com/";
            MyHttps.sendRequest(urlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
