package com.github.martinfrank.garage.webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WebServerRequestHandler implements HttpHandler {


    public WebServerRequestHandler() {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        createResponse(httpExchange);
    }

    private void createResponse(HttpExchange httpExchange) throws IOException {
        String htmlDoc = generateHtmlDocument();
        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        httpExchange.sendResponseHeaders(200, htmlDoc.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(htmlDoc.getBytes(StandardCharsets.UTF_8));
        os.close();
    }


    private String generateHtmlDocument() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/template.js")))) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                String line = reader.readLine();
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "<html>an error happened during the preparation of this website\nerror:\n" + e + "<html>";
        }
    }

}
