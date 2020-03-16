package com.github.martinfrank.raspi.webserver;

import java.util.Objects;

public class HtmlSubmitButton {

    private final String name;
    private final String value;
    private final String text;
    private final GpioAction action;

    public HtmlSubmitButton(String name, String value, String text, GpioAction action) {
        this.name = name;
        this.value = value;
        this.text = text;
        this.action = action;
    }

    public String createSubmitButtonHtmlString() {
        return "<button type=\"submit\" name=\"" + name + "\" value=\"" + value + "\" style=\"height:100px;width:300px\">" + text + "</button>";
    }

    public void apply(String name, String value) {
        if (Objects.equals(this.name, name) && Objects.equals(this.value, value)) {
            System.out.println("apply");
            action.apply();
        }
    }


}
