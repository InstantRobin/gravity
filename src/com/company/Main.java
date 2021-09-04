package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) throws InterruptedException {

        frame = new JFrame("Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpacePanel space = new SpacePanel();

        JButton clStarButton = new JButton();
        clStarButton.setText("Clear Stars");
        JButton clSatButton = new JButton();
        clSatButton.setText("Clear Satellites");
        JButton clAllButton = new JButton();
        clAllButton.setText("Clear All");

        clStarButton.addActionListener(e -> space.clearStars());
        clSatButton.addActionListener(e -> space.clearSatellites());
        clAllButton.addActionListener(e -> {
            space.clearSatellites();
            space.clearStars();
        });

        frame.add(clSatButton,BorderLayout.PAGE_START);
        frame.add(clStarButton,BorderLayout.PAGE_START);
        frame.add(clAllButton,BorderLayout.PAGE_START);
        frame.add(space,BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        SpaceClickListener listener = new SpaceClickListener(space);
        frame.getContentPane().addMouseListener(listener);
        frame.getContentPane().addMouseMotionListener(listener);
        space.setBackground(Color.black);

        while(true){
            space.updateSatellites();
            space.repaint();
            Thread.sleep(10);
        }
    }
    
    void addButton(String str, ActionListener listener){
        JButton button = new JButton();
        button.setText(str);
        button.addActionListener(listener);
        frame.add(button,BorderLayout.PAGE_START);
    }
}
