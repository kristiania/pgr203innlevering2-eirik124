package no.kristiania.httpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {

    private File documentRoot;
    private final List<String> teamNames = new ArrayList<>();

    public HttpServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        new Thread(() -> {
            while(true) {
                try {
                    Socket socket = serverSocket.accept();
                    handleRequest(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleRequest(Socket clientSocket) throws IOException {
        String requestLine = HttpClient.readLine(clientSocket);
        System.out.println(requestLine);

        String requestMethod = requestLine.split(" ")[0];
        String requestTarget = requestLine.split(" ")[1];

        int questionPos = requestTarget.indexOf('?');

        String requestPath = questionPos != -1 ? requestTarget.substring(0, questionPos) : requestTarget;

        if (requestMethod.equals("POST")) {
            HttpMessage requestMessage = new HttpMessage(requestLine);
            requestMessage.readHeaders(clientSocket);

            int contentLength = Integer.parseInt(requestMessage.getHeader("Content-Length"));
            StringBuilder body = new StringBuilder();
            for (int i = 0; i < contentLength; i++) {
                body.append((char)clientSocket.getInputStream().read());
            }
            QueryString requestForm = new QueryString(body.toString());
            teamNames.add(requestForm.getParameter("name"));

            HttpMessage responseMessage = new HttpMessage("HTTP/1.1 200 OK");
            responseMessage.write(clientSocket);
        } else if (requestPath.equals("/echo")) {
            handleEchoRequest(clientSocket, requestTarget, questionPos);
        } else {
            File targetFile = new File(documentRoot, requestTarget);

            if (!targetFile.exists()) {
                writeResponse(clientSocket, "404", requestTarget + " not found");
            } else {
                String statusCode = "200";
                String contentType;

                if (targetFile.getName().endsWith(".html")) {
                    contentType = "text/html";
                } else if (targetFile.getName().endsWith(".txt")){
                    contentType = "text/plain";
                } else {
                    contentType = "text/css";
                }

                String responseHeaders = "HTTP/1.1 " + statusCode + " OK\r\n" +
                        "Content-Length: " + targetFile.length() + "\r\n" +
                        "Content-Type: " + contentType + "\r\n" +
                        "\r\n";

                clientSocket.getOutputStream().write(responseHeaders.getBytes());
                try (FileInputStream inputStream = new FileInputStream(targetFile)) {
                    inputStream.transferTo(clientSocket.getOutputStream());
                }
            }
        }
    }

    private void writeResponse(Socket clientSocket, String statusCode, String body) throws IOException {
        String response = "HTTP/1.1 " + statusCode + " OK\r\n" +
                "Content-Length: " + body.length() + "\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                body;

        clientSocket.getOutputStream().write(response.getBytes());
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(8080);
        System.out.print("Server is running on: localhost:8080 \n unless port is changed above");
        server.setDocumentRoot(new File("src/main/resources"));
    }

    private void handleEchoRequest(Socket clientSocket, String requestTarget, int questionPos) throws IOException {
        String statusCode = "200";
        String body = "Hello <strong>World</strong>!";
        if (questionPos != -1) {
            QueryString queryString = new QueryString(requestTarget.substring(questionPos + 1));
            if (queryString.getParameter("status") != null) {
                statusCode = queryString.getParameter("status");
            }
            if (queryString.getParameter("body") != null) {
                body = queryString.getParameter("body");
            }
        }
        String response = "HTTP/1.1 " + statusCode + " OK\r\n" +
                "Content-Length: " + body.length() + "\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                body;

        clientSocket.getOutputStream().write(response.getBytes());
    }

    public void setDocumentRoot(File documentRoot) {
        this.documentRoot = documentRoot;
    }

    public List<String> getTeamNames() {
        return teamNames;
    }
}