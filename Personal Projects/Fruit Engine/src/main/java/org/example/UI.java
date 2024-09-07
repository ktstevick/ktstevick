package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    private final GamePanel gp;
    private Graphics2D g2;
    private final BufferedImage creditScreen;
    private final Font arial_TILE, arial_80B;
    private String[] currentDialogue;
    private int dialogueIndex = 0;

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

    public void drawPauseScreen() {
        String text = "PAUSED";

        int x = getXForCenteredText(text);
        int y = gp.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    private void drawDialogueScreen() {
        int x = 0;
        int y = gp.getTileSize() * 8;
        int width = gp.getScreenWidth();
        int height = gp.getTileSize() * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(arial_TILE);
        x += gp.getTileSize() / 2;
        y += gp.getTileSize() + (gp.getTileSize() / 2);

        if(currentDialogue[dialogueIndex] != null) {
            for(String line : currentDialogue[dialogueIndex].split("\n")) {
                g2.drawString(line, x, y);
                y += (int) (gp.getTileSize() * 1.5);
            }

        } else { // Return from Dialogue State
            dialogueIndex = 0;
            gp.setGameState(gp.getPlayState());
        }
    }

    private void drawCreditsScreen() {
        String text = "YOU WIN";

        int x = getXForCenteredText(text);
        int y = gp.getScreenHeight() / 2;

        g2.drawImage(creditScreen, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
        g2.drawString(text, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(255,255,255, 200);

        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35, 35);

        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.getScreenWidth() - length) / 2;
    }

    public String[] getCurrentDialogue() {
        return currentDialogue;
    }
    public void setCurrentDialogue(String[] currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
    public int getDialogueIndex() {
        return dialogueIndex;
    }
    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }

}
