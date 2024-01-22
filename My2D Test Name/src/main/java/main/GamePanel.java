package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile

    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    // WORLD SETTINGS
    public final int MAX_WORLD_COL = 66;
    public final int MAX_WORLD_ROW = 60;

    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public BattleManager battleManager = new BattleManager(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public int interactingMonsterId;
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int TITLE_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int DIALOGUE_STATE = 3;
    public final int CHARACTER_STATE = 4;
    public final int BATTLE_STATE = 5;


    // CONSTRUCTOR
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        // playMusic(0);
        // stopMusic();
        gameState = TITLE_STATE;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

//    @Override // SLEEP METHOD
//    public void run() {
//        double drawInterval = 1_000_000_000/FPS; // 0.01667 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        // GAME LOOP
//        while(gameThread != null) { // System.out.println("The game loop is running. You'd better go catch it!");
//            long currentTime = System.nanoTime();
//
//            // 1 UPDATE: update info, such as character positions
//            update();
//
//            // 2 DRAW: draw the screen with the updated information
//            repaint(); // THIS is how we call paintComponent(), essentially legacy code
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1_000_000; // Converts from nanoseconds to milliseconds before typecasting to long
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                // Eating exceptions in development
//            }
//        }
//    }

    @Override // DELTA METHOD
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            // We check the current time, subtracting lastTime from current, giving us the time passed, then dividing by the interval.
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

//            if (timer >= 1_000_000_000) {
//                // For testing, prints FPS and resets relevant counters
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }

    public void update() {
        if(gameState == PLAY_STATE) {
            if (!player.fromBattleState) {
                // PLAYER
                player.update();

                // NPC
                for (int i = 0; i < npc.length; i++) {
                    if (npc[i] != null) {
                        npc[i].update();
                    }
                }

                // MONSTER
                for (int i = 0; i < monster.length; i++) {
                    if (monster[i] != null) {
                        if (monster[i].alive && !monster[i].dying) {
                            monster[i].update();
                        }
                        if (!monster[i].alive) {
                            monster[i] = null;
                        }
                    }
                }

                // PROJECTILE
                for (int i = 0; i < projectileList.size(); i++) {
                    if (projectileList.get(i) != null) {
                        if (projectileList.get(i).alive) {
                            projectileList.get(i).update();
                        }
                        if (!projectileList.get(i).alive) {
                            projectileList.remove(i);
                        }
                    }
                }


            }
            if (gameState == PAUSE_STATE) {
                // Nothing for now
            }
            if (gameState == BATTLE_STATE) {
                // :P
            }
        }
    }

    // Built in Java method
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == TITLE_STATE) {
            ui.draw(g2);
        }

        // OTHERS
        else {
            // TILE
            tileM.draw(g2);

            // Add all entities to list
            entityList.add(player);
            for(int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for(int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for(int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            for(int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            // Sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            entityList.clear();

            // UI
            ui.draw(g2);
        }

        // DEBUG
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 48, 488);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    // SOUND EFFECTS
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }
    public void playSoundEffect(int i) {
        se.setFile(i);
        se.play();
    }
}
