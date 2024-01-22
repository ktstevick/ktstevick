package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cello extends SuperObject {
    public OBJ_Cello() {
        name = "Cello";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cello.png"));

        } catch(IOException e) {
            // Not printing Stack Trace lmfao
        }
    }
}
