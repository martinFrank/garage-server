package com.github.martinfrank.garageserver;

import com.pi4j.io.gpio.GpioController;

public abstract class GpioAction {

    private final GpioController gpio;

    public GpioAction(GpioController gpio) {
        this.gpio = gpio;
    }

    public abstract void apply();

    public GpioController getGpio() {
        return gpio;
    }
}
