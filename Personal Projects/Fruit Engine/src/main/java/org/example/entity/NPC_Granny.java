package org.example.entity;

import org.example.Dialogue;
import org.example.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Granny extends Entity {
    private boolean mentionedApple = false; // Corresponding "mentionedInventory" List?
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
        String dialogues[] = new String[4]; // Default loop size
        BufferedImage portraits[] = new BufferedImage[4];
        BufferedImage background = gp.getPanelUI().getCreditScreen();

        // DEFAULT
        dialogues[0] = "Hello! So, you've arrived on \nthis island at last.";
        portraits[0] = getPortrait1();
        dialogues[1] = "When you have all three fruits, \ncome back.";
        portraits[1] = getPortrait1();
        dialogues[2] = "Have fun exploring, but be \nsafe.";
        portraits[2] = getPortrait1();
        dialogues[3] = "My dialogue loop is about to \nreset. It was nice chatting!";
        portraits[3] = getPortrait1();

        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));

        // CONDITIONALS
        portraits = new BufferedImage[] {null};

        dialogues = new String[] {"You know what they say about \nan apple a day!"};
        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));

        dialogues = new String[] {"Why does banana candy suck \nso much anyways?"};
        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));

        dialogues = new String[] {"The developer borrowed that \ncherry sprite from Pac-Man."};
        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));

        // Item combinations
        portraits = new BufferedImage[] {getPortrait2()};
        background = gp.getPanelUI().getCreditScreen();

        dialogues = new String[] {"Only the Apple left. Head \nNorth!"};
        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));

        dialogues = new String[] {"Only the Banana left. Head \nWest!"};
        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));

        dialogues = new String[] {"Only the Cherry left. Head \nEast!"};
        getObjDialogue().add(new Dialogue(background, dialogues, portraits, null));
    }

    public void speak() {
        turnToPlayer();

        // DEFAULT
        gp.getDialogueH().setCurrentD(getObjDialogue().get(0));

        // CONDITIONAL DIALOGUE
        if(gp.getPlayer().isHasApple() && !mentionedApple) {
            if(gp.getPlayer().isHasBanana()) {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(6));
            } else if(gp.getPlayer().isHasCherry()) {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(5));

            } else {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(1));
            }

            mentionedApple = true;

        } else if(gp.getPlayer().isHasBanana() && !mentionedBanana) {
            if(gp.getPlayer().isHasApple()) {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(6));
            } else if(gp.getPlayer().isHasCherry()) {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(4));
            } else {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(2));
            }

            mentionedBanana = true;

        } else if(gp.getPlayer().isHasCherry() && !mentionedCherry) {
            if(gp.getPlayer().isHasApple()) {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(5));
            } else if(gp.getPlayer().isHasBanana()) {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(4));
            } else {
                gp.getDialogueH().setCurrentD(getObjDialogue().get(3));
            }

            mentionedCherry = true;
        }
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
}
