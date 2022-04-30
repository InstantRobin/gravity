package com.company;

import java.awt.*;

// Represents a stationary object that exerts gravity
public class Star extends GravBod{

    private final int mass;

    public Star(double x, double y, int radius, Color color, int mass) {
        super(x, y, radius, color);
        this.mass = mass;
    }

    public int getMass() {
        return mass;
    }
}
