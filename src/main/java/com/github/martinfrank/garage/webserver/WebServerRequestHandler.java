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
        String response = generateWebsite();


//        "    xhttp.setRequestHeader(\"Access-Control-Allow-Headers\", \"http://192.168.0.38:8080\");\n" +
//                "    xhttp.setRequestHeader(\"Access-Control-Allow-Credentials\", \"true\");\n" +


        httpExchange.getResponseHeaders().add("Content-Type", "text/html");

//        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
//        httpExchange.getResponseHeaders().add("Access-Control-Allow-Header", "*");
//        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
//        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE");

        httpExchange.sendResponseHeaders(200, response.length());

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }


    private String generateWebsite() {

        //https://strobelstefan.org/?p=5772

//        final StringBuilder content = new StringBuilder("<!doctype html><html><head/><body>");
//        content.append("<div align=\"center\"><embed src=\"http://192.168.0.64:8081\" style=\"width:680px; height: 520px;\"></div><br/>");
//        content.append("<div align=\"center\"><form method=\"post\">");
//        for (HtmlSubmitButton button : buttons) {
//            content.append(button.createSubmitButtonHtmlString());
//        }
//        content.append("</form></div>");
//        content.append("</body></html>");
//        return content.toString();

        String str = "{ \"id\" : 2,  \"content\" : \"Hello there, Stranger!\" + }";


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/template.html")))) {
            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                String line = reader.readLine();
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "<!DOCTYPE html><html>error<html>";

//        return "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<script type=\"text/javascript\" language=\"javascript\">\n" +
////                "function UserAction() {\n" +
////                "    var xhttp = new XMLHttpRequest();\n" +
////                "    xhttp.onreadystatechange = function() {\n" +
////                "         if (this.readyState == 4 && this.status == 200) {\n" +
////                "             alert(this.responseText);\n" +
////                "         }\n" +
////                "    };\n" +
//////                "    xhttp.open(\"POST\", \"http://192.168.0.38:8080/hello-world\", true);\n" + // false for synchronous request
////                "    xhttp.open(\"GET\", \"http://192.168.0.66:8080/hello-world\", true);\n" +  // false for synchronous request
//////                "    xhttp.setRequestHeader(\"Access-Control-Allow-Headers\", \"http://192.168.0.38:8080\");\n" +
//////                "    xhttp.setRequestHeader(\"Access-Control-Allow-Credentials\", \"true\");\n" +
////                "    xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n" +
//////                "    xhttp.send(\"{ \\\"id\\\" : 2,  \\\"content\\\" : \\\"Hello there, Stranger!\\\" }\");\n" +
////                "    xhttp.send( null );\n" +
////                "    return xhttp.responseText;\n" +
////                "}" +
//                "const UserAction = async () => {\n" +
//                "  const response = await fetch('http://192.168.0.66:8080/hello-world');\n" +
//                "  const myJson = await response.json(); //extract JSON from the http response\n" +
//                "  // do something with myJson\n" +
//                "  return myJson;"+
//                "}"+
//                "</script>" +
//                "<body>\n" +
//                "\n" +
//                "<h2>My First JavaScript</h2>\n" +
//                "\n" +
////                "<button type=\"submit\" onclick=\"UserAction()\">Search</button>\n"+
//                "<button type=\"submit\" onclick=\"document.getElementById('demo2').innerHTML = UserAction()\">Search</button>\n" +
//                "<button type=\"button\"\n" +
//                "onclick=\"document.getElementById('demo').innerHTML = Date()\">\n" +
//                "Click me to display Date and Time.</button>\n" +
//                "\n" +
//                "<p id=\"demo\"></p>\n" +
//                "<p id=\"demo2\"></p>\n" +
//                "<p id=\"myJson\"></p>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html> ";
    }
}
