package org.suai.view;

import javax.swing.*;

public class GUI extends JFrame {
    public GUI(){
        super("SpaceBattle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Game());
        this.setSize(796, 519);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}