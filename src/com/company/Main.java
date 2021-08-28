package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpacePanel space = new SpacePanel();
        frame.add(space);

        frame.pack();
        frame.setVisible(true);

    }
}
