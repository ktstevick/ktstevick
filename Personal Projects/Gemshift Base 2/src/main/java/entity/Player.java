package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int SCREEN_X;
    public final int SCREEN_Y;
    public int hasKey = 0; // Represents how many "keys" the player has

    // Constructor
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        SCREEN_X = (gp.SCREEN_WIDTH / 2) - (gp.TILE_SIZE / 2);
        SCREEN_Y = (gp.SCREEN_HEIGHT / 2) - (gp.TILE_SIZE / 2);

        // Collision hurt box, slightly smaller than full tile at the moment
        solidArea = new Rectangle();
        solidArea.x = 18;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.TILE_SIZE - 36;
        solidArea.height = gp.TILE_SIZE - 18; // Different so there's no offset when approaching a tile from above

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.TILE_SIZE * 15; // Set starting coordinates
        worldY = gp.TILE_SIZE * 8;

//        worldX = gp.TILE_SIZE * 42; // Debug set
//        worldY = gp.TILE_SIZE * 22;

        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try { // I'll have to use more deliberate naming conventions moving forward, this is difficult to read at a glance
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/nursebackstandrightstep.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/nursebackstandleftstep.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/nursefrontstandleftstep.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/nursefrontstandrightstep.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/nurseleftstandleftstep.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/nurseleftstandrightstep.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/nurserightstandleftstep.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/nurserightstandrightstep.png"));

        } catch (IOException e) {
            // Eating it
        }


    }
    public void update() { // This first conditional keeps the sprite from updating without a key press
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
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

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
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
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) { // Means we didn't touch any object (999 being nothing)
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Banana": // Using banana item since there is no key object assets yet
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("This looks like a key!");
                    break;
                case "Door": // Solid object that disappears conditionally
                    if(hasKey > 0) {
                        gp.playSoundEffect(4);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("I'm in!");
                    }
                    else {
                        gp.ui.showMessage("Locked. If only I had a key...");
                    }
                    break;
                case "Cello": // Pretending these are speed-boosting Boots
                    gp.playSoundEffect(3);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("*High School Musical Reference*");
                    break;
                case "Orb": // This ENDS the game
                    gp.ui.gameFinished = true;
                    gp.stopMusic();;
                    gp.playSoundEffect(2);
                    break;
            }
        }
    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.TILE_SIZE, gp.TILE_SIZE);

        BufferedImage image = null;

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

        g2.drawImage(image, SCREEN_X, SCREEN_Y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
