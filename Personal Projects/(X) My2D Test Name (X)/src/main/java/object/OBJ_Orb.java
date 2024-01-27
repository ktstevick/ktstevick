package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Orb extends Entity {
    GamePanel gp;

    // Constructor
    public OBJ_Orb(GamePanel gp) {
        super(gp);

        name = "Orb";
        down1 = setup("/objects/orb", gp.TILE_SIZE, gp.TILE_SIZE);

        // solidArea.x = X is how we would set custom item sizes
        description = "[" + name + "]\nWell worth pondering...";
    }

}



