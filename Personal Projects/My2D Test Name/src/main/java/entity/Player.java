package entity;

import jdk.jshell.execution.Util;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyH;

    public final int SCREEN_X;
    public final int SCREEN_Y;
    int standCounter;
    boolean attacking = false;
    public boolean attackCancelled;
    public boolean fromBattleState;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public String[] BATTLE_MENU_OPTIONS = new String[]{"Attack", "Item", "Retreat"};
    public final int inventoryMax = 20;

    public final String playerName = "KURT"; // Will be variable in the future, spaces to help formatting
    public String displayName = "K U R T";

    // BATTLE BOOLEANS
    public boolean battleMenuOn = false;
    public boolean attemptAttack = false;
    public boolean attemptRetreat = false;
    public boolean battleItemMenu = false;
    public boolean attemptUseItem = false;
    public boolean usedItem = false;

    // Constructor
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        SCREEN_X = (gp.SCREEN_WIDTH / 2) - (gp.TILE_SIZE / 2);
        SCREEN_Y = (gp.SCREEN_HEIGHT / 2) - (gp.TILE_SIZE / 2);

        // Collision hurt box, slightly smaller than full tile at the moment
        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.TILE_SIZE - 36;
        solidArea.height = gp.TILE_SIZE - 24; // Different so there's no offset when approaching a tile from above

