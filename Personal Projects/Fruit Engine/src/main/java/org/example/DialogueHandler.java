package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogueHandler {
    private final GamePanel gp;
    private Graphics2D g2;
    private Dialogue currentD;
    private int dialogueIndex = 0;

    private int FULL_FADE_MAX = 250;
    private int PART_FADE_MAX = 180;
    private int fadeCounter = 0;
    private boolean inFullTransition = true;

    public DialogueHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void drawDialogue(Graphics2D g2) {
        this.g2 = g2;

        // Text window
        int x = 0;
        int y = gp.getTileSize() * 8;
        int width = gp.getScreenWidth();
        int height = gp.getTileSize() * 4;

        // Third character to check all three types on the same load

        // Background
        if(dialogueIndex < currentD.getMessages().length && currentD.getPortraitsLeft()[dialogueIndex] != null) {
            // Background check
            if(currentD.getBackground() == null) {
                if(fadeCounter < PART_FADE_MAX) {
                    fadeCounter += (PART_FADE_MAX / 30); // Arbitrary interval
                }

                drawBackground(fadeCounter);
                inFullTransition = false;

            } else {
                if(fadeCounter < FULL_FADE_MAX && inFullTransition) {
                    fadeCounter += (FULL_FADE_MAX / 50);
                    drawBackground(fadeCounter);

                } else if (fadeCounter == FULL_FADE_MAX) {
                    inFullTransition = false; // !!!
                }

                if(!inFullTransition) {
                    drawBackground(currentD.getBackground());
                }
            }
        } else {
            inFullTransition = false;
        }

        // Regular draw
        if(!inFullTransition) {
            drawPortrait(gp.getTileSize(), (gp.getTileSize() * 3));
            drawTextWindow(x, y, width, height);
            drawText(x, y);

            if(fadeCounter > 0) { // !!!
                System.out.println(fadeCounter);
                fadeCounter -= (FULL_FADE_MAX / 50);
                drawBackground(fadeCounter);
            }
        }

        // Exit Dialogue State
        if(dialogueIndex >= currentD.getMessages().length) {
            dialogueIndex = 0;
            fadeCounter = 0;
            inFullTransition = true;

            gp.setGameState(gp.getPlayState());
        }
    }

    private void drawTextWindow(int x, int y, int width, int height) {
        Color c = new Color(255,255,255, 200);

        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35, 35);

        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawPortrait(int x, int y) {
        int HEIGHT = gp.getTileSize() * 5;
        int WIDTH = gp.getTileSize() * 5;

        if(dialogueIndex < currentD.getMessages().length) {
            g2.drawImage(currentD.getPortraitsLeft()[dialogueIndex], x, y, WIDTH, HEIGHT, null);
        }
    }

    private void drawBackground(int alpha) { // Default background
        g2.setColor(new Color(0,0,0, alpha));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
    }
    private void drawBackground(BufferedImage bgImage) { // Specific image
        g2.drawImage(bgImage, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
    }

    private void drawText(int winX, int winY) {
        int x = winX + gp.getTileSize() / 2;
        int y = winY + gp.getTileSize() + (gp.getTileSize() / 2);

        g2.setFont(gp.getPanelUI().getArial_TILE());

        if(dialogueIndex < currentD.getMessages().length) {
            for (String line : currentD.getMessages()[dialogueIndex].split("\n")) {
                g2.drawString(line, x, y);
                y += (int) (gp.getTileSize() * 1.5);
            }
        }
    }

    public int getDialogueIndex() {
        return dialogueIndex;
    }
    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }
    public Dialogue getCurrentD() {
        return currentD;
    }
    public void setCurrentD(Dialogue currentD) {
        this.currentD = currentD;
    }
}
