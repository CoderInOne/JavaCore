package xunshan.net.protocol.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by eldorado on 16-12-14.
 */
public class EchoBaidu {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getByName("baidu.com"), 80);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.print("GET / HTTP/1.1\r\n");
        pw.print("Host: baidu.com\r\n");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String t;
        while ((t = br.readLine()) != null) System.out.println(t);
        br.close();
    }
}
