package com.github.martinfrank.garageserver;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.RaspiPin;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebServerRequestHandler implements HttpHandler {

    private final List<HtmlSubmitButton> buttons;
    private boolean toggle;

    public WebServerRequestHandler(GpioController gpio) {
        buttons = new ArrayList<>();
        buttons.add(new HtmlSubmitButton("action", "GPIO_04", "GPIO 04<br/>Licht an/aus", new ToggleAction(gpio, RaspiPin.GPIO_04)));
        buttons.add(new HtmlSubmitButton("action", "GPIO_05", "GPIO 05<br>Knopf", new PulseAction(gpio, RaspiPin.GPIO_05, 250)));
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        handleRequestBody(httpExchange);
        createResponse(httpExchange);
    }

    private void createResponse(HttpExchange httpExchange) throws IOException {
        String response = generateWebsite();
        httpExchange.sendResponseHeaders(200, response.length());
        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private void handleRequestBody(HttpExchange httpExchange) {
        String request = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))
                .lines().collect(Collectors.joining("\n"));
        String[] asPair = request.split("=");
        if (asPair.length == 2) {
            String key = asPair[0];
            String value = asPair[1];
            handleAction(key, value);
        }
    }

    private void handleAction(String key, String value) {
        for (HtmlSubmitButton button : buttons) {
            button.apply(key, value);
        }
    }

    private String generateWebsite() {
        //https://strobelstefan.org/?p=5772

        final StringBuilder content = new StringBuilder("<!doctype html><html><head/><body>");
        content.append("<div align=\"center\"><embed src=\"http://192.168.0.64:8081\" style=\"width:680px; height: 520px;\"></div><br/>");
        content.append("<div align=\"center\"><form method=\"post\">");
        for (HtmlSubmitButton button : buttons) {
            content.append(button.createSubmitButtonHtmlString());
        }
        content.append("</form></div>");
        content.append("</body></html>");
        return content.toString();
    }
}
