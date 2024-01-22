package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Window first
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MOTHER 4 FOR 4");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Causes window to be sized toe fit the preferred size and layouts of its subcomponents (=GamePanel)
        window.pack();

        // Sets window to center of screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}