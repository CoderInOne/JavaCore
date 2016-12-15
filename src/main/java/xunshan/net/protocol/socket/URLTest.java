package xunshan.net.protocol.socket;

import java.io.IOException;
import java.net.URL;

/**
 * URL自动封装HTTP协议族
 */
public class URLTest {
    public static void main(String[] args) throws IOException {
        Object content = new URL("https://" + "www.baidu.com" + "/").getContent();
    }
}
