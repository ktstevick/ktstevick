package org.example.entity;

import org.example.Dialogue;
import org.example.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Girl extends Entity {
    private boolean mentionedBanana = false;

    public NPC_Girl(GamePanel gp) {
        super(gp);

        setDirection("down");
        setSpeed(2);

        getImage();
        setDialogue();
    }

    public void getImage() {
        setPortrait1(setup("/npc/girl_portrait_1"));

        setUp1(setup("/npc/girl_up_1"));
        setUp2(setup("/npc/girl_up_2"));
        setDown1(setup("/npc/girl_down_1"));
        setDown2(setup("/npc/girl_down_2"));
        setLeft1(setup("/npc/girl_left_1"));
        setLeft2(setup("/npc/girl_left_2"));
        setRight1(setup("/npc/girl_right_1"));
        setRight2(setup("/npc/girl_right_2"));
    }

    public void setDialogue() {
        String[] dialogues = new String[2];
        BufferedImage[] portraitsL = new BufferedImage[2];
        BufferedImage[] portraitsR = new BufferedImage[2];

        // DEFAULT
        dialogues[0] = "That boy took my candy!";
        portraitsL[0] = getPortrait1();
        portraitsR[0] = gp.getPlayer().getPortrait1();
        dialogues[1] = "I was really hungry too. \nWhat the heck!";
        portraitsL[1] = getPortrait1();
        portraitsR[1] = gp.getPlayer().getPortrait1();

        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));

        // CONDITIONALS
        dialogues = new String[3];
        portraitsL = new BufferedImage[3];
        portraitsR = new BufferedImage[3];
        BufferedImage background = gp.getPanelUI().getBackgroundScreens()[1];

        dialogues[0] = "When I was a girl, a giant \nwave sank my island.";
        portraitsL[0] = getPortrait1();
        portraitsR[0] = gp.getPlayer().getPortrait1();
        dialogues[1] = "I only survived by floating \naway on a giant banana.";
        portraitsL[1] = getPortrait1();
        portraitsR[1] = gp.getPlayer().getPortrait1();
        dialogues[2] = "I owe that banana my life...";
        portraitsL[2] = getPortrait1();
        portraitsR[2] = gp.getPlayer().getPortrait1();

        getObjDialogue().add(new Dialogue(background, dialogues, portraitsL, portraitsR));
    }

    public void speak() {
        turnToPlayer();

        // DEFAULT
        gp.getDialogueH().setCurrentD(getObjDialogue().get(0));

        // CONDITIONALS
        if(gp.getPlayer().isHasBanana() && !mentionedBanana) {
            gp.getDialogueH().setCurrentD(getObjDialogue().get(1));
            mentionedBanana = true;
        }
    }

    public void setAction() {
        turnRandomly(60);
    }
}