package org.example.tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.example.GamePanel;
import org.example.UtilityTool;

public class TileManager {
    private GamePanel gp;
    private UtilityTool uTool = new UtilityTool();
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        System.out.println(gp.getCollisionChecker());

        tile = new Tile[20]; // Total Types of Tile
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        getTileImage();
        loadMap("/maps/world_island_0.txt");
    }

    public void setup(int index, String imageName, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].setImage(uTool.scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize()));
            tile[index].setCollision(collision);

        } catch(IOException e) {
            System.out.println("Something happened! Error Code: 003");
        }
    }

    public void getTileImage() {
            setup(10, "grass_0", false);
//            setup(11, "wall_0", true);
            setup(12, "water_0", true);
//            setup(13, "dirt_0", false);
            setup(14, "bush_0", true);
            setup(15, "path_0", false);
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();

                while(col < gp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch(Exception e) {
            System.out.println("Something happened! Error Code: 004");
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX= worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            // Only draws tiles that appear on screen
            if(     worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);

                // DEBUG
                if(gp.getKeyH().isDebugOn()) {
                    g2.drawRect(screenX, screenY, gp.getTileSize(), gp.getTileSize());
                    int stringY = screenY + gp.getTileSize();

                    g2.setFont(gp.getPanelUI().getArial_DEBUG());
                    g2.drawString((worldX / gp.getTileSize()) + "x" + (worldY / gp.getTileSize()), screenX, stringY);
                }
            }

            worldCol++;

            if(worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }
    public Tile[] getTileArray() {
        return tile;
    }
}
