package com.company;

import javax.swing.*;
import java.awt.*;

public class SpacePanel extends JPanel {

    SpacePanel(){
        super();
    }

    // Sets component size to value determined here
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawOval(50,50,10,10);
    }
}
