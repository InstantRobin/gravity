package com.company;

import java.awt.*;

// Represents an object that exerts gravity
// Stationary
public class Star extends GravBod{

    private int mass;

    public Star(double x, double y, int radius, Color color, int mass) {
        super(x, y, radius, color);
        this.mass = mass;
    }

    public int getMass() {
        return mass;
    }
}
