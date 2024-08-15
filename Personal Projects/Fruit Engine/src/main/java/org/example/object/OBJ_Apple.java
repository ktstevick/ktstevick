package org.example.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.example.GamePanel;

public class OBJ_Apple extends SuperObject{
    public OBJ_Apple(GamePanel gp) {
        setName("Apple");

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/apple_0.png"));
            setImage(getUTool().scaleImage(image, gp.getTileSize(), gp.getTileSize()));
        } catch (IOException e) {
            System.out.println("Something happened! Error Code: 005");
        }
    }
}