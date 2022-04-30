package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame frame;
    private static GridBagConstraints c;
    private static final int fps = 100;

    public static void main(String[] args){
        SpacePanel space = new SpacePanel();

        initFrame();
        initGridBag();
        initSpace(space);
        startSimulation(space);
    }

    private static void initFrame() {
        frame = new JFrame("Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState()| JFrame.MAXIMIZED_BOTH); // Maximize window
    }

    private static void initGridBag() {
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
    }

    private static void initSpace(SpacePanel space) {
        addTopButtons(space);
        addSpacePanel(space);
        addSpaceClickListener(space);
    }

    private static void addTopButtons(SpacePanel space) {
        addTopButton("Clear Stars", e -> space.clearStars());
        addTopButton("Clear Satellites", e -> space.clearSatellites());
        addTopButton("Clear All", e -> {
            space.clearSatellites();
            space.clearStars();
        });
    }

    // Set up constraints to allow Space to fill up rest of screen
    private static void addSpacePanel(SpacePanel space) {
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(space,c);
        frame.pack();
    }

    // Enables mouse interaction
    private static void addSpaceClickListener(SpacePanel space) {
        SpaceClickListener listener = new SpaceClickListener(space);
        space.addMouseListener(listener);
        space.addMouseMotionListener(listener);
    }

    private static void startSimulation(SpacePanel space) {
        Timer timer = new Timer(1000/fps, e -> {
            space.updateSatellites();
            space.repaint();
        });

        timer.start();
    }

    // Places button sequentially at top of screen
    // Note: Only works properly if things have only been added to top row
    private static void addTopButton(String str, ActionListener listener){
        JButton button = new JButton();
        button.setText(str);
        button.addActionListener(listener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = frame.getContentPane().getComponentCount();
        c.gridy = 0;

        frame.add(button,c);
    }
}
