package com.company;

public class Planet {

    private int mass;
    private int radius;
    private int x;
    private int y;
    private int dx;
    private int dy;

    Planet(int mass, int radius, int x, int y, int dx, int dy){
        this.mass = mass;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public int getMass() {
        return mass;
    }

    public int getRad() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
