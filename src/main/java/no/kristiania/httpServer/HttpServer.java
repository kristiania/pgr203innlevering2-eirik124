package no.kristiania.httpServer;

import java.io.IOException;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("urlecho.appspot.com", 80);

        String request = "GET /echo?status=200&body=Hello%20world! HTTP/1.1\r\n" +
                "Host: urlecho.appspot.com\r\n\r\n";
        socket.getOutputStream().write(request.getBytes());

        int c;
        while ((c = socket.getInputStream().read()) != -1) {
            System.out.print((char)c);
        }
    }
}
