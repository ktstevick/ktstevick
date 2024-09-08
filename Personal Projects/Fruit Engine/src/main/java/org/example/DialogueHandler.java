package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogueHandler {
    private final GamePanel gp;
    private Graphics2D g2;

    private String[] currentDialogue;
    private BufferedImage[] currentPortraits;
    private int dialogueIndex = 0;

    public DialogueHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void drawDialogue(Graphics2D g2) {
        this.g2 = g2;

        int x = 0;
        int y = gp.getTileSize() * 8;
        int width = gp.getScreenWidth();
        int height = gp.getTileSize() * 4;

        // Skips background draw if no corresponding portrait
        if(dialogueIndex < currentDialogue.length && currentPortraits[dialogueIndex] != null) {
            drawBackground();
        }
        drawPortrait(gp.getTileSize(), (gp.getTileSize() * 3));
        drawTextWindow(x, y, width, height);

        g2.setFont(gp.getPanelUI().getArial_TILE());
        x += gp.getTileSize() / 2;
        y += gp.getTileSize() + (gp.getTileSize() / 2);

        if(dialogueIndex < currentDialogue.length) {
            for(String line : currentDialogue[dialogueIndex].split("\n")) {
                g2.drawString(line, x, y);
                y += (int) (gp.getTileSize() * 1.5);
            }

        } else { // Return from Dialogue State
            dialogueIndex = 0;
            gp.setGameState(gp.getPlayState());
        }
    }

    public void drawTextWindow(int x, int y, int width, int height) {
        Color c = new Color(255,255,255, 200);

        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35, 35);

        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPortrait(int x, int y) {
        int HEIGHT = gp.getTileSize() * 5;
        int WIDTH = gp.getTileSize() * 5;

        if(dialogueIndex < currentDialogue.length) {
            g2.drawImage(currentPortraits[dialogueIndex], x, y, WIDTH, HEIGHT, null);
        }
    }

    public void drawBackground() { // Default background
        g2.setColor(new Color(0,0,0, 127));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
    }
    public void drawBackground(BufferedImage bgImage) { // Specific image
        g2.drawImage(bgImage, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
    }

    public String[] getCurrentDialogue() {
        return currentDialogue;
    }
    public void setCurrentDialogue(String[] currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
    public BufferedImage[] getCurrentPortraits() {
        return currentPortraits;
    }
    public void setCurrentPortraits(BufferedImage[] currentPortraits) {
        this.currentPortraits = currentPortraits;
    }
    public int getDialogueIndex() {
        return dialogueIndex;
    }
    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }

}
