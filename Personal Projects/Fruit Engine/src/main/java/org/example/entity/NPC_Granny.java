package org.example.entity;

import org.example.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Granny extends Entity {
    private boolean mentionedApple = false;
    private boolean mentionedBanana = false;
    private boolean mentionedCherry = false;

    public NPC_Granny(GamePanel gp) {
        super(gp);

        setDirection("down");
        setSpeed(1);

        getImage();
        setDialogue();
    }

    public void getImage() {
        setPortrait1(setup("/npc/granny_portrait_1"));
        setPortrait2(setup("/npc/granny_portrait_2"));

        setUp1(setup("/npc/granny_up_1"));
        setUp2(setup("/npc/granny_up_2"));
        setDown1(setup("/npc/granny_down_1"));
        setDown2(setup("/npc/granny_down_2"));
        setLeft1(setup("/npc/granny_left_1"));
        setLeft2(setup("/npc/granny_left_2"));
        setRight1(setup("/npc/granny_right_1"));
        setRight2(setup("/npc/granny_right_2"));
    }

    public void setDialogue() {
        String dialogues[] = new String[10];
        BufferedImage portraits[] = new BufferedImage[10];

        dialogues[0] = "Hello! So, you've arrived on \nthis island at last.";
        portraits[0] = getPortrait1();
        dialogues[1] = "When you have all three fruits, \ncome back.";
        portraits[1] = getPortrait1();
        dialogues[2] = "Have fun exploring, but be \nsafe.";
        portraits[2] = null;
        dialogues[3] = "My dialogue loop is about to \nreset. It was nice chatting!";
        portraits[3] = getPortrait2();

        // Found items
        dialogues[4] = "You know what they say about \nan apple a day!";
        portraits[4] = null;

        dialogues[5] = "Why does banana candy suck \nso much anyways?";
        portraits[5] = getPortrait1();

        dialogues[6] = "The developer borrowed that \ncherry sprite from Pac-Man.";
        portraits[6] = getPortrait1();

        // Item combinations
        dialogues[7] = "Only the Apple left. Head \nNorth!";
        portraits[7] = getPortrait2();

        dialogues[8] = "Only the Banana left. Head \nWest!";
        portraits[8] = getPortrait2();

        dialogues[9] = "Only the Cherry left. Head \nEast!";
        portraits[9] = getPortrait2();

        setDialogues(dialogues);
        setPortraits(portraits);
    }

    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);

        if(getActionLockCounter() == 180) {
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

    public void speak() {
        // DEFAULT
        gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(0, 3));
        gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(0, 3));

        // CONDITIONAL DIALOGUE
        if(gp.getPlayer().isHasApple() && !mentionedApple) {
            if(gp.getPlayer().isHasBanana()) {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(9));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(9));

            } else if(gp.getPlayer().isHasCherry()) {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(8));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(8));

            } else {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(4));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(4));
            }

            mentionedApple = true;

        } else if(gp.getPlayer().isHasBanana() && !mentionedBanana) {
            if(gp.getPlayer().isHasApple()) {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(9));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(9));

            } else if(gp.getPlayer().isHasCherry()) {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(7));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(7));

            } else {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(5));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(5));
            }

            mentionedBanana = true;

        } else if(gp.getPlayer().isHasCherry() && !mentionedCherry) {
            if(gp.getPlayer().isHasApple()) {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(8));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(8));
            } else if(gp.getPlayer().isHasBanana()) {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(7));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(7));
            } else {
                gp.getDialogueH().setCurrentDialogue(generateHandlerDialogue(6));
                gp.getDialogueH().setCurrentPortraits(generateHandlerPortraits(6));
            }

            mentionedCherry = true;
        }

        // CHANGE DIRECTION TO FACE PLAYER
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

    // UTILITY, can be moved to Entity when relevant
    public String[] generateHandlerDialogue(int beginningIndex) {
        String[] result = new String[1];
        result[0] = getDialogues()[beginningIndex];

        return result;
    }
    public String[] generateHandlerDialogue(int beginningIndex, int endingIndex) {
        int totalMessages = (endingIndex - beginningIndex) + 1;
        String[] result = new String[totalMessages];

        for(int i = 0; i < totalMessages; i++) {
            result[i] = getDialogues()[i + beginningIndex];
        }

        return result;
    }

    public BufferedImage[] generateHandlerPortraits(int beginningIndex) {
        BufferedImage[] result = new BufferedImage[1];
        result[0] = getPortraits()[beginningIndex];

        return result;
    }
    public BufferedImage[] generateHandlerPortraits(int beginningIndex, int endingIndex) {
        int totalPortraits = (endingIndex - beginningIndex) + 1;
        BufferedImage[] result = new BufferedImage[totalPortraits];

        for(int i = 0; i < totalPortraits; i++) {
            result[i] = getPortraits()[i + beginningIndex];
        }

        return result;
    }
}
