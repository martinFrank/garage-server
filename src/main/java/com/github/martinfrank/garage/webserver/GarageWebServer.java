package com.github.martinfrank.garage.webserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class GarageWebServer {

    public static void main(String[] args) {
        try {
            System.out.println("starting new webserver");
            new GarageWebServer().startUp();
        } catch (IOException e) {
            System.out.println("error starting webserver: " + e);
            e.printStackTrace();
        }
    }

    private void startUp() throws IOException {
        final WebServerRequestHandler webServerRequestHandler = new WebServerRequestHandler();
        final HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/garage/webserver", webServerRequestHandler);
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("webserver is started...");
    }


}