//        attackArea.width = 36;
//        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.TILE_SIZE * 15; // Set starting coordinates
        worldY = gp.TILE_SIZE * 8;
        speed = 3;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1; // The more strength Player has, the more damage given
        dexterity = 1; // The more dexterity possessed, the less damage received
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);

        attack = getAttack(); // Total attack value is decided by strength and weapon
        defense = getDefense(); // Likewise, defense value decided by dexterity and shield
    }

    public void setItems() {
        inventory.add(new OBJ_Cello(gp));
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Banana(gp));
        inventory.add(new OBJ_Cello(gp));

        // Making first three visible in battle as default
        inventory.get(0).isBattleMenuVisible = true;
        inventory.get(1).isBattleMenuVisible = true;
        inventory.get(2).isBattleMenuVisible = true;
    }
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup("/player/nursebackstandleftstep", gp.TILE_SIZE, gp.TILE_SIZE);
        up2 = setup("/player/nursebackstandrightstep", gp.TILE_SIZE, gp.TILE_SIZE);
        down1 = setup("/player/nursefrontstandleftstep", gp.TILE_SIZE, gp.TILE_SIZE);
        down2 = setup("/player/nursefrontstandrightstep", gp.TILE_SIZE, gp.TILE_SIZE);
        left1 = setup("/player/nurseleftstandleftstep", gp.TILE_SIZE, gp.TILE_SIZE);
        left2 = setup("/player/nurseleftstandrightstep", gp.TILE_SIZE, gp.TILE_SIZE);
        right1 = setup("/player/nurserightstandleftstep", gp.TILE_SIZE, gp.TILE_SIZE);
        right2 = setup("/player/nurserightstandrightstep", gp.TILE_SIZE, gp.TILE_SIZE);
    }

    public void getPlayerAttackImage() {
        if(currentWeapon.type == TYPE_SWORD) {
            attackUp1 = setup("/player/nursebackstandstab1", gp.TILE_SIZE, gp.TILE_SIZE * 2);
            attackUp2 = setup("/player/nursebackstandstab2", gp.TILE_SIZE, gp.TILE_SIZE * 2);
            attackDown1 = setup("/player/nursefrontstandstab1", gp.TILE_SIZE, gp.TILE_SIZE * 2);
            attackDown2 = setup("/player/nursefrontstandstab2", gp.TILE_SIZE, gp.TILE_SIZE * 2);
            attackLeft1 = setup("/player/nurseleftstandstab1", gp.TILE_SIZE * 2, gp.TILE_SIZE);
            attackLeft2 = setup("/player/nurseleftstandstab2", gp.TILE_SIZE * 2, gp.TILE_SIZE);
            attackRight1 = setup("/player/nurserightstandstab1", gp.TILE_SIZE * 2, gp.TILE_SIZE);
            attackRight2 = setup("/player/nurserightstandstab2", gp.TILE_SIZE * 2, gp.TILE_SIZE);
        }
        if(currentWeapon.type == TYPE_AXE) {
            attackUp1 = setup("/player/nursebackstandstab1", gp.TILE_SIZE, gp.TILE_SIZE * 3);
            attackUp2 = setup("/player/nursebackstandstab2", gp.TILE_SIZE, gp.TILE_SIZE * 3);
            attackDown1 = setup("/player/nursefrontstandstab1", gp.TILE_SIZE, gp.TILE_SIZE * 3);
            attackDown2 = setup("/player/nursefrontstandstab2", gp.TILE_SIZE, gp.TILE_SIZE * 3);
            attackLeft1 = setup("/player/nurseleftstandstab1", gp.TILE_SIZE * 3, gp.TILE_SIZE);
            attackLeft2 = setup("/player/nurseleftstandstab2", gp.TILE_SIZE * 3, gp.TILE_SIZE);
            attackRight1 = setup("/player/nurserightstandstab1", gp.TILE_SIZE * 3, gp.TILE_SIZE);
            attackRight2 = setup("/player/nurserightstandstab2", gp.TILE_SIZE * 3, gp.TILE_SIZE);
        }
    }

    public void update() { // This first conditional keeps the sprite from updating without a key press

            if (attacking) {
                attacking();
            } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

                // CHECK DIRECTION
                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                // CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                // CHECK NPC COLLISION
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                // CHECK MONSTER COLLISION
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                contactMonster(monsterIndex);

                // CHECK EVENT
                gp.eHandler.checkEvent();

                // IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (!collisionOn && !keyH.enterPressed) {
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

                if (keyH.enterPressed && !attackCancelled) { // Allows us to trigger events with Enter key without attacking
                    gp.playSoundEffect(7);
                    attacking = true;
                    spriteCounter = 0;
                }

                attackCancelled = false;
                gp.keyH.enterPressed = false;

                spriteCounter++;
                // This changes the spriteNum every X intervals, in this case, 12 intervals at 60 FPS
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }

            }

            if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30) { // One at a time
                // SET DEFAULT COORDINATES, DIRECTION, AND USER
                projectile.set(worldX, worldY, direction, true, this);

                // ADD TO LIST
                gp.projectileList.add(projectile);

                shotAvailableCounter = 0;

                // Sound effect goes here
            }

            // Outside of key if statement so it counts down even without keyboard input
            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 45) { // 60 FPS, one second of i-frames
                    invincible = false;
                    invincibleCounter = 0;
                }
            }

            if(shotAvailableCounter < 30) { // Cooldown
                shotAvailableCounter++;
            }
        }


    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= 5) { // First image shown in first five frames
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25) { // Second image shown for next twenty
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.width = attackArea.height;

            // Check monster collision with the updated worldX, worldY, and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            // Restore original state
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25) { // Animation ends and attack state changed after 25 frames
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            String text;
            if(inventory.size() != inventoryMax) {
                inventory.add(gp.obj[i]);
                gp.playSoundEffect(1);
                text = "Got a " + gp.obj[i].name + "!";
            } else {
                text = "No room for this!";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null;
        }
    }

    public void interactNPC(int i) {
        if(keyH.enterPressed) {
            if (i != 999) {
                attackCancelled = true;
                gp.gameState = gp.DIALOGUE_STATE;
                gp.npc[i].speak();

            }
        }
    }

    public void contactMonster(int i) {
        if(i != 999) {
            if(gp.monster[i].turnBased && !gp.monster[i].invincible) {
                gp.playSoundEffect(6);
                gp.gameState = gp.BATTLE_STATE;

                gp.player.invincible = true; // Grants and resets invincibility throughout the battle
                gp.player.invincibleCounter = 0;

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
                gp.ui.battleMonsterID = i;
            }

            // Basic damage and response. This will NEED to be developed further
            else {
                if (!invincible) {
                    gp.playSoundEffect(6);

                    int damage = gp.monster[i].attack - defense;
                    if(damage < 0) {
                        damage = 0;
                    }

                    life -= 1;
                    invincible = true;
                }
            }
        }
    }

    public void damageMonster(int i, int attack) {
        if(i != 999) {
            // This conditional just prevents us from killing turn based enemies before the Battle State is initiated
            if(!gp.monster[i].turnBased) {
                if (!gp.monster[i].invincible) {
                    gp.playSoundEffect(5);

                    int damage = attack - gp.monster[i].defense;
                    if(damage < 0) {
                        damage = 0;
                    }

                    gp.monster[i].life -= damage;

                    gp.monster[i].invincible = true;
                    gp.monster[i].damageReaction();

                    if (gp.monster[i].life <= 0) {
                        gp.monster[i].dying = true;
                        gp.ui.addMessage("The " + gp.monster[i].name + " was defeated!");
                        gp.ui.addMessage("Exp + " + gp.monster[i].exp + "!");
                        exp += gp.monster[i].exp;

                        checkLevelUp();
                    }

                    else { gp.ui.addMessage(damage + " damage!"); } // Prevents irksome 'double text' on kill
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2; // Two for one full heart
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSoundEffect(2);
            gp.gameState = gp.DIALOGUE_STATE;
            gp.ui.currentDialogue = "Level up! Welcome to level " + level + ".";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if(itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == TYPE_SWORD || selectedItem.type == TYPE_AXE) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage(); // Updates animations
            }
            if(selectedItem.type == TYPE_SHIELD) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == TYPE_CONSUMABLE) {
                // It's working from here. This was originally a separate method of Entity but it wouldn't work
                gp.gameState = gp.DIALOGUE_STATE;
                if(life < maxLife) {
                    gp.ui.currentDialogue = inventory.get(itemIndex).useMessage;
                    life += 5;
                    if (gp.player.life > gp.player.maxLife) {
                        gp.player.life = gp.player.maxLife;
                    }
                    gp.playSoundEffect(2);
                    inventory.remove(itemIndex);
                } else {
                    gp.ui.currentDialogue = "You're already at full health!";
                }

            }
        }
    }

    public void inBattleSelectItem(int row) {

        // Healing items only right now
        if(life < maxLife) {
            gp.ui.currentDialogue = "Recovered 5HP!";
            life += 5;
            if (gp.player.life > gp.player.maxLife) {
                gp.player.life = gp.player.maxLife;
            }
            gp.playSoundEffect(2);

            inventory.remove(gp.ui.visibleBattleInventory.get(row));

        } else {
            gp.ui.currentDialogue = "You're already at full health!";
        }
    }

    public boolean isItemUsable(int type) {
        boolean usable = false;

        // Healing item check
        if(type == 1) {
            if(life < maxLife) {
                usable = true;
            }
        }

        return usable;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = SCREEN_X;
        int tempScreenY = SCREEN_Y;

        switch(direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }

                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if(attacking) {
                    tempScreenY = SCREEN_Y - gp.TILE_SIZE; // Shifting to allow the image to be displayed properly
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }

                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if(!attacking) {
                    if(spriteNum == 1) { image = down1; }
                    if(spriteNum == 2) { image = down2; }
                }
                if(attacking) {
                    if(spriteNum == 1) { image = attackDown1; }
                    if(spriteNum == 2) { image = attackDown2; }
                }
                break;
            case "left":
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if(attacking) {
                    tempScreenX = SCREEN_X - gp.TILE_SIZE;
                if (spriteNum == 1) {
                    image = attackLeft1;
                }
                if (spriteNum == 2) {
                    image = attackLeft2;
                }
                }
                break;
            case "right":
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if(attacking) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }

        if(invincible) { // Visual effects for invincibility
            invincibleAnimation(g2);
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible Counter: " + invincibleCounter, 12, 96);
    }
}
