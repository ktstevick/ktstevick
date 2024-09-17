package org.example.tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private BufferedImage image2;
    private boolean collision = false;
    private boolean overhead = false;

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage getImage2() {
        return image2;
    }
    public void setImage2(BufferedImage image2) {
        this.image2 = image2;
    }
    public boolean isCollision() {
        return collision;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public boolean isOverhead() {
        return overhead;
    }
    public void setOverhead(boolean overhead) {
        this.overhead = overhead;
    }
}
