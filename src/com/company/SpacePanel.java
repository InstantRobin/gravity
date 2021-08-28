package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpacePanel extends JPanel {

    // As long as planets have gravity towards one another, max of 2 planets allowed in array
    // (this can be fixed once 3 body problem is solved)
    private ArrayList<Planet> planets = new ArrayList<>();

    SpacePanel(){
        super();
        planets.add(new Planet(100,10,400,300, 5, 0));
        planets.add(new Planet(100000,100,500,500, 0, 0));
    }

    // Sets component size to value determined here
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    // Draws each planet's location, lines between each place in planet's historical path
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Planet p : planets){
            g.drawOval((int)p.getX()-p.getRad()/2,(int)p.getY()-p.getRad()/2,p.getRad(),p.getRad());
            for (int i = 1; i < p.getHistory().size(); i++) {
                CopyOnWriteArrayList<Point> hist = p.getHistory();
                g.drawLine(hist.get(i - 1).x, hist.get(i - 1).y, hist.get(i).x, hist.get(i).y);
            }
        }

    }

    // Changes planet's position, velocity, updates path history
    public void updatePlanets(){
        for (Planet p : planets){
            updateVelocity(p);
            p.updatePos();
        }
    }

    // changes velocity of planet based on gravitation attraction to other
    // greatly increased gravity strength given smaller scale
    private void updateVelocity(Planet planet){
        for (Planet p : planets){
            if (p != planet) {
                double dist = Math.sqrt(Math.pow(planet.getX()-p.getX(),2) +
                        Math.pow(planet.getY()-p.getY(),2));
                double angle = Math.atan2((p.getY() - planet.getY()),(p.getX() - planet.getX()));
                double acc = .06674 * p.getMass() / Math.pow(dist,2);
                planet.setDx(planet.getDx() + acc * Math.cos(angle));
                planet.setDy(planet.getDy() + acc * Math.sin(angle));
            }
        }
    }
}
