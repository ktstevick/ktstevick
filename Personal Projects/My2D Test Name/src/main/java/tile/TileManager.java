package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    // Constructor
    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[30];
        mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];

        getTileImage();
        loadMap("/maps/worldmap.txt"); // Set to Map 01 for the time being
    }

    public void getTileImage() {
            setup(0, "grasstile", false);
            setup(1, "walltile", true);
            setup(2, "watertile", true);
            setup(3, "dirttile", false);
            setup(4, "bushtile", true);
            setup(5, "pathtile", false);

            // Partial tiles, corners, decorative entryways
            setup(6, "clifftopleft", true);
            setup(7, "clifftopright", true);
            setup(8, "cliffbottomleft", true);
            setup(9, "cliffbottomright", true);
            setup(10, "cave", false);
            setup(11, "coastopleft", true);
            setup(12, "coasttop", true);
            setup(13, "coasttopright", true);
            setup(14, "coastbottomleft", true);
            setup(15, "coastbottom", true);
            setup(16, "coastbottomright", true);
            setup(17, "coastleft", true);
            setup(18, "coastright", true);
            setup(19, "coastbottomleftoutie", true);
            setup(20, "coastbottomrightoutie", true);
            setup(21, "clifftop", true);
            setup(22, "cliffleft", true);
            setup(23, "cliffright", true);
            setup(24, "coasttoplefttoutie", true);
            setup(25, "coasttoprightoutie", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.TILE_SIZE, gp.TILE_SIZE);
            tile[index].collision = collision;

        } catch (IOException e) {
            // :3
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_COL) {
                String line = br.readLine();

                while (col < gp.MAX_WORLD_COL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {
            // Uncaught for now
        }
    }

    public void draw(Graphics2D g2) {
        // Automated method. Manual resembles this syntax - g2.drawImage(tile[1].image, 0, 0, gp.TILE_SIZE, gp.TILE_SIZE, null);
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) { // Deck of Cards loop logic
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X; // Offsets difference between the edge of the screen
            int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

            // This conditional creates a boundary the size of the screen, allowing us to render ONLY tiles that appear on-screen
            if      (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;

            }
        }


    }
}
