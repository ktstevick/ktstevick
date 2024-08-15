package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fruit Engine");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Sets window to preferred size of its subcomponents (GamePanel)

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}