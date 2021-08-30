package com.company;

import java.awt.*;

// Short for Gravitational Body
// Represents an object that experiences the pull of gravity
public class GravBod {
    protected int radius;
    protected double x;
    protected double y;
    protected Color color;

    public GravBod(double x, double y, int radius, Color color) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setRad(int radius) {
        this.radius = radius;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
