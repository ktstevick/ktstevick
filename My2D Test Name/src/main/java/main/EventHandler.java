package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][]; // 2 Dimensional Array

    int previousEventX, previousEventY;
    boolean canTouchEvent = true; // Distance margin to prevent events from happening too many times

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];

        int col = 0;
        int row = 0;
        while(col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {
            // If we made this Rectangle the size of a tile, the Event would trigger as soon as the tile is TOUCHED
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 12;
            eventRect[col][row].y = 12;
            eventRect[col][row].width = 24;
            eventRect[col][row].height = 24;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.MAX_WORLD_COL) {
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent() {
        // Check if player character is more than one tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance); // Picks the largest of the two
        if(distance > gp.TILE_SIZE) {
            canTouchEvent = true;
        }

        // EVENTS
        if(canTouchEvent) {
            if (hit(22, 18, "down")) { damagePit(22, 18, gp.DIALOGUE_STATE); }

            if (hit(32, 11, "right")) { teleport(32, 11, gp.DIALOGUE_STATE, 38, 11); }
            if (hit(38, 11, "left")) { teleport(38, 11, gp.DIALOGUE_STATE, 32, 11); }
            if (hit(49, 6, "up")) { teleport(49, 6, gp.DIALOGUE_STATE, 43, 47); }

            if (hit(22, 9, "up")) { healingPool(22, 9, gp.DIALOGUE_STATE); }
        }
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = (col * gp.TILE_SIZE) + eventRect[col][row].x;
        eventRect[col][row].y = (row * gp.TILE_SIZE) + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.playSoundEffect(6);
        gp.ui.currentDialogue = "Surprise! A pit!!";
        gp.player.life -= 1;

        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool (int col, int row, int gameState) {
        if(gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.player.attackCancelled = true;
            gp.playSoundEffect(3);
            gp.ui.currentDialogue = "You drink the water. \nNot exactly your best idea.";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
        }
    }

    public void teleport(int col, int row, int gameState, int x, int y) {
        gp.gameState = gameState;
        gp.playSoundEffect(3);
        gp.ui.currentDialogue = "TELEPORT TEXT";

        gp.player.worldX = gp.TILE_SIZE * x;
        gp.player.worldY = gp.TILE_SIZE * y - 6;
    }

}
