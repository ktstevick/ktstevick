package org.example.entity;

import org.example.Dialogue;
import org.example.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Boy extends Entity {
    public NPC_Boy(GamePanel gp) {
        super(gp);

        setDirection("down");
        setSpeed(5);

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
        String[] dialogues = new String[2];
        BufferedImage[] portraitsL = new BufferedImage[2];
        BufferedImage[] portraitsR = new BufferedImage[2];

        // DEFAULT
        dialogues[0] = "I ate a lot of candy!";
        dialogues[1] = "LikeaLOTlotlot!!";

        getObjDialogue().add(new Dialogue(null, dialogues, portraitsL, portraitsR));
    }

    public void speak() {
        turnToPlayer();

        gp.getDialogueH().setCurrentD(getObjDialogue().get(0));
    }

    public void setAction() {
        turnRandomly(5);
    }
}
