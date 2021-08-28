package com.company;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Planet {

    private int mass;
    private int radius;
    private double x;
    private double y;
    private double dx;
    private double dy;
    // History of all points visited by planet
    private CopyOnWriteArrayList<Point> history = new CopyOnWriteArrayList<>();

    Planet(int mass, int radius, int x, int y, double dx, double dy){
        this.mass = mass;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        history.add(new Point(x, y));
        history.add(new Point(x, y));
    }

    public void updatePos(){
        x += dx;
        y += dy;
        history.add(new Point((int) x, (int) y));
    }

    public int getMass() {
        return mass;
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

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public CopyOnWriteArrayList<Point> getHistory() {
        return history;
    }
}
