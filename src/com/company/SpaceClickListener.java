package com.company;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

// Handles mouse creation of stars and satellites
public class SpaceClickListener extends MouseInputAdapter {

    private final SpacePanel panel;
    private Color color;
    private GravBod previewBod;
    private int x;
    private int y;
    private int button;
    private final int simSpeed;
    private static final double VEL_MULTIPLIER = 1.5;
    Point[] previewLine = new Point[2];

    SpaceClickListener(SpacePanel panel, int simSpeed){
        this.panel = panel;
        this.simSpeed = simSpeed;
    }

    private void randColor(){
        color = new Color((int) (Math.random() * 255), (int) (Math.random()*255), (int) (Math.random()*255));
    }

    // Generic GravBod creation
    // Middle click creates satellite with random velocity
    // Right click creates star with size 50
    @Override
    public void mouseClicked(MouseEvent e) {
        randColor();
        if (e.getButton() == MouseEvent.BUTTON2) {
            double randX = VEL_MULTIPLIER * (Math.random() - 0.5) * 500 / simSpeed;
            double randY = VEL_MULTIPLIER * (Math.random() - 0.5) * 500 / simSpeed;
            panel.addSat(e.getX(), e.getY(), color, randX, randY);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            panel.addStar(e.getX(), e.getY(), color, 100);
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
            previewBod = new Satellite(x, y, 10, color, 2 * (Math.random() - 0.5) * 20, 2 * (Math.random() - 0.5) * 20, simSpeed);
            // Stores points for movement vector
            previewLine[0] = new Point(x,y);
            previewLine[1] = new Point(x,y);
            panel.setPreviewLine(previewLine);
            panel.setPreviewBod(previewBod);
            panel.setPreviewRenderState(SpacePanel.RenderState.SAT);
            button = MouseEvent.BUTTON1; // so mouseDragged knows which action is being taken
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            previewBod = new Star(x, y, 0, color);
            panel.setPreviewBod(previewBod);
            panel.setPreviewRenderState(SpacePanel.RenderState.STAR);
            button = MouseEvent.BUTTON3;
        }
    }

    // For left click, updates satellite velocity vector
    // For right click, updates star preview size
    @Override
    public void mouseDragged(MouseEvent e){
        if (button == MouseEvent.BUTTON1) {
            previewLine[1] = new Point(e.getX(),e.getY());
        } else if (button == MouseEvent.BUTTON3) {
            previewBod.setRad((int)Math.sqrt(Math.pow(e.getX()-x,2)+Math.pow(e.getY()-y,2)));
        }
    }

    // For left click, releases the satellite with trajectory based on drag distance
    // For right click, creates star with radius based on drag distance
    // Messages SpacePanel to stop rendering previews
    @Override
    public void mouseReleased(MouseEvent e){
        if (button == MouseEvent.BUTTON1) {
            panel.addSat(previewBod.getX(), previewBod.getY(),color,
                    VEL_MULTIPLIER * -((double)(e.getY()-y)) / simSpeed,
                    VEL_MULTIPLIER * -((double)(e.getX()-x)) / simSpeed);
        } else if (button == MouseEvent.BUTTON3) {
           panel.addStar(previewBod.getX(), previewBod.getY(), previewBod.getColor(), previewBod.getRad());
        }
        panel.setPreviewRenderState(SpacePanel.RenderState.NONE);
        button = 0;
    }
}
