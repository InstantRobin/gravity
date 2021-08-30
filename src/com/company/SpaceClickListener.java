package com.company;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

// Handles mouse creation of stars and satellites
public class SpaceClickListener extends MouseInputAdapter {

    private final SpacePanel panel;
    private Color color;
    private GravBod bod;
    private int x;
    private int y;
    private int button;
    Point[] line = new Point[2];

    SpaceClickListener(SpacePanel panel){
        this.panel = panel;
    }

    private void randColor(){
        color = new Color((int) (Math.random() * 255), (int) (Math.random()*255), (int) (Math.random()*255));
    }

    // Default GravBod creation
    // Middle click creates satellite with random velocity
    // Right click creates star with size 50
    @Override
    public void mouseClicked(MouseEvent e) {
        randColor();
        if (e.getButton() == MouseEvent.BUTTON2) {
            panel.addSat(e.getX(), e.getY(), color, 2 * (Math.random() - 0.5) * 20, 2 * (Math.random() - 0.5) * 20);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            panel.addStar(e.getX(), e.getY(), color, 50);
        }
    }

    // First stage in drag-to-create GravBod creation
    // Left click drag creates a satellite with velocity determined by mouse drag distance
    //      also has a line from initial point to mouse location as preview vector for velocity
    @Override
    public void mousePressed(MouseEvent e){
        randColor();
        x = e.getX();
        y = e.getY();

        // Initializes GravBod based on click, locks in current creation type
        if (e.getButton() == MouseEvent.BUTTON1) {
            bod = new Satellite(x, y, 10, color, 2 * (Math.random() - 0.5) * 20, 2 * (Math.random() - 0.5) * 20);
            // Stores points for movement vector
            line[0] = new Point(x,y);
            line[1] = new Point(x,y);
            panel.setTempLn(line);
            panel.setTempBod(bod);
            panel.setTempVal(1);
            button = MouseEvent.BUTTON1; // so mouseDragged knows which action is being taken
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            bod = new Star(x, y, 0, color, 0);
            panel.setTempBod(bod);
            panel.setTempVal(3);
            button = MouseEvent.BUTTON3;
        }
    }

    // For left click, updates satellite velocity vector
    // For right click, updates star preview size
    @Override
    public void mouseDragged(MouseEvent e){
        if (button == MouseEvent.BUTTON1) {
            line[1] = new Point(e.getX(),e.getY());
        } else if (button == MouseEvent.BUTTON3) {
            bod.setRad((int)Math.sqrt(Math.pow(e.getX()-x,2)+Math.pow(e.getY()-y,2)));
        }
    }

    // For left click, releases the satellite with trajectory based on drag distance
    // For right click, creates star with radius based on drag distance
    // Messages SpacePanel to stop rendering previews
    @Override
    public void mouseReleased(MouseEvent e){
        if (button == MouseEvent.BUTTON1) {
            panel.addSat(bod.getX(),bod.getY(),color,-((float)(e.getY()-y))/10,-((float)(e.getX()-x))/10);
        } else if (button == MouseEvent.BUTTON3) {
           panel.addStar(bod.getX(),bod.getY(),bod.getColor(),bod.getRad());
        }
        panel.setTempVal(0);
        button = 0;
    }
}
