package org.example.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.example.GamePanel;

public class OBJ_Banana extends SuperObject{
    public OBJ_Banana(GamePanel gp) {
        setName("Banana");

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/banana_0.png"));
            setImage(getUTool().scaleImage(image, gp.getTileSize(), gp.getTileSize()));
        } catch (IOException e) {
            System.out.println("Something happened! Error Code: 006");
        }
    }
}