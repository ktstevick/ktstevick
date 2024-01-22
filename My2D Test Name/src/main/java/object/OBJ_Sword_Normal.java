package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = TYPE_SWORD;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.TILE_SIZE, gp.TILE_SIZE);
        attackValue = 1;

        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";

    }
}
