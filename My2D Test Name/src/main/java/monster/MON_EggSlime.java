package monster;
import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;
public class MON_EggSlime extends Entity {


    GamePanel gp;
    public int defaultSpeed = 1;
    public MON_EggSlime(GamePanel gp, int monsterId) {
        // THIS is probably the best place to but variable monster damage moving forward
        super(gp);
        this.gp = gp;
        this.monsterIndex = monsterId;

        turnBased = true;
        idleMessage = "Good morning, sunshine!";

        type = TYPE_MONSTER;
        name = "Egg Slime";
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        exp = 2;
        attack = 2;

        solidArea.x = 3;
        solidArea.y = 12;
        solidArea.width = 42;
        solidArea.height = 36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public BufferedImage getPortrait() {
        return setup("/monster/eggslime1", gp.TILE_SIZE * 2, gp.TILE_SIZE *2);
    }

    public void getImage() {
        up1 = setup("/monster/eggslime1", gp.TILE_SIZE, gp.TILE_SIZE);
        up2 = setup("/monster/eggslime2", gp.TILE_SIZE, gp.TILE_SIZE);
        down1 = setup("/monster/eggslime1", gp.TILE_SIZE, gp.TILE_SIZE);
        down2 = setup("/monster/eggslime2", gp.TILE_SIZE, gp.TILE_SIZE);
        left1 = setup("/monster/eggslime1", gp.TILE_SIZE, gp.TILE_SIZE);
        left2 = setup("/monster/eggslime2", gp.TILE_SIZE, gp.TILE_SIZE);
        right1 = setup("/monster/eggslime1", gp.TILE_SIZE, gp.TILE_SIZE);
        right2 = setup("/monster/eggslime2", gp.TILE_SIZE, gp.TILE_SIZE);
    }

    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 120) { // 60 FPS
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Picks number from 1 to 100 as opposed to 0 to 99

            // Self-proclaimed 'Simplest AI Ever'
            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

        if(actionLockCounter == 30) {
            speed = defaultSpeed; // Resets monster speed after one second
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        speed += 2; // Boost speed to run away
        direction = gp.player.direction; // Changes direction and moves away from Player
    }


}
