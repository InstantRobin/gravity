package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpacePanel extends JPanel {

    // As long as satellites have gravity towards one another, max of 2 satellites allowed in array
    // (this can be fixed once 3 body problem is solved)
    private ArrayList<Satellite> satellites = new ArrayList<>();
    private ArrayList<Star> stars = new ArrayList<>();

    SpacePanel(){
        super();
        stars.add(new Star(300,500,Color.orange,25,1000000));
        stars.add(new Star(1200,500,Color.blue,25,1000000));
        satellites.add(new Satellite(800,500,10,Color.red, 9, -9));
        satellites.add(new Satellite(800,500,10,Color.green, -11, 10));
    }

    // Sets component size to value determined here
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1920,1080);
    }

    // Draws each satellite's location, lines between each place in satellite's historical path
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Satellite p : satellites){
            drawGravBod(g,p);
            for (int i = 1; i < p.getHistory().size(); i++) {
                CopyOnWriteArrayList<Point> hist = p.getHistory();
                g.setColor(p.getColor());
                g.drawLine(hist.get(i - 1).x, hist.get(i - 1).y, hist.get(i).x, hist.get(i).y);
            }
        }
        for (Star s : stars) {
            drawGravBod(g,s);
        }
    }


    // Renders a gravitational body as an oval centered at x and y coords
    public void drawGravBod(Graphics g, GravBod bod){
        int rad = bod.getRad();
        g.setColor(bod.getColor());
        g.fillOval((int) (bod.getX()-rad), (int) (bod.getY()-rad),2*rad,2*rad);
        g.setColor(Color.black);
    }

    // Changes satellite's position, velocity, updates path history
    public void updateSatellites(){
        for (Satellite p : satellites){
            p.updateVelocity(stars);
            p.updatePos();
        }
    }
}
