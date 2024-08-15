package org.example.object;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.example.GamePanel;
import org.example.UtilityTool;

public class SuperObject {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX;
    private int worldY;
    private Rectangle solidArea = new Rectangle(0,0,48,48); // Default Tile Size
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;
    private UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if(     worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }


    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isCollision() {
        return collision;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public int getWorldX() {
        return worldX;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
    public UtilityTool getUTool() {
        return uTool;
    }
    public void setUTool(UtilityTool uTool) {
        this.uTool = uTool;
    }


}
