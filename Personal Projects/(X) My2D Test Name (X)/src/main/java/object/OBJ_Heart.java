package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";

        image = setup("/objects/heartfull", gp.TILE_SIZE, gp.TILE_SIZE);
        image2 = setup("/objects/hearthalf", gp.TILE_SIZE, gp.TILE_SIZE);
        image3 = setup("/objects/heartempty", gp.TILE_SIZE, gp.TILE_SIZE);
    }



}
