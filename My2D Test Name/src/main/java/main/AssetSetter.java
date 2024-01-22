package main;

import entity.NPC_Granny;
import monster.MON_BlueSlime;
import monster.MON_EggSlime;
import monster.MON_RedSlime;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int i = 0;

        gp.obj[i] = new OBJ_Banana(gp);
        gp.obj[i].worldX = gp.TILE_SIZE * 25;
        gp.obj[i].worldY = gp.TILE_SIZE * 25;
        i++;

        gp.obj[i] = new OBJ_Banana(gp);
        gp.obj[i].worldX = gp.TILE_SIZE * 25;
        gp.obj[i].worldY = gp.TILE_SIZE * 30;
        i++;

        gp.obj[i] = new OBJ_Banana(gp);
        gp.obj[i].worldX = gp.TILE_SIZE * 25;
        gp.obj[i].worldY = gp.TILE_SIZE * 35;
        i++;

        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.TILE_SIZE * 24;
        gp.obj[i].worldY = gp.TILE_SIZE * 40;
        i++;

        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = gp.TILE_SIZE * 15;
        gp.obj[i].worldY = gp.TILE_SIZE * 25;
        i++;


    }

    public void setNPC() {
        gp.npc[0] = new NPC_Granny(gp);
        gp.npc[0].worldX = (gp.TILE_SIZE * 20);
        gp.npc[0].worldY = (gp.TILE_SIZE * 12);

        gp.npc[1] = new NPC_Granny(gp);
        gp.npc[1].worldX = (gp.TILE_SIZE * 47);
        gp.npc[1].worldY = (gp.TILE_SIZE * 49);
    }

    public void setMonster() {
        gp.monster[0] = new MON_BlueSlime(gp, 0); // Set to zero because he's so unique
        gp.monster[0].worldX = (gp.TILE_SIZE * 12);
        gp.monster[0].worldY = (gp.TILE_SIZE * 12);

        gp.monster[1] = new MON_EggSlime(gp, 1);
        gp.monster[1].worldX = (gp.TILE_SIZE * 22);
        gp.monster[1].worldY = (gp.TILE_SIZE * 34);

        gp.monster[2] = new MON_EggSlime(gp, 2);
        gp.monster[2].worldX = (gp.TILE_SIZE * 20);
        gp.monster[2].worldY = (gp.TILE_SIZE * 32);

        gp.monster[3] = new MON_RedSlime(gp);
        gp.monster[3].worldX = (gp.TILE_SIZE * 46);
        gp.monster[3].worldY = (gp.TILE_SIZE * 51);

        gp.monster[4] = new MON_RedSlime(gp);
        gp.monster[4].worldX = (gp.TILE_SIZE * 22);
        gp.monster[4].worldY = (gp.TILE_SIZE * 36);

        gp.monster[5] = new MON_RedSlime(gp);
        gp.monster[5].worldX = (gp.TILE_SIZE * 17);
        gp.monster[5].worldY = (gp.TILE_SIZE * 16);

    }
}
