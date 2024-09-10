package org.example.entity;

import org.example.Dialogue;
import org.example.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Girl extends Entity {
    public NPC_Girl(GamePanel gp) {
        super(gp);

        setDirection("down");
        setSpeed(0);

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

        dialogues[0] = "That boy took my candy!";
        portraitsL[0] = getPortrait1();
        portraitsR[0] = null;
        dialogues[1] = "I was really hungry too. \nWhat the heck!";
        portraitsL[1] = getPortrait1();
        portraitsR[1] = null;

        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));
    }

    public void speak() {
        turnToPlayer();

        gp.getDialogueH().setCurrentD(getObjDialogue().get(0));
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