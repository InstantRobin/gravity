package com.company;

import java.awt.*;

// Represents a stationary object that exerts gravity
public class Star extends GravBod{

    // Multiply rad by RAD_MASS_RATIO to get gravBod mass
    private static final int RAD_MASS_RATIO = 10000;
    private final int mass;

    public Star(double x, double y, int radius, Color color) {
        super(x, y, radius, color);
        mass = radius * RAD_MASS_RATIO;
    }

    public int getMass() {
        return mass;
    }
}
