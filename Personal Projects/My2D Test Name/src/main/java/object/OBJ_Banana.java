package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Banana extends Entity {
    GamePanel gp;
    int healValue = 5;

    public OBJ_Banana(GamePanel gp) {
        super(gp);

        type = TYPE_CONSUMABLE;
        name = "Banana";
        down1 = setup("/objects/banana", gp.TILE_SIZE, gp.TILE_SIZE);
        description = "[" + name + "]\nThis looks like it can \nopen doors.";
        useMessage = "I was wrong, looks like these\nrestore your health by 5!";

        battleItem = true;
    }
}
