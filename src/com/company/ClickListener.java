package com.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {

    SpacePanel panel;

    ClickListener(SpacePanel panel){
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Color color = new Color((int) (Math.random() * 255), (int) (Math.random()*255), (int) (Math.random()*255));
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.addSat(e.getX(), e.getY(), color, 2 * (Math.random() - 0.5) * 20, 2 * (Math.random() - 0.5) * 20);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            panel.addStar(e.getX(), e.getY(), color, 1000000);
        }
    }
}
