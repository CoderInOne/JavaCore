package xunshan.net.protocol.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eldorado on 17-3-21.
 */
public class LastModified {
    public static void main(String[] args) {
        query("http://localhost:8442/hi.txt");
    }

    public static void query(final String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            final long lastModified = conn.getLastModified();
            System.out.println(lastModified);
            conn.disconnect();

            new Thread(() -> {
                long curlm = lastModified;
                long newlm;
                while (true) {
                    HttpURLConnection tmp = null;
                    try {
                        tmp = (HttpURLConnection) new URL(url).openConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    newlm = tmp.getLastModified();
                    if (newlm == 0 || curlm == newlm) {
                        continue;
                    } else {
                        curlm = newlm;
                        System.out.println("new change:" + newlm);
                    }

                    sleep(1 * 1000);
                    System.out.println("wait for 1 sec");
                    tmp.disconnect();
                }
            }).start();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
