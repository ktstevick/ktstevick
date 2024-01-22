package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cello extends Entity {
    GamePanel gp;

    int value = 5;
    public OBJ_Cello(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Cello";
        down1 = setup("/objects/cello", gp.TILE_SIZE, gp.TILE_SIZE);
        description = "[" + name + "]\nWhere'd I find this?";
    }
}
