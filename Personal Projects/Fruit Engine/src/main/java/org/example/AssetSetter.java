package org.example;

import org.example.entity.NPC_Boy;
import org.example.entity.NPC_Girl;
import org.example.entity.NPC_Granny;
import org.example.object.*;

public class AssetSetter {
    private final GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.getObjArray()[0] = new OBJ_Apple(gp);
        gp.getObjArray()[0].setWorldX(gp.getTileSize() * 27);
        gp.getObjArray()[0].setWorldY(gp.getTileSize() * 9);

        gp.getObjArray()[1] = new OBJ_Banana(gp);
        gp.getObjArray()[1].setWorldX(gp.getTileSize() * 11);
        gp.getObjArray()[1].setWorldY(gp.getTileSize() * 35);

        gp.getObjArray()[2] = new OBJ_Cherry(gp);
        gp.getObjArray()[2].setWorldX(gp.getTileSize() * 37);
        gp.getObjArray()[2].setWorldY(gp.getTileSize() * 29);
    }

    public void setNPC() {
        gp.getNpc()[0] = new NPC_Granny(gp);
        gp.getNpc()[0].setWorldX(gp.getTileSize() * 22);
        gp.getNpc()[0].setWorldY(gp.getTileSize() * 22);

        gp.getNpc()[1] = new NPC_Girl(gp);
        gp.getNpc()[1].setWorldX(gp.getTileSize() * 27);
        gp.getNpc()[1].setWorldY(gp.getTileSize() * 27);

        gp.getNpc()[2] = new NPC_Boy(gp);
        gp.getNpc()[2].setWorldX(gp.getTileSize() * 30);
        gp.getNpc()[2].setWorldY(gp.getTileSize() * 30);

        gp.getNpc()[3] = new NPC_Boy(gp);
        gp.getNpc()[3].setWorldX(gp.getTileSize() * 30);
        gp.getNpc()[3].setWorldY(gp.getTileSize() * 35);
    }
}
