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
        BufferedImage portraitsL[] = new BufferedImage[4];
        BufferedImage portraitsR[] = new BufferedImage[4];

        // DEFAULT
        dialogues[0] = "Hello! So, you've arrived on \nthis island at last.";
        portraitsL[0] = getPortrait1();
        dialogues[1] = "When you have all three fruits, \ncome back.";
        portraitsL[1] = getPortrait1();
        dialogues[2] = "Have fun exploring, but be \nsafe.";
        portraitsL[2] = getPortrait1();
        dialogues[3] = "My dialogue loop is about to \nreset. It was nice chatting!";
        portraitsL[3] = getPortrait1();

        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        // CONDITIONALS
        portraitsL = new BufferedImage[] {null};
        portraitsR = new BufferedImage[] {null};

        dialogues = new String[] {"You know what they say about \nan apple a day!"};
        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        dialogues = new String[] {"Why does banana candy suck \nso much anyways?"};
        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        dialogues = new String[] {"The developer borrowed that \ncherry sprite from Pac-Man."};
        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        // Item combinations
        portraitsL = new BufferedImage[] {getPortrait1()};

        dialogues = new String[] {"Only the Apple left. Head \nNorth!"};
        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        dialogues = new String[] {"Only the Banana left. Head \nWest!"};
        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        dialogues = new String[] {"Only the Cherry left. Head \nEast!"};
        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        // End of Game
        dialogues = new String[4];
        portraitsL = new BufferedImage[4];
        portraitsR = new BufferedImage[4];
        BufferedImage background = gp.getPanelUI().getBackgroundScreens()[0];

        dialogues[0] = "So you've finally found the \nlegendary fruit.";
        portraitsL[0] = getPortrait1();
        portraitsR[0] = gp.getPlayer().getPortrait1();
        dialogues[1] = "I'd love to tell you how quickly \nyou did it!";
        portraitsL[1] = getPortrait1();
        portraitsR[1] = gp.getPlayer().getPortrait1();
        dialogues[2] = "Either way, congratulations!";
        portraitsL[2] = getPortrait1();
        portraitsR[2] = gp.getPlayer().getPortrait1();
        dialogues[3] = "You won!";
        portraitsL[3] = getPortrait1();
        portraitsR[3] = gp.getPlayer().getPortrait1();

        getObjDialogue().add(new Dialogue(background, dialogues, portraitsL, portraitsR));
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

        // End of Game
        if(gp.getPlayer().isHasApple() && gp.getPlayer().isHasBanana() && gp.getPlayer().isHasCherry()) {
            gp.getDialogueH().setCurrentD(getObjDialogue().get(7));
        }
    }

    public void setAction() {
        turnRandomly(180);
    }
}
