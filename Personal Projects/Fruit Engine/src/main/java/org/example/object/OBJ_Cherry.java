package org.example.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.example.GamePanel;

public class OBJ_Cherry extends SuperObject{
    public OBJ_Cherry(GamePanel gp) {
        setName("Cherry");

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/objects/cherry_0.png"));
            setImage(getUTool().scaleImage(image, gp.getTileSize(), gp.getTileSize()));
        } catch (IOException e) {
            System.out.println("Something happened! Error Code: 007");
        }
    }
}