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
    private final GamePanel gp;
    private final UtilityTool uTool = new UtilityTool();
    private final Tile[] tile;
    private final int mapTileNum[][];
    private int spriteCounter = 0;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        System.out.println(gp.getCollisionChecker());

        tile = new Tile[50]; // Total Types of Tile
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        getTileImage();
        loadMap("/maps/world_island_0.txt");
    }

    public void getTileImage() {
            // Gaps for current map readability
            setup(10, "grass_0", false);
            setup(11, "flower_0", "flower_1", false); // Animated
            setup(12, "bush_0", true);

            setup(13, "bush_0", false); // Overhead proof of concept
                tile[13].setOverhead(true);

            setup(14, "water_0", true);
            setup(15, "path_0", false);

            // Transition Tiles
            setup(16, "water_edge_east_0", true);
            setup(17, "water_edge_south_0", true);
            setup(18, "water_edge_west_0", true);
            setup(19, "water_edge_north_0", true);

            setup(20, "water_corner_outer_nw_0", true);
            setup(21, "water_corner_inner_nw_0", true);

            setup(22, "water_corner_outer_ne_0", true);
            setup(23, "water_corner_inner_ne_0", true);

            setup(24, "water_corner_outer_sw_0", true);
            setup(25, "water_corner_inner_sw_0", true);

            setup(26, "water_corner_outer_se_0", true);
            setup(27, "water_corner_inner_se_0", true);
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
        spriteCounter++;

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            // Only draws tiles that appear on screen
            if(     worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);

                // Animation
                if(spriteCounter > 60) {
                    g2.drawImage(tile[tileNum].getImage2(), screenX, screenY, null);
                }

                if (spriteCounter == 120) {
                    spriteCounter = 0;
                }

                // DEBUG
                if(gp.getKeyH().isDebugOn()) {
                    int stringY = screenY + gp.getTileSize();

                    if(tile[tileNum].isCollision()) {
                        g2.fillRect(screenX, screenY, gp.getTileSize(), gp.getTileSize());
                    } else {
                        g2.drawRect(screenX, screenY, gp.getTileSize(), gp.getTileSize());
                    }

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

    public void drawOverhead(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            // Only draws tiles that appear on screen
            if(     worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

                if(tile[tileNum].isOverhead()) {
                    g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
                }
            }

            worldCol++;

            if(worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    // Basic Tile
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

    // Animated Tile
    public void setup(int index, String imageName, String image2name, boolean collision) {
        try {
            tile[index] = new Tile();

            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].setImage(uTool.scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize()));

            tile[index].setImage2(ImageIO.read(getClass().getResourceAsStream("/tiles/" + image2name + ".png")));
            tile[index].setImage2(uTool.scaleImage(tile[index].getImage2(), gp.getTileSize(), gp.getTileSize()));

            tile[index].setCollision(collision);

        } catch(IOException e) {
            System.out.println("Something happened! Error Code: 003");
        }
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }
    public Tile[] getTileArray() {
        return tile;
    }
}
