package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpacePanel space = new SpacePanel();
        frame.add(space);

        frame.pack();
        frame.setVisible(true);
        space.setBackground(Color.black);

        while(true){
            space.updateSatellites();
            space.repaint();
            Thread.sleep(10);
        }

    }
}
