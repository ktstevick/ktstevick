package org.example.entity;

import org.example.Dialogue;
import org.example.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Boy extends Entity {
    public NPC_Boy(GamePanel gp) {
        super(gp);

        setDirection("down");
        setSpeed(3);

        getImage();
        setDialogue();
    }

    public void getImage() {
        setUp1(setup("/npc/boy_up_1"));
        setUp2(setup("/npc/boy_up_2"));
        setDown1(setup("/npc/boy_down_1"));
        setDown2(setup("/npc/boy_down_2"));
        setLeft1(setup("/npc/boy_left_1"));
        setLeft2(setup("/npc/boy_left_2"));
        setRight1(setup("/npc/boy_right_1"));
        setRight2(setup("/npc/boy_right_2"));
    }

    public void setDialogue() {
        String[] dialogues = new String[] {"I ate a lot of candy!!"};
        BufferedImage[] portraits = new BufferedImage[] {null};

        getObjDialogue().add(new Dialogue(null, dialogues, portraits, null));
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
