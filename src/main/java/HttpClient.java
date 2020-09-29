import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    private String responseBody;
    private final HttpMessage responseMessage;

    public HttpClient(String hostName, int port, String requestTarget) throws IOException {

        Socket socket = new Socket(hostName, port);

        HttpMessage requestMessage = new HttpMessage("GET " + requestTarget + " HTTP/1.1");
        requestMessage.setHeader("Host", hostName);
        requestMessage.write(socket);

        responseMessage = HttpMessage.read(socket);

        int contentLength = Integer.parseInt(getResponseHeader("Content-Length"));
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            // Reads content body based on content length
            body.append((char)socket.getInputStream().read());
        }
        responseBody = body.toString();
    }

    public HttpClient(String hostName, int port, String requestTarget, String method, QueryString form) throws IOException {
        Socket socket = new Socket(hostName, port);

        String requestBody = form.getQueryString();

        HttpMessage requestMessage = new HttpMessage(method + " " + requestTarget + " HTTP/1.1");
        requestMessage.setHeader("Host", hostName);
        requestMessage.setHeader("Content-Length", String.valueOf(requestBody.length()));
        requestMessage.write(socket);
        socket.getOutputStream().write(requestBody.getBytes());

        responseMessage = HttpMessage.read(socket);
    }

    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?status=200");
        System.out.println(client.getResponseHeader("Server"));
    }

    public int getStatusCode() {
        String[] responseLineParts = responseMessage.getStartLine().split(" ");
        int statusCode = Integer.parseInt(responseLineParts[1]);
        return statusCode;
    }

    public String getResponseHeader(String headerName) {
        return responseMessage.getHeader(headerName);
    }

    public String getResponseBody() {
        return responseBody;
    }
}