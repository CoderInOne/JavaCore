package xunshan.net.protocol.ssl;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * SSL Server this
 * keystore is in /resources, not run EchoServer/Client in IDE
 * Copy CS code and keystore to a same file
 * run it by:
 *   javac...
 *   java -Djavax.net.ssl.keyStore=mySrvKeystore -Djavax.net.ssl.keyStorePassword=eldorado EchoServer
 *   java -Djavax.net.ssl.trustStore=mySrvKeystore -Djavax.net.ssl.keyStorePassword=eldorado EchoClient
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        Socket sslSocket = getSslServerSocket();

        InputStream inputstream = sslSocket.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

        String string = null;
        while ((string = bufferedreader.readLine()) != null) {
            System.out.println(string);
            System.out.flush();
        }
    }

    private static Socket getSslServerSocket() throws IOException {
        SSLServerSocketFactory sslSocketFactory =
                (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslserversocket =
                (SSLServerSocket) sslSocketFactory.createServerSocket(9999);
        return sslserversocket.accept();
    }
}
