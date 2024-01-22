package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Orb extends SuperObject {

    // Constructor
    public OBJ_Orb() {
        name = "Orb";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/orb.png"));

        } catch(IOException e) {
            // Not printing Stack Trace lmfao
        }

        // solidArea.x = X is how we would set custom item sizes
    }

}



