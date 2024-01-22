package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Banana extends SuperObject {
    public OBJ_Banana() {
        name = "Banana";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/banana.png"));

        } catch(IOException e) {
            // :3
        }
    }
}
