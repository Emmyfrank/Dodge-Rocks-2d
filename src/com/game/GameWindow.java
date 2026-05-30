package com.game;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameWindow extends JFrame {
    public GameWindow() {
        super("Dodge Rocks — 2D Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel(1400, 1000);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.requestFocusInWindow();
        panel.start();
    }
}
