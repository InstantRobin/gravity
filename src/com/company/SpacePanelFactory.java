package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SpacePanelFactory {
    private static JFrame frame;
    private static GridBagConstraints constraints;
    private static SpacePanel space;
    private int speed;
    
    SpacePanelFactory(JFrame frame, GridBagConstraints constraints, int speed){
        SpacePanelFactory.frame = frame;
        SpacePanelFactory.constraints = constraints;
        this.speed = speed;
        space = new SpacePanel(speed);
    }

    public SpacePanel initSpace() {
        addTopButtons();
        addSpacePanel();
        addSpaceClickListener();
        return space;
    }

    private void addTopButtons() {
        addTopButton("Clear Stars", e -> space.clearStars());
        addTopButton("Clear Satellites", e -> space.clearSatellites());
        addTopButton("Clear All", e -> {
            space.clearSatellites();
            space.clearStars();
        });
    }

    // Set up constraints to allow Space to fill up rest of screen
    private void addSpacePanel() {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(space, constraints);
        frame.pack();
    }

    // Enables mouse interaction
    private void addSpaceClickListener() {
        SpaceClickListener listener = new SpaceClickListener(space,speed);
        space.addMouseListener(listener);
        space.addMouseMotionListener(listener);
    }

    // Places button sequentially at top of screen
    // Note: Only works properly if things have only been added to top row
    private void addTopButton(String str, ActionListener listener){
        JButton button = new JButton();
        button.setText(str);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.PLAIN, 36));

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = frame.getContentPane().getComponentCount();
        constraints.gridy = 0;

        frame.add(button, constraints);
    }
}
