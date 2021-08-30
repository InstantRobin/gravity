package com.company;

public class Star extends GravBod{

    private int mass;

    public Star(int x, int y, int radius, int mass) {
        super(x, y, radius);
        this.mass = mass;
    }

    public int getMass() {
        return mass;
    }
}
