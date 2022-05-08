package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpacePanel space = new SpacePanel();
        frame.add(space);

        frame.pack();
        frame.setVisible(true);

        Timer test = new Timer(1, e -> {
            space.updatePlanets();
            space.repaint();
        });
        test.start();

    }
}
