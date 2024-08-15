package org.example;

import org.example.entity.Entity;

public class CollisionChecker {
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX()+ entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1;
        int tileNum2;

        switch(entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];

                if(gp.getTileM().getTileArray()[tileNum1].isCollision() || gp.getTileM().getTileArray()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];

                if(gp.getTileM().getTileArray()[tileNum1].isCollision() || gp.getTileM().getTileArray()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];

                if(gp.getTileM().getTileArray()[tileNum1].isCollision() || gp.getTileM().getTileArray()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];

                if(gp.getTileM().getTileArray()[tileNum1].isCollision() || gp.getTileM().getTileArray()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999; // Arbitrary

        for(int i = 0; i < gp.getObjArray().length; i++) {
            if(gp.getObjArray()[i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                gp.getObjArray()[i].getSolidArea().x = gp.getObjArray()[i].getWorldX() + gp.getObjArray()[i].getSolidArea().x;
                gp.getObjArray()[i].getSolidArea().y = gp.getObjArray()[i].getWorldY() + gp.getObjArray()[i].getSolidArea().y;

                switch(entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjArray()[i].getSolidArea())) {
                            if(gp.getObjArray()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjArray()[i].getSolidArea())) {
                            if(gp.getObjArray()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjArray()[i].getSolidArea())) {
                            if(gp.getObjArray()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjArray()[i].getSolidArea())) {
                            if(gp.getObjArray()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.getObjArray()[i].getSolidArea().x = gp.getObjArray()[i].getSolidAreaDefaultX();
                gp.getObjArray()[i].getSolidArea().y = gp.getObjArray()[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999; // Arbitrary

        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                switch(entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())) {
                                entity.setCollisionOn(true);
                                index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

    public void checkPlayer(Entity entity) {
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;

        switch(entity.getDirection()) {
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
        }

        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
    }
}
