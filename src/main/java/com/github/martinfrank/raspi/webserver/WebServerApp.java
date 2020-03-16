package com.github.martinfrank.raspi.webserver;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServerApp {

    private final HttpServer server;
    private final GpioController gpio;
    private final WebServerRequestHandler webServerRequestHandler;

    public static void main(String[] args) {
        try {
            new WebServerApp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public WebServerApp() throws IOException {
        gpio = GpioFactory.getInstance();
        webServerRequestHandler = new WebServerRequestHandler(gpio);
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", webServerRequestHandler);
        server.setExecutor(null); // creates a default executor
        server.start();
        new DistanceMeasure(gpio, RaspiPin.GPIO_27, RaspiPin.GPIO_28);
    }


}
