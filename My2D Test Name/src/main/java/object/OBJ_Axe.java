package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = TYPE_AXE;
        name = "Woodcutter's Axe";
        down1 = setup ("/objects/axe", gp.TILE_SIZE, gp.TILE_SIZE);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nRusty, but can still\ncut some trees.";

    }
}
