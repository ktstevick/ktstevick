package org.example;

import java.awt.image.BufferedImage;

public class Dialogue {
    private BufferedImage background; // Ultimately also array
    private String[] messages;
    private BufferedImage[] portraitsLeft;
    private BufferedImage[] portraitsRight;

    private boolean isEnding = false;

    public Dialogue(BufferedImage background, String[] messages, BufferedImage[] portraitsLeft, BufferedImage[] portraitsRight) {
        this.background = background;
        this.messages = messages;
        this.portraitsLeft = portraitsLeft;
        this.portraitsRight = portraitsRight;
    }

    public String[] getMessages() {
        return messages;
    }
    public void setMessages(String[] messages) {
        this.messages = messages;
    }
    public BufferedImage[] getPortraitsLeft() {
        return portraitsLeft;
    }
    public void setPortraitsLeft(BufferedImage[] portraitsLeft) {
        this.portraitsLeft = portraitsLeft;
    }
    public BufferedImage[] getPortraitsRight() {
        return portraitsRight;
    }
    public void setPortraitsRight(BufferedImage[] portraitsRight) {
        this.portraitsRight = portraitsRight;
    }
    public BufferedImage getBackground() {
        return background;
    }
    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public boolean isEnding() {
        return isEnding;
    }
    public void setIsEnding(boolean ending) {
        isEnding = ending;
    }
}
