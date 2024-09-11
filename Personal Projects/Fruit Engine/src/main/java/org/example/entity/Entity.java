package org.example.entity;

import org.example.Dialogue;
import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Entity {
    GamePanel gp;
    private UtilityTool uTool = new UtilityTool();

    private int worldX;
    private int worldY;
    private int speed;
    private String direction;
    private Rectangle solidArea = new Rectangle(0,0, 48, 48); // Defaults to one tile
    private int solidAreaDefaultX;
    private int solidAreaDefaultY;
    private boolean collisionOn = false;

    private BufferedImage portrait1;
    private BufferedImage portrait2;

    private BufferedImage up1;
    private BufferedImage up2;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;

    private BufferedImage pickup1;
    private BufferedImage holding1;

    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int actionLockCounter = 0;
    private ArrayList<Dialogue> objDialogue = new ArrayList<Dialogue>();
    private String dialogues[] = new String[20];
    private BufferedImage portraits[] = new BufferedImage[20];

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = getUTool().scaleImage(image, gp.getTileSize(), gp.getTileSize());
        } catch(IOException e) {
            System.out.println("Something happened! Error Code: 002");
        }

        return image;
    }

    public void update() {
        setAction();

        setCollisionOn(false);
        gp.getCollisionChecker().checkTile(this);
        gp.getCollisionChecker().checkObject(this, false);
        gp.getCollisionChecker().checkPlayer(this);

        if(!isCollisionOn()) {
            switch(getDirection()) {
                case "up":
                    setWorldY(getWorldY() - getSpeed());
                    break;
                case "down":
                    setWorldY(getWorldY() + getSpeed());
                    break;
                case "left":
                    setWorldX(getWorldX() - getSpeed());
                    break;
                case "right":
                    setWorldX(getWorldX() + getSpeed());
                    break;
            }
        }

        // ANIMATION
        setSpriteCounter(getSpriteCounter() + 1);
        if(getSpriteCounter() > 15) {
            if(getSpriteNum() == 1) {
                setSpriteNum(2);
            } else if(getSpriteNum() == 2){
                setSpriteNum(1);
            }
            setSpriteCounter(0);
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            switch (getDirection()) {
                case "up":
                    if (getSpriteNum() == 1) {
                        image = getUp1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getUp2();
                    }
                    break;
                case "down":
                    if (getSpriteNum() == 1) {
                        image = getDown1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getDown2();
                    }
                    break;
                case "left":
                    if (getSpriteNum() == 1) {
                        image = getLeft1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getLeft2();
                    }
                    break;
                case "right":
                    if (getSpriteNum() == 1) {
                        image = getRight1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getRight2();
                    }
                    break;

                case "pickup":
                    image = getPickup1();
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

            if(holding1 != null && getDirection().equals("pickup")) {
                g2.drawImage(holding1, screenX, screenY - gp.getTileSize(), gp.getTileSize(),gp.getTileSize(), null);
            } else {
                holding1 = null;
            }

                // DEBUG, draws collision
                if(gp.getKeyH().isDebugOn()) {
                    g2.setColor(Color.red);
                    g2.fillRect(screenX + getSolidArea().x, screenY + getSolidArea().y, getSolidArea().width, getSolidArea().height);
                }
            }
        }

    public void setAction() { }
    public void speak() { }

    public void turnToPlayer() {
        switch(gp.getPlayer().getDirection()) {
            case "up":
                setDirection("down");
                break;
            case "down":
                setDirection("up");
                break;
            case "left":
                setDirection("right");
                break;
            case "right":
                setDirection("left");
                break;
        }
    }

    public void turnRandomly(int frameCount) {
        setActionLockCounter(getActionLockCounter() + 1);

        if(getActionLockCounter() == frameCount) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                setDirection("up");
            }
            if (i > 25 && i <= 50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75 && i <= 100) {
                setDirection("right");
            }

            setActionLockCounter(0);
        }
    }

    public int getWorldX() {
        return worldX;
    }
    public void setWorldX(int x) {
        this.worldX = x;
    }
    public int getWorldY() {
        return worldY;
    }
    public void setWorldY(int y) {
        this.worldY = y;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
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
    public void setSolidAreaDefaultY(int getSolidAreaDefaultY) {
        this.solidAreaDefaultY = getSolidAreaDefaultY;
    }
    public boolean isCollisionOn() {
        return collisionOn;
    }
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    public int getActionLockCounter() {
        return actionLockCounter;
    }
    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public BufferedImage getPortrait1() {
        return portrait1;
    }
    public void setPortrait1(BufferedImage portrait1) {
        this.portrait1 = portrait1;
    }
    public BufferedImage getPortrait2() {
        return portrait2;
    }
    public void setPortrait2(BufferedImage portrait2) {
        this.portrait2 = portrait2;
    }
    public BufferedImage getUp1() {
        return up1;
    }
    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }
    public BufferedImage getUp2() {
        return up2;
    }
    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }
    public BufferedImage getDown1() {
        return down1;
    }
    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }
    public BufferedImage getDown2() {
        return down2;
    }
    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }
    public BufferedImage getLeft1() {
        return left1;
    }
    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }
    public BufferedImage getLeft2() {
        return left2;
    }
    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }
    public BufferedImage getRight1() {
        return right1;
    }
    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }
    public BufferedImage getRight2() {
        return right2;
    }
    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }
    public BufferedImage getPickup1() {
        return pickup1;
    }
    public BufferedImage getHolding1() {
        return holding1;
    }
    public void setHolding1(BufferedImage holding1) {
        this.holding1 = holding1;
    }
    public void setPickup1(BufferedImage pickup1) {
        this.pickup1 = pickup1;
    }
    public int getSpriteCounter() {
        return spriteCounter;
    }
    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }
    public int getSpriteNum() {
        return spriteNum;
    }
    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public UtilityTool getUTool() {
        return uTool;
    }
    public String[] getDialogues() {
        return dialogues;
    }
    public void setDialogues(String[] dialogues) {
        this.dialogues = dialogues;
    }
    public BufferedImage[] getPortraits() {
        return portraits;
    }
    public void setPortraits(BufferedImage[] portraits) {
        this.portraits = portraits;
    }

    public ArrayList<Dialogue> getObjDialogue() {
        return objDialogue;
    }
    public void setObjDialogue(ArrayList<Dialogue> objDialogue) {
        this.objDialogue = objDialogue;
    }
}
