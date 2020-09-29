package no.kristiania.httpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public HttpServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket = serverSocket.accept();

        handleRequest(socket);
    }

    public static void main(String[] args) throws IOException {
        new HttpServer(8080);
    }

    private static void handleRequest(Socket socket) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=utf-8\r\n" +
                "Content-Length: 11\r\n" +
                "\r\n" +
                "Hello World!";

        socket.getOutputStream().write(response.getBytes());
    }
}