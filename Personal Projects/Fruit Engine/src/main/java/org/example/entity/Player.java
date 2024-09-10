package org.example.entity;

import java.awt.*;

import org.example.GamePanel;
import org.example.KeyHandler;

public class Player extends Entity{
    KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    private final int DEFAULT_SPEED = 3;
    private boolean hasApple;
    private boolean hasBanana;
    private boolean hasCherry;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = (gp.getScreenWidth() / 2) - (gp.getTileSize() / 2);
        screenY = (gp.getScreenHeight() / 2) - (gp.getTileSize() / 2);

        Rectangle solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 12;
        solidArea.width = 32;
        solidArea.height = 32;

        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        setSolidArea(solidArea);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        setWorldX(gp.getTileSize() * 25);
        setWorldY(gp.getTileSize() * 25);
        setSpeed(DEFAULT_SPEED);
        setDirection("down");
    }

    public void getPlayerImage() {
        setUp1(setup("/player/nurse_up_1"));
        setUp2(setup("/player/nurse_up_2"));
        setDown1(setup("/player/nurse_down_1"));
        setDown2(setup("/player/nurse_down_2"));
        setLeft1(setup("/player/nurse_left_1"));
        setLeft2(setup("/player/nurse_left_2"));
        setRight1(setup("/player/nurse_right_1"));
        setRight2(setup("/player/nurse_right_2"));
        setPickup1(setup("/tiles/bush_0"));
    }

    public void update() {
        if (getActionLockCounter() == 0) {
            if (keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()) {
                // ASSIGN DIRECTION
                if (keyH.isUpPressed()) {
                    setDirection("up");
                } else if (keyH.isDownPressed()) {
                    setDirection("down");
                } else if (keyH.isLeftPressed()) {
                    setDirection("left");
                } else if (keyH.isRightPressed()) {
                    setDirection("right");
                }

                // SPEED
                if (keyH.isShiftPressed()) {
                    setSpeed(DEFAULT_SPEED * 2);
                } else {
                    setSpeed(DEFAULT_SPEED);
                }

                // CHECK COLLISION
                setCollisionOn(false);
                gp.getCollisionChecker().checkTile(this);

                // CHECK OBJECT
                int objIndex = gp.getCollisionChecker().checkObject(this, true);
                pickUpObject(objIndex);

                // CHECK NPC
                int npcIndex = gp.getCollisionChecker().checkEntity(this, gp.getNpc());
                interactNPC(npcIndex);

                // ACTUAL MOVEMENT
                if (!isCollisionOn()) {
                    switch (getDirection()) {
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

                        case "pickup":
                            setActionLockCounter(getActionLockCounter() + 1);
                            break;
                    }
                }

                // ANIMATION
                setSpriteCounter(getSpriteCounter() + 1);
                if (getSpriteCounter() > 15) {
                    if (getSpriteNum() == 1) {
                        setSpriteNum(2);
                    } else if (getSpriteNum() == 2) {
                        setSpriteNum(1);
                    }
                    setSpriteCounter(0);
                }
            } else {
                setSpriteNum(1);
            }
        }

        // OTHER ACTIONS
        if (!getDirection().equals("up") && !getDirection().equals("down") && !getDirection().equals("left") && !getDirection().equals("right")) {
            setActionLockCounter(getActionLockCounter() + 1);

            if (getActionLockCounter() > 90) {
                setDirection("down");
                setActionLockCounter(0);
            }
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            String objName = gp.getObjArray()[i].getName();

            gp.getPlayer().setDirection("pickup");
            gp.getPlayer().setHolding1(gp.getObjArray()[i].getImage());

            switch(objName) {
                case "Apple":
                    gp.getPlayer().setHasApple(true);
                    gp.getObjArray()[i] = null;
                    break;
                case "Banana":
                    gp.getPlayer().setHasBanana(true);
                    gp.getObjArray()[i] = null;
                    break;
                case "Cherry":
                    gp.getPlayer().setHasCherry(true);
                    gp.getObjArray()[i] = null;
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if(i != 999) {
            gp.setGameState(gp.getDialogueState());
            gp.getNpc()[i].speak();
        }
    }

    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }
    public boolean isHasApple() { return hasApple; }
    public void setHasApple(boolean hasApple) { this.hasApple = hasApple; }
    public boolean isHasBanana() { return hasBanana; }
    public void setHasBanana(boolean hasBanana) { this.hasBanana = hasBanana; }
    public boolean isHasCherry() { return hasCherry; }
    public void setHasCherry(boolean hasCherry) { this.hasCherry = hasCherry; }
}
