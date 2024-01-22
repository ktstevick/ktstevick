package entity;

import main.GamePanel;
import main.UtilityTool;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

// Parent class for any character in the game
public class Entity {
    GamePanel gp;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stun;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48); // Default collision box
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[20];

    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean isTakingTurn = false;
    public boolean turnBased = false; // THIS is for turn based enemies only

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int defaultSpeed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    // ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public String useMessage = "";

    // TYPE

    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int TYPE_PLAYER = 0;
    public final int TYPE_NPC = 1;
    public final int TYPE_MONSTER = 2;
    public final int TYPE_SWORD = 3;
    public final int TYPE_AXE = 4;
    public final int TYPE_SHIELD = 5;
    public final int TYPE_CONSUMABLE = 6;

    public int monsterIndex;
    public String idleMessage;
    public boolean battleItem;
    public boolean isBattleMenuVisible;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }
    public void escapeReaction() { }
    public void damageReaction() { }
    public void speak() { }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);

        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        // THIS BLOCK is where Monster making contact with Player interactions are handled by the way
        if(this.type == TYPE_MONSTER && contactPlayer) {
            // I lied I actually need some of this still
            if(!gp.player.invincible) {
                this.direction = reverseDirection(this.direction);
                gp.player.direction = this.direction;

                if(!this.turnBased) {
                    gp.playSoundEffect(6);

                    int damage = attack - gp.player.defense;
                    if(damage < 0) {
                        damage = 0;
                    }

                    gp.player.invincible = true;
                    gp.player.life -= damage;
                }
                if(this.turnBased) {
                    gp.playSoundEffect(6);
                    gp.gameState = gp.BATTLE_STATE;
                    gp.player.invincible = true;

                   gp.ui.battleMonsterID = this.monsterIndex;

                    this.invincible = true;
                    escapeReaction();
                }
            }

            gp.player.invincibleCounter = 0; // This resets the i-frames so we have max at the end of the battle
        }

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 30) { // 30 FPS, one second of i-frames
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
        int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

        if      (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) {

            switch(direction) {
                case "up":
                    if(spriteNum == 1) {
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1) {
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1) {
                        image = left1;
                    }
                    if(spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1) {
                        image = right1;
                    }
                    if(spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }

            // Monster HP bar
            if(type == 2 && hpBarOn && maxLife != life) {
                double oneScale = (double)(gp.TILE_SIZE - 12) / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX + 3, screenY - 9, gp.TILE_SIZE - 6, 12);

                g2.setColor(Color.red);
                g2.fillRect(screenX + 6, screenY - 6, (int)hpBarValue, 6);

                hpBarCounter++;

                if(hpBarCounter > 180) { // 60 FPS
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if(invincible) { // Visual effects for invincibility
                hpBarOn = true;
                hpBarCounter = 0;
                invincibleAnimation(g2);
            }
            if(dying) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);

            // Reset alpha
            changeAlpha(g2, 1f);
        }
    }

    public void invincibleAnimation(Graphics2D g2) {
        int i = 5; // Frame interval

        // Utilizes the already running invincibleCounter
        if(invincibleCounter <= i) { changeAlpha(g2, 1f); }
        if(invincibleCounter > i && invincibleCounter <= i * 2) { changeAlpha(g2, 0.3f); }
        if(invincibleCounter > i * 2 && invincibleCounter <= i * 3) { changeAlpha(g2, 1f); }
        if(invincibleCounter > i * 3 && invincibleCounter <= i * 4) { changeAlpha(g2, 0.3f); }
        if(invincibleCounter > i * 4 && invincibleCounter <= i * 5) { changeAlpha(g2, 1f); }
        if(invincibleCounter > i * 5 && invincibleCounter <= i * 6) { changeAlpha(g2, 0.3f); }
        if(invincibleCounter > i * 6) { changeAlpha(g2, 0f); }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5; // Frame interval

        // For a more elaborate death animation, we'd simply change the image file path instead of the transparency value
        if(dyingCounter <= i) { changeAlpha(g2, 1f); }
        if(dyingCounter > i && dyingCounter <= i * 2) { changeAlpha(g2, 0f); }
        if(dyingCounter > i * 2 && dyingCounter <= i * 3) { changeAlpha(g2, 1f); }
        if(dyingCounter > i * 3 && dyingCounter <= i * 4) { changeAlpha(g2, 0f); }
        if(dyingCounter > i * 4 && dyingCounter <= i * 5) { changeAlpha(g2, 1); }
        if(dyingCounter > i * 5 && dyingCounter <= i * 6) { changeAlpha(g2, 0f); }
        if(dyingCounter > i * 6 && dyingCounter <= i * 7) { changeAlpha(g2, 1f); }
        if(dyingCounter > i * 7 && dyingCounter <= i * 8) { changeAlpha(g2, 0); }
        if(dyingCounter > i * 8) {
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            // :3
        }

        return image;
    }

    public String reverseDirection(String currentDirection) {
        String updatedDirection = "";

        if(currentDirection.equals("up")) { updatedDirection = "down"; }
        if(currentDirection.equals("down")) { updatedDirection = "up"; }
        if(currentDirection.equals("left")) { updatedDirection = "right"; }
        if(currentDirection.equals("right")) { updatedDirection = "left"; }

        return updatedDirection;
    }

    public void use(Entity entity) {

    }
}
