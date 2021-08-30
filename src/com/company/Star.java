package com.company;

import java.awt.*;

public class Star extends GravBod{

    private int mass;

    public Star(int x, int y, Color color, int radius, int mass) {
        super(x, y, radius, color);
        this.mass = mass;
    }

    public int getMass() {
        return mass;
    }
}
