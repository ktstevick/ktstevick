package main;

import object.OBJ_Banana;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_60B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#00.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_60B = new Font("Arial", Font.BOLD, 60);
        OBJ_Banana key = new OBJ_Banana(); // Hopefully this schism won't be relevant for much longer
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) { // Multi step process to avoid instantiation within game loop

        if(gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the Orb! Ponder well!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.SCREEN_WIDTH / 2) - (textLength / 2);
            y = (gp.SCREEN_HEIGHT / 2) - (gp.TILE_SIZE*3);
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.SCREEN_WIDTH / 2) - (textLength / 2);
            y = (gp.SCREEN_HEIGHT / 2) - (gp.TILE_SIZE * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_60B);
            g2.setColor(Color.yellow);
            text = "*** Congratulations ***";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.SCREEN_WIDTH / 2) - (textLength / 2);
            y = (gp.SCREEN_HEIGHT / 2) + (gp.TILE_SIZE*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

        else { // gameFinished == false;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, (gp.TILE_SIZE / 2), (gp.TILE_SIZE / 2) ,gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawString("x " + gp.player.hasKey, 80, 60); // This Y coordinate refers to the baseline of the text, not the top

            // TIME
            playTime += (double)1/60; // Derived from FPS, adds each frame to the running time count
            g2.drawString("Time: " + dFormat.format(playTime), gp.TILE_SIZE * 11, 60);

            // MESSAGE
            if(messageOn) {

                int textLength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
                int x = (gp.SCREEN_WIDTH / 2) - (textLength / 3); // Not sure why X/2 didn't center this, but X/3 seems perfect
                int y = (gp.SCREEN_HEIGHT / 2) - 40;

                g2.setFont(g2.getFont().deriveFont(25F));
                g2.drawString(message, x, y);

                messageCounter++;

                if(messageCounter > 120) { // This 120 references FPS
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
