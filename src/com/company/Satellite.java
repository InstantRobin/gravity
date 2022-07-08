package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Math.sqrt;

// Represents a body that experiences gravity but does not exert it
// Moves according to path of gravity
public class Satellite extends GravBod {

    public static final int MAX_HISTORY = 300;
    // History of all points visited by planet
    protected CopyOnWriteArrayList<Point> history = new CopyOnWriteArrayList<>();

    protected double dx;
    protected double dy;
    protected int simSpeed;

    // greatly increased gravity strength given smaller scale
    protected static final double GRAV_CONST = .06674;
    public static final double ELASTICITY = 0.75;
    
    Satellite(double x, double y, int radius, Color color, double dx, double dy, int simSpeed){
        super(x, y, radius, color);
        this.dx = dx;
        this.dy = dy;
        this.simSpeed = simSpeed;
        history.add(new Point((int)x, (int)y));
        history.add(new Point((int)x, (int)y));
    }

    public void updateVelocity(List<Star> stars){
        for (Star star : stars){
            double distX = star.getX() - x;
            double distY = star.getY() - y;
            double dist = Math.sqrt(Math.pow(distX,2) + Math.pow(distY,2));
            double angle = Math.atan2(distY,distX);

            double acc = (GRAV_CONST * star.getMass() / Math.pow(dist,2)) / simSpeed;
            dx = dx + acc * Math.cos(angle);
            dy = dy + acc * Math.sin(angle);
        }
    }

    public void updatePos(List<Star> stars) {
        x += dx;
        y += dy;

        checkForCollisions(stars);
        updateHistory();
    }

    // Checks to make sure doesn't clip inside of stars, if so, moves to edge of star and bounces
    private void checkForCollisions(List<Star> stars) {
        for (Star star : stars){
            double distX = star.getX() - x;
            double distY = star.getY() - y;
            double dist = Math.sqrt(Math.pow(distX,2) +
                                    Math.pow(distY,2));
            if (dist <= star.getRad()){
                unCollide(star);
                bounce(star);
            }
        }
    }

    // Given a colliding star, moves the Satellite to the nearest point on star's radius to previous location
    private void unCollide(Star star) {
        double prevX = x - dx;
        double prevY = y - dy;
        double prevAng = Math.atan2(star.getX() - prevX, star.getY() - prevY);
        double nearX = star.getX() - Math.sin(prevAng) * star.getRad();
        double nearY = star.getY() - Math.cos(prevAng) * star.getRad();
        x = nearX;
        y = nearY;
    }

    /*
     Calculate bounce trajectory
     Based on equation found here:
     https://math.stackexchange.com/questions/13261/how-to-get-a-reflection-vector
     Added extra spacing because this fn is dense + complicated
    */
    private void bounce(Star star) {
        Point2D satVec = new Point2D.Double(dx,dy);

        // starNormalVec is the normal vector at the point of impact
        Point2D starNormalVec = new Point2D.Double((x-star.getX()) / star.getRad(),
                                                   (y-star.getY()) / star.getRad());

        // 2(d*n) where d is satVec and n is unitNormalVec
        double dotProd = 2 * (satVec.getX() * starNormalVec.getX()
                            + satVec.getY() * starNormalVec.getY());
        // 2(d*n)n
        Point2D res = new Point2D.Double(dotProd * starNormalVec.getX(),
                                         dotProd * starNormalVec.getY());

        // d - 2(d*n)n, as well as momentum loss on each bounce
        dx = ELASTICITY * (satVec.getX() - res.getX());
        dy = ELASTICITY * (satVec.getY() - res.getY());

        if (dx <= 0.01 && dy <= 0.01){
            history.clear();
        }
    }

    private void updateHistory() {
        if (history.size() > MAX_HISTORY) {
            history.remove(0);
        }
        history.add(new Point((int) x, (int) y));
    }

    // changes velocity of satellite based on gravitation attraction to other

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
