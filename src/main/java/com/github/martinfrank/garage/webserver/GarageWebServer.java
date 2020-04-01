package com.github.martinfrank.garage.webserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class GarageWebServer {

    private final HttpServer server;
    private final WebServerRequestHandler webServerRequestHandler;


    public static void main(String[] args) {
        try {
            System.out.println("main...");
            GarageWebServer server = new GarageWebServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public GarageWebServer() throws IOException {

        webServerRequestHandler = new WebServerRequestHandler();
        System.out.println("webServerRequestHandler:" + webServerRequestHandler);
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        System.out.println("server:" + server);
        server.createContext("/", webServerRequestHandler);
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("server.start()");
//        //new DistanceMeasure(gpio, RaspiPin.GPIO_27, RaspiPin.GPIO_28);
    }


}
