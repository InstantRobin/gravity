package com.company;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Satellite extends GravBod {

    // History of all points visited by planet
    protected CopyOnWriteArrayList<Point> history = new CopyOnWriteArrayList<>();

    protected double dx;
    protected double dy;

    Satellite(int x, int y, int radius, double dx, double dy){
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
        history.add(new Point(x, y));
        history.add(new Point(x, y));
    }

    public CopyOnWriteArrayList<Point> getHistory() {
        return history;
    }

    public void updatePos() {
        x += dx;
        y += dy;
        history.add(new Point((int) x, (int) y));
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
}
