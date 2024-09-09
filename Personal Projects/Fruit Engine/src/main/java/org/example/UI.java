package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    private final GamePanel gp;
    private Graphics2D g2;
    private final BufferedImage creditScreen;
    private final Font arial_TILE;
    private final Font arial_80B;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_TILE = new Font("Arial", Font.PLAIN, gp.getTileSize());
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // CREDITS
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/screens/credits_0.png"));

        } catch (IOException e) {
            System.out.println("Something happened! Error Code: 008");
        }

        this.creditScreen = image;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_80B);
        g2.setColor(Color.white);

        if(gp.getGameState() == gp.getPauseState()) {
            drawPauseScreen();
        }
        if(gp.getGameState() == gp.getDialogueState()) {
            drawDialogueScreen();
        }
        if(gp.getGameState() == gp.getCreditsState()) {
            drawCreditsScreen();
        }
    }

    private void drawPauseScreen() {
        String text = "PAUSED";

        int x = getXForCenteredText(text);
        int y = gp.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    private void drawDialogueScreen() {
        gp.getDialogueH().drawDialogue(g2);
    }

    private void drawCreditsScreen() {
        String text = "YOU WIN";

        int x = getXForCenteredText(text);
        int y = gp.getScreenHeight() / 2;

        g2.drawImage(creditScreen, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.getScreenWidth() - length) / 2;
    }

    public Font getArial_TILE() {
        return arial_TILE;
    }
    public BufferedImage getCreditScreen() {
        return creditScreen;
    }
}
