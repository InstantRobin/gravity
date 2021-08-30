package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

// Represents a body that experiences gravity but does not exert it
// Moves according to path of gravity
public class Satellite extends GravBod {

    // History of all points visited by planet
    protected CopyOnWriteArrayList<Point> history = new CopyOnWriteArrayList<>();

    protected double dx;
    protected double dy;

    Satellite(int x, int y, int radius, Color color, double dy, double dx){
        super(x, y, radius, color);
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

    // changes velocity of satellite based on gravitation attraction to other
    // greatly increased gravity strength given smaller scale
    public void updateVelocity(ArrayList<Star> stars){
        for (Star star : stars){
            double dist = Math.sqrt(Math.pow(x-star.getX(),2) +
                    Math.pow(y-star.getY(),2));
            double angle = Math.atan2((star.getY() - y),(star.getX() - x));
            double acc = .06674 * star.getMass() / Math.pow(dist,2);
            dx = (dx + acc * Math.cos(angle));
            dy = (dy + acc * Math.sin(angle));
        }
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
