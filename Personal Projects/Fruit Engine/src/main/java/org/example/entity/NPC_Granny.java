package org.example.entity;

import org.example.GamePanel;

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

        dialogues[0] = "Hello! So, you've arrived on \nthis island at last.";
        dialogues[1] = "When you have all three fruits, \ncome back.";
        dialogues[2] = "Have fun exploring, but be \nsafe.";
        dialogues[3] = "My dialogue loop is about to \nreset. It was nice chatting!";

        // Found items
        dialogues[4] = "You know what they say about \nan apple a day!";
        dialogues[5] = "Why does banana candy suck \nso much anyways?";
        dialogues[6] = "The developer borrowed that \ncherry sprite from Pac-Man.";

        // Item combinations
        dialogues[7] = "Only the Apple left. Head \nNorth!";
        dialogues[8] = "Only the Banana left. Head \nWest!";
        dialogues[9] = "Only the Cherry left. Head \nEast!";

        setDialogues(dialogues);
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
        gp.getPanelUI().setCurrentDialogue(generateUIDialogue(0, 3));

        // CONDITIONAL DIALOGUE
        if(gp.getPlayer().isHasApple() && !mentionedApple) {
            if(gp.getPlayer().isHasBanana()) {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(9));

            } else if(gp.getPlayer().isHasCherry()) {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(8));

            } else {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(4));
            }

            mentionedApple = true;

        } else if(gp.getPlayer().isHasBanana() && !mentionedBanana) {
            if(gp.getPlayer().isHasApple()) {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(9));

            } else if(gp.getPlayer().isHasCherry()) {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(7));

            } else {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(5));
            }

            mentionedBanana = true;

        } else if(gp.getPlayer().isHasCherry() && !mentionedCherry) {
            if(gp.getPlayer().isHasApple()) {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(8));
            } else if(gp.getPlayer().isHasBanana()) {
                gp.getPanelUI().setCurrentDialogue(generateUIDialogue(7));
            } else { gp.getPanelUI().setCurrentDialogue(generateUIDialogue(6));
            }

            mentionedCherry = true;
        }

        // GAME END CHECK
//        if(gp.getPlayer().isHasApple() && gp.getPlayer().isHasBanana() && gp.getPlayer().isHasCherry()) {
//            gp.setGameState(gp.getCreditsState());
//        }

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
    public String[] generateUIDialogue(int beginningIndex) {
        String[] result = new String[1];
        result[0] = getDialogues()[beginningIndex];

        return result;
    }

    public String[] generateUIDialogue(int beginningIndex, int endingIndex) {
        int totalMessages = (endingIndex - beginningIndex) + 1;
        String[] result = new String[totalMessages];

        for(int i = 0; i < totalMessages; i++) {
            result[i] = getDialogues()[i + beginningIndex];
        }

        return result;
    }
}
