package com.company;

// Short for Gravitational Body
// Represents an object that experiences the pull of gravity
public class GravBod {
    protected int radius;
    protected double x;
    protected double y;

    public GravBod(int x, int y, int radius) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public int getRad() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
