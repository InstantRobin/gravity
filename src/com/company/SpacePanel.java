package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpacePanel extends JPanel {

    // Arrays of stars and satellites to be rendered
    private List<Satellite> satellites = new ArrayList<>();
    private List<Star> stars = new ArrayList<>();
    private final int simSpeed;

    /* Preview render state (according to mouse press)
    0: Not previewing
    1: Left  Click: Satellite preview (satellite + velocity vector)
    3: Right Click: Star preview
    */
    public enum RenderState {
        NONE, SAT, STAR
    }

    // Used when previewing a star or satellite during mouse-drag creation
    private RenderState previewRenderState = RenderState.NONE;

    // GravBod preview to be rendered when creating GravBods
    private GravBod previewBod;
    // Used to preview velocity when creating Satellites
    private Point[] previewLine = new Point[2];

    SpacePanel(int simSpeed){
        super();
        // Set cool space background color
        this.setBackground(Color.black);
        this.simSpeed = simSpeed;
        /*
        Example stars / satellites:
        stars.add(new Star(300,500, 50, Color.orange, 1000000));
        stars.add(new Star(1200,500, 50, Color.cyan, 1000000));
        satellites.add(new Satellite(500,500,10,Color.red, 0, 0));
        satellites.add(new Satellite(800,500,10,Color.green, -11, 10));
        */
    }

    // Set component size to value determined here
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
            drawHistory(g,p);
        }
        for (Star s : stars) {
            drawGravBod(g,s);
        }
        renderPreview(g);
    }

    // Renders a gravitational body as a colored oval centered body's at x and y coords
    public void drawGravBod(Graphics g, GravBod bod){
        int rad = bod.getRad();
        g.setColor(bod.getColor());
        g.fillOval((int) (bod.getX()-rad), (int) (bod.getY()-rad),2*rad,2*rad);
        g.setColor(Color.white);
    }

    // Draws ever position a given satellite has visited in satellite's color
    private void drawHistory(Graphics g, Satellite sat){
        for (int i = 1; i < sat.getHistory().size(); i++) {
            CopyOnWriteArrayList<Point> hist = sat.getHistory();
            g.setColor(sat.getColor());
            g.drawLine(hist.get(i - 1).x, hist.get(i - 1).y, hist.get(i).x, hist.get(i).y);
        }
    }

    // Changes satellite's position, velocity, updates path history
    public void updateSatellites(){
        for (Satellite p : satellites){
            p.updateVelocity(stars);
            p.updatePos(stars);
        }
    }

    // Creates a satellite
    public void addSat(double x, double y, Color color, double dx, double dy){
        satellites.add(new Satellite(x,y,10,color,dy,dx, simSpeed));
    }

    // Creates a star, mass is proportional to radius
    public void addStar(double x, double y, Color color, int rad){
        stars.add(new Star(x,y, rad, color));
    }

    // Stores a temporary GravBod to be rendered as a preview
    public void setPreviewBod(GravBod previewBod) {
        this.previewBod = previewBod;
    }

    // Renders the GravBod stored in preview, if left click, renders line stored in preview first
    public void renderPreview(Graphics g){
        switch(previewRenderState){
            case NONE:
                break;
            case SAT:
                // Renders Satellite and Velocity Line preview based on mouse location
                g.setColor(previewBod.getColor());
                g.drawLine(previewLine[0].x, previewLine[0].y, previewLine[1].x, previewLine[1].y);
                g.setColor(Color.white);
            case STAR:
                // Renders GravBod
                drawGravBod(g, previewBod);
                break;
        }
    }

    // Set current preview state
    public void setPreviewRenderState(RenderState previewRenderState) {
        this.previewRenderState = previewRenderState;
    }

    // Set coordinates of preview line
    public void setPreviewLine(Point[] vLine) {
        this.previewLine = vLine;
    }

    public void clearStars(){
        stars = new ArrayList<>();
    }

    public void clearSatellites(){
        satellites = new ArrayList<>();
    }
}
