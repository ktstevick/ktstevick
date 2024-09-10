package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogueHandler {
    private final GamePanel gp;
    private Graphics2D g2;
    private Dialogue currentD;
    private int dialogueIndex = 0;

    private final int FULL_FADE_MAX = 250;
    private final int PART_FADE_MAX = 180;
    private int fadeCounter = 0;
    private boolean initialFade = true;
    private boolean leavingDialogue = false;

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

        // Am I drawing an INITIAL FADE TO BLACK?
        if (currentD.getBackground() == null) {
            initialFade = false;
        }

        if (initialFade) {
            drawBackground(fadeCounter);

            if (fadeCounter < FULL_FADE_MAX) {
                fadeCounter += (FULL_FADE_MAX / 50);
            } else {
                initialFade = false;
            }
        }

        if (!initialFade && !leavingDialogue) {
            // Am I ONLY drawing TEXT?
            if (currentD.getPortraitsLeft()[0] != null || currentD.getPortraitsRight()[0] != null) {
                // Am I drawing a DEFAULT BACKGROUND?
                if (fadeCounter < PART_FADE_MAX && currentD.getBackground() == null) {
                    fadeCounter += (PART_FADE_MAX / 30);
                }
                drawBackground(fadeCounter);

                // Am I drawing a BACKGROUND IMAGE?
                drawBackground(currentD.getBackground());

                // Am I drawing PORTRAITS?
                drawPortrait(gp.getTileSize(), (gp.getTileSize() * 3));
            }

            // Am I drawing TEXT?
            drawTextWindow(x, y, width, height);
            drawText(x, y);

            // Am I drawing a FADE IN?
            if (currentD.getBackground() != null) {
                drawBackground(fadeCounter);

                if (fadeCounter > 0) {
                    fadeCounter -= (FULL_FADE_MAX / 50);
                }
            }
        }

        // Am I LEAVING DIALOGUE STATE?
        if (dialogueIndex >= currentD.getMessages().length) {
            // FADE TO BLACK
            if(currentD.getBackground() != null) {
                if(fadeCounter < FULL_FADE_MAX) {
                    fadeCounter += 10; // Offset
                } else {
                    leavingDialogue = true;
                }

            } else {
                leavingDialogue = true;
            }

            // GENERAL FADE
            if(leavingDialogue) {
                if (fadeCounter > 0) {
                    drawBackground(fadeCounter);
                    fadeCounter -= 15;
                    System.out.println("You made it!");
                    System.out.println(fadeCounter);
                } else if (fadeCounter == 0) {
                    // EXIT DIALOGUE STATE
                    gp.setGameState(gp.getPlayState());

                    dialogueIndex = 0;
                    initialFade = true;
                    leavingDialogue = false;
                }
            }
        }
    }

    private void drawTextWindow(int x, int y, int width, int height) {
        Color c = new Color(255, 255, 255, 200);

        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawPortrait(int x, int y) {
        int HEIGHT = gp.getTileSize() * 5;
        int WIDTH = gp.getTileSize() * 5;

        if (dialogueIndex < currentD.getMessages().length) {
            g2.drawImage(currentD.getPortraitsLeft()[dialogueIndex], x, y, WIDTH, HEIGHT, null);
            g2.drawImage(currentD.getPortraitsRight()[dialogueIndex], x + (gp.getTileSize() * 8), y, WIDTH, HEIGHT, null);
        }
    }

    private void drawBackground(int alpha) { // Default background
        g2.setColor(new Color(0, 0, 0, alpha));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
    }

    private void drawBackground(BufferedImage bgImage) { // Specific image
        g2.drawImage(bgImage, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
    }

    private void drawText(int winX, int winY) {
        int x = winX + gp.getTileSize() / 2;
        int y = winY + gp.getTileSize() + (gp.getTileSize() / 2);

        g2.setFont(gp.getPanelUI().getArial_TILE());

        if (dialogueIndex < currentD.getMessages().length) {
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
