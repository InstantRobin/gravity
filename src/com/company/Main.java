package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame frame;
    private static GridBagConstraints constraints;
    private static final int speed = 60;
    private static final int fps = 200;

    public static void main(String[] args){
        initFrame();
        initGridBag();
        SpacePanel space = new SpacePanelFactory(frame, constraints, speed).initSpace();
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
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    private static void startSimulation(SpacePanel space) {
        Timer timer = new Timer(1000/ fps, e -> {
            space.updateSatellites();
            space.repaint();
        });

        timer.start();
    }

}
