package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;

public class SpacePanel extends JPanel {

    // As long as planets have gravity towards one another, max of 2 planets allowed in array
    // (this can be fixed once 3 body problem is solved)
    private ArrayList<Planet> planets = new ArrayList<>();

    SpacePanel(){
        super();
        planets.add(new Planet(100,10,100,100, 1, 0));
        planets.add(new Planet(100000,100,300,200, 0, 0));
    }

    // Sets component size to value determined here
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Planet p : planets){
            g.drawOval(p.getX(),p.getY(),p.getRad(),p.getRad());
        }
    }

    public void updatePlanets(){
//        for (Planet p : planets){
//            updateVelocity(p);
//            p.updatePos();
//        }
        updateVelocity(planets.get(0));
        planets.get(0).updatePos();
    }

    // changes velocity of planet based on gravitation attraction to other
    // greatly increased gravity strength given smaller scale
    // TODO: double check the math here
    private void updateVelocity(Planet planet){
        for (Planet p : planets){
            if (p != planet) {
                double dist = Math.sqrt(Math.pow(planet.getX()-p.getX(),2) +
                        Math.pow(planet.getY()-p.getY(),2));
                double angle = Math.atan2((p.getY() - planet.getY()),(p.getX() - planet.getX()));
                double acc = .0006674 * p.getMass() / Math.pow(dist,2);
                planet.setDx(planet.getDx() + acc * Math.cos(angle));
                planet.setDy(planet.getDy() + acc * Math.sin(angle));
            }
        }
    }
}
