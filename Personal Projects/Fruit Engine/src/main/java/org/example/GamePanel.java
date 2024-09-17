package org.example;

import javax.swing.*;
import java.awt.*;

import org.example.entity.Entity;
import org.example.entity.Player;
import org.example.object.SuperObject;
import org.example.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    private final int SCALE = 3;
    private final int tileSize = ORIGINAL_TILE_SIZE * SCALE;
    private final int MAX_SCREEN_COL = 15;
    private final int MAX_SCREEN_ROW = 12;
    private final int screenWidth = tileSize * MAX_SCREEN_COL;
    private final int screenHeight = tileSize * MAX_SCREEN_ROW;
    private final int FPS = 60;

    // WORLD SETTINGS
    private final int MAX_WORLD_COL = 50;
    private final int MAX_WORLD_ROW = 50;
    private final int worldWidth = tileSize * MAX_WORLD_COL;
    private final int worldHeight = tileSize * MAX_WORLD_ROW;

    // SYSTEM
    private final TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler(this);
    private final DialogueHandler dialogueH = new DialogueHandler(this);
    private Thread gameThread;
    private final CollisionChecker cChecker = new CollisionChecker(this);
    private final AssetSetter aSetter = new AssetSetter(this);
    private final UI ui = new UI(this);

    // ENTITY AND OBJECT
    private final Player player = new Player(this, keyH);
    private Entity npc[] = new Entity[10];
    private SuperObject obj[] = new SuperObject[10];

    // GAME STATE
    private int gameState;
    private final int PLAY_STATE = 1;
    private final int PAUSE_STATE = 2;
    private final int DIALOGUE_STATE = 3;
    private final int CREDITS_STATE = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNPC();

        gameState = PLAY_STATE;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            double drawInterval = 1_000_000_000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            // UPDATE
            update();

            // DRAW
            repaint();

            // SLEEP
            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1_000_000; // Converted to milliseconds

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e){
                System.out.println("Something happened! Error Code: 001");
            }
        }
    }

    public void update() {
        if(gameState == PLAY_STATE) {
            player.update();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.isDebugOn()) {
            drawStart = System.nanoTime();
        }

        // DRAW
        tileM.draw(g2);

        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }

        for (Entity entity : npc) {
            if (entity != null) {
                entity.draw(g2);
            }
        }

        player.draw(g2);

        if(!keyH.isDebugOn()) {
            tileM.drawOverhead(g2);
        }

        ui.draw(g2);

        // DEBUG
        if(keyH.isDebugOn()) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setColor(Color.white);
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);

            String fruitCheck = "hasApple = " + getPlayer().isHasApple();
            g2.drawString(fruitCheck, 10, 100);
            fruitCheck = "hasBanana = " + getPlayer().isHasBanana();
            g2.drawString(fruitCheck, 10, 150);
            fruitCheck = "hasCherry = " + getPlayer().isHasCherry();
            g2.drawString(fruitCheck, 10, 200);
        }

        g2.dispose();
    }

    public int getOriginalTileSize() {
        return ORIGINAL_TILE_SIZE;
    }
    public int getScale() {
        return SCALE;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getMaxScreenCol() {
        return MAX_SCREEN_COL;
    }
    public int getMaxScreenRow() {
        return MAX_SCREEN_ROW;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getWorldWidth() {
        return worldWidth;
    }
    public int getWorldHeight() {
        return worldHeight;
    }
    public int getMaxWorldCol() {
        return MAX_WORLD_COL;
    }
    public int getMaxWorldRow() {
        return MAX_WORLD_ROW;
    }
    public int getFPS() {
        return FPS;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public TileManager getTileM() {
        return tileM;
    }
    public CollisionChecker getCollisionChecker() {
        return cChecker;
    }
    public Player getPlayer() {
        return player;
    }
    public SuperObject[] getObjArray() {
        return obj;
    }
    public UI getPanelUI() {
        return ui;
    }
    public Entity[] getNpc() {
        return npc;
    }
    public KeyHandler getKeyH() {
        return keyH;
    }
    public DialogueHandler getDialogueH() {
        return dialogueH;
    }
    public void setNpc(Entity[] npc) {
        this.npc = npc;
    }
    public void setObj(SuperObject[] obj) {
        this.obj = obj;
    }

    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public int getPlayState() {
        return PLAY_STATE;
    }
    public int getPauseState() {
        return PAUSE_STATE;
    }
    public int getDialogueState() {
        return DIALOGUE_STATE;
    }
    public int getCreditsState() { return CREDITS_STATE; }
}
