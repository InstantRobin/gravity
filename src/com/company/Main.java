package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame frame;
    private static GridBagConstraints c;
    private static final int fps = 100;

    public static void main(String[] args){

        // Set up basic window, uses GridBagLayout
        frame = new JFrame("Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
    
        SpacePanel space = new SpacePanel();
        
        // Create clear element buttons, to be placed at top of screen
        addButton("Clear Stars",e -> space.clearStars());
        addButton("Clear Satellites",e -> space.clearSatellites());
        addButton("Clear All",e -> {
            space.clearSatellites();
            space.clearStars();
        });

        // Set up constraints to allow Space to fill up rest of screen
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(space,c);

        frame.pack();
        frame.setVisible(true);
        // Maximize window
        frame.setExtendedState(frame.getExtendedState()| JFrame.MAXIMIZED_BOTH);
        
        // Enables mouse interaction
        SpaceClickListener listener = new SpaceClickListener(space);
        space.addMouseListener(listener);
        space.addMouseMotionListener(listener);
        
        // Set cool space background color
        space.setBackground(Color.black);


        Timer timer = new Timer(1000/fps, e -> {
            space.updateSatellites();
            space.repaint();
        });
        
        timer.start();
    }
    
    // Places button sequentially at top of screen
    // Note: Only works properly if things have only been added to top row
    private static void addButton(String str, ActionListener listener){
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
