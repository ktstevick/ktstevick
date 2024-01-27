package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Granny extends Entity {

    public NPC_Granny(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/grannyup1", gp.TILE_SIZE, gp.TILE_SIZE);
        up2 = setup("/npc/grannyup2", gp.TILE_SIZE, gp.TILE_SIZE);
        down1 = setup("/npc/grannydown1", gp.TILE_SIZE, gp.TILE_SIZE);
        down2 = setup("/npc/grannydown2", gp.TILE_SIZE, gp.TILE_SIZE);
        left1 = setup("/npc/grannyleft1", gp.TILE_SIZE, gp.TILE_SIZE);
        left2 = setup("/npc/grannyleft2", gp.TILE_SIZE, gp.TILE_SIZE);
        right1 = setup("/npc/grannyright1", gp.TILE_SIZE, gp.TILE_SIZE);
        right2 = setup("/npc/grannyright2", gp.TILE_SIZE, gp.TILE_SIZE);
    }

    public void setDialogue() {
        dialogues[0] = "Hello, lass.";
        dialogues[1] = "So you've come to this island to find \nthe treasure?";
        dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for taking on an adventure.";
        dialogues[3] = "Best of luck!";
    }

    public void setAction() { // Essentially it's AI, here for overriding purposes mostly
        actionLockCounter++;

        if(actionLockCounter == 120) {
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
    }

    public void speak() {
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction) {
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }
}
