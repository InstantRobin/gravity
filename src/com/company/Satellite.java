package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Represents a body that experiences gravity but does not exert it
// Moves according to path of gravity
public class Satellite extends GravBod {

    // History of all points visited by planet
    protected CopyOnWriteArrayList<Point> history = new CopyOnWriteArrayList<>();

    protected double dx;
    protected double dy;

    // greatly increased gravity strength given smaller scale
    protected static final double GRAV_CONST = .06674;
    
    Satellite(double x, double y, int radius, Color color, double dx, double dy){
        super(x, y, radius, color);
        this.dx = dx;
        this.dy = dy;
        history.add(new Point((int)x, (int)y));
        history.add(new Point((int)x, (int)y));
    }

    public CopyOnWriteArrayList<Point> getHistory() {
        return history;
    }

    public void updatePos(List<Star> stars) {
        x += dx;
        y += dy;
        
        // Checks to make sure doesn't clip inside of stars, allows bouncing
        // TODO: Works poorly for smaller stars
        for (Star star : stars){
            double distX = star.getX() - x;
            double distY = star.getY() - y;
            double dist = Math.sqrt(Math.pow(distX,2) +
                                    Math.pow(distY,2));
            if (dist <= star.getRad()){
                double angle = Math.atan2(distY,-distX);
                x += distX >= 0 ? -1 * (Math.abs(star.getRad() * Math.cos(angle)) - distX) : Math.abs(star.getRad() * Math.cos(angle)) + distX;
                y += distY >= 0 ? -1 * (Math.abs(star.getRad() * Math.sin(angle)) - distY) : Math.abs(star.getRad() * Math.sin(angle)) + distY;
                
                /*
                 Calculate bounce trajectory
                 Based on math found here:
                 https://math.stackexchange.com/questions/13261/how-to-get-a-reflection-vector
                */
                Point2D ball = new Point2D.Double(dx,dy);
                Point2D norm = new Point2D.Double((x-star.getX())/star.getRad(),(y-star.getY())/star.getRad());
                double dotProd = 2 * ball.getX() * norm.getX() + ball.getY() * norm.getY();
                Point2D temp = new Point2D.Double(dotProd * norm.getX(),dotProd * norm.getY());
                
                // momentum loss on each bounce
                double elasticity = 0.75;
                dx = elasticity *(ball.getX() - temp.getX());
                dy = elasticity *(ball.getY() - temp.getY());
            }
        }
        
        if (history.size() > 50) {
            history.remove(0);
        }
        history.add(new Point((int) x, (int) y));
    }

    // changes velocity of satellite based on gravitation attraction to other
    public void updateVelocity(List<Star> stars){
        for (Star star : stars){
            double distX = star.getX() - x;
            double distY = star.getY() - y;
            double dist = Math.sqrt(Math.pow(distX,2) + Math.pow(distY,2));
            double angle = Math.atan2(distY,distX);

            double acc = GRAV_CONST * star.getMass() / Math.pow(dist,2);
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
