package main;

import object.OBJ_Banana;
import object.OBJ_Cello;
import object.OBJ_Door;
import object.OBJ_Orb;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Orb();
        gp.obj[0].worldX = 34 * gp.TILE_SIZE;
        gp.obj[0].worldY = 16 * gp.TILE_SIZE;

        gp.obj[1] = new OBJ_Banana();
        gp.obj[1].worldX = 22 * gp.TILE_SIZE;
        gp.obj[1].worldY = 15 * gp.TILE_SIZE;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 48 * gp.TILE_SIZE;
        gp.obj[2].worldY = 13 * gp.TILE_SIZE;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 49 * gp.TILE_SIZE;
        gp.obj[3].worldY = 13 * gp.TILE_SIZE;

        gp.obj[4] = new OBJ_Orb();
        gp.obj[4].worldX = 48 * gp.TILE_SIZE;
        gp.obj[4].worldY = 10 * gp.TILE_SIZE;

        gp.obj[5] = new OBJ_Banana();
        gp.obj[5].worldX = 40 * gp.TILE_SIZE;
        gp.obj[5].worldY = 41 * gp.TILE_SIZE;

        gp.obj[6] = new OBJ_Cello();
        gp.obj[6].worldX = 26 * gp.TILE_SIZE;
        gp.obj[6].worldY = 30 * gp.TILE_SIZE;
    }
}
