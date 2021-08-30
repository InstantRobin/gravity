package com.company;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

// Handles mouse creation of stars and satellites
public class ClickListener extends MouseInputAdapter {

    private final SpacePanel panel;
    private Color color;
    private GravBod bod;
    private int x;
    private int y;
    private int button;

    ClickListener(SpacePanel panel){
        this.panel = panel;
    }

    private void randColor(){
        color = new Color((int) (Math.random() * 255), (int) (Math.random()*255), (int) (Math.random()*255));
    }

    // Default GravBod creation
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
    @Override
    public void mousePressed(MouseEvent e){
        randColor();
        x = e.getX();
        y = e.getY();
        // Initializes GravBod based on click, locks in current creation type
        if (e.getButton() == MouseEvent.BUTTON1) {
            bod = new Satellite(x, y, 10, color, 2 * (Math.random() - 0.5) * 20, 2 * (Math.random() - 0.5) * 20);
            button = MouseEvent.BUTTON1; // so mouseDragged does not forget which action is being taken
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            bod = new Star(x, y, 0, color, 0);
            button = MouseEvent.BUTTON3;
        }
    }

    // For left click, draws satellite velocity line
    // For right click, draws star preview
    @Override
    public void mouseDragged(MouseEvent e){
        if (button == MouseEvent.BUTTON1) {
            panel.drawGravBod(panel.getGraphics(),bod);
            panel.getGraphics().drawLine(x,y,e.getX(),e.getY());
        } else if (button == MouseEvent.BUTTON3) {
            bod.setRad((int)Math.sqrt(Math.pow(e.getX()-x,2)+Math.pow(e.getY()-y,2)));
            panel.drawGravBod(panel.getGraphics(),bod);
        }
    }

    // For left click, releases the satellite with trajectory based on drag distance
    // For right click, creates star with radius based on drag distance
    @Override
    public void mouseReleased(MouseEvent e){
        if (button == MouseEvent.BUTTON1) {
            panel.addSat(bod.getX(),bod.getY(),color,-((float)(e.getY()-y))/10,-((float)(e.getX()-x))/10);
            button = 0;
        } else if (button == MouseEvent.BUTTON3) {
           panel.addStar(bod.getX(),bod.getY(),bod.getColor(),bod.getRad());
           button = 0;
        }
    }
}
