package main;

import entity.Entity;
import jdk.jshell.execution.Util;
import object.OBJ_Banana;
import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Font arial_40, arial_32, arial_60B;
    public Graphics2D g2;
    BufferedImage heart_full, heart_half, heart_empty;
    KeyHandler keyH;
    UtilityTool uTool = new UtilityTool();
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    public ArrayList<Entity> visibleBattleInventory = new ArrayList<>();
    ArrayList<Integer> messageCounterArray = new ArrayList<>();
    public boolean gameFinished = false;
    public boolean fromBattleState = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#00.00");
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: first screen, 1: second screen
    public int slotCol;
    public int slotRow;
    public int battleRow;
    public int currentDamageDealt;
    public boolean battleOver;

    // COUNTERS
    public int genericCounter = 0;
    public int battleCounter = 0;
    public int colorCounter = 0;
    public int redCounter = 0;
    public int blueCounter = 0;
    public int greenCounter = 0;
    public int bgRedValue = 0;
    public int bgGreenValue = 0;
    public int bgBlueValue = 0;
    public int transitionCounter = 1;
    public int playStateShuffle = 0;
    public int idleAnimationCounter = 0;
    public int turnTimeCounter = 0; // 60 FPS!
    public int turnStepCounter = 0; // Three steps. Initial text, flash and damage calc, second text.
    public int textDisplayCounter = 0;
    public int animationFrameLock = 0;
    public String battleCharacterText = "";
    public int battleMonsterID = 0;
    public int turnTimeAssist = 0;
    public int turnTimeAssist2 = 0;
    public UI(GamePanel gp) {
        this.gp = gp;
        keyH = new KeyHandler(gp);

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_32 = new Font("Arial", Font.PLAIN, 32);
        arial_60B = new Font("Arial", Font.BOLD, 60);


        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounterArray.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.TITLE_STATE) {
            drawTitleScreen();
        }
        if(gp.gameState == gp.PLAY_STATE) {
            // Transition out of Battle State
            if(gp.player.fromBattleState) {
                gp.player.battleMenuOn = false;
                if(playStateShuffle == 0) {
                    if (transitionCounter <= 45) {
                        // Decides screen draw based on monster livelihood
                        if(gp.monster[battleMonsterID] != null) {
                            drawBattleBase(battleMonsterID,currentDialogue, 1);

                            g2.setColor(new Color(0, 0, 0, transitionCounter * 5));
                            g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
                        }
                        else {
                            // Draw last battle screen.
                            drawBattleBackground();
                            drawBattleTopFrame(currentDialogue, 0);
                            drawBattleBottomFrame(1, gp.TILE_SIZE * 8);

                            g2.setColor(new Color(0, 0, 0, transitionCounter * 5));
                            g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
                        }

                        transitionCounter++;
                    }
                    if (transitionCounter > 45) {
                        playStateShuffle++;
                        transitionCounter = 1;
                    }
                }
                if(playStateShuffle == 1) {
                    // No need to deliberately call to draw the Play State

                    if (transitionCounter <= 45) {
                        g2.setColor(new Color(0, 0, 0, 255 - (transitionCounter * 5)));
                        g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);

                        transitionCounter++;
                    }
                    else {
                        playStateShuffle++;
                        transitionCounter = 1;
                    }
                }
                if(playStateShuffle == 2) {
                    playStateShuffle = 0;
                    transitionCounter = 1;
                    gp.player.fromBattleState = false;
                    gp.player.invincible = true;
                }
            }

            else { // Standard operating procedure
                drawMessage();
                drawPlayerLife();

                // Resetting counters from other states
                genericCounter = 0;
                battleCounter = 0;
                colorCounter = 0;
                redCounter = 0;
                blueCounter = 0;
                greenCounter = 0;
                battleRow = 0;
                battleOver = false;
                battleMonsterID = 0;
                gp.player.battleItemMenu = false;
                visibleBattleInventory.clear();
            }
        }
        if(gp.gameState == gp.PAUSE_STATE) {
            drawPauseScreen();
        }
        if(gp.gameState == gp.DIALOGUE_STATE) {
            drawDialogueScreen();
        }
        if(gp.gameState == gp.CHARACTER_STATE) {
            drawCharacterScreen();
            drawInventory();
        }
        if(gp.gameState == gp.BATTLE_STATE) {
            gp.player.fromBattleState = true;
            transitionCounter = 1;
            drawBattleScreen();

        }
    }

    public void drawPlayerLife() { // This block is GENIUS, updates life display AS we adjust the player's actual stats
        int x = gp.TILE_SIZE / 2;
        int y = gp.TILE_SIZE / 2;
        int i = 0;

        // DRAW BLANK HEART
        while(i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.TILE_SIZE;
        }

        // RESET (y stays the same)
        x = gp.TILE_SIZE / 2;
        i = 0;

        // DRAW CORRECT LIFE
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;

            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }

            i++;
            x += gp.TILE_SIZE;
        }

        // Draw EXP BAR method?
    }
    public void drawMessage() {
        /* Scrolling message method is cool, I doubt I'll keep it around forever though. Cool idea for now - have the bar
        appear over the head, and go from black to gray to light gray ascending, displaying the message but the message is ONLY
        the last three damages dealt, so it stacks over your head like a counter. */
        int messageX = gp.TILE_SIZE;
        int messageY = gp.TILE_SIZE * 3;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));

        for(int i = 0; i < message.size(); i++) {
            if(message.get(i) != null) {
                g2.setColor(Color.darkGray);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                // messageX = getXforCenteredText(message.get(i));
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounterArray.get(i) + 1; // messageCounter++, basically
                messageCounterArray.set(i, counter);
                messageY += gp.TILE_SIZE;

                if(messageCounterArray.get(i) > 180) {
                    message.remove(i);
                    messageCounterArray.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen() {
        if(titleScreenState == 0) {
            int spriteNum = 1;

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
            String text = "GemQuest Demo";
            int x = getXforCenteredText(text);
            int y = gp.TILE_SIZE * 3;

            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // SPRITE IMAGE, leaving this for now but I have BIG plans for this title screen. I'll have to play with it some more
            x = (gp.SCREEN_WIDTH / 2) - gp.TILE_SIZE;
            y += gp.TILE_SIZE * 2; // Tile and a half
            g2.drawImage(gp.player.down1, x, y, gp.TILE_SIZE * 2, gp.TILE_SIZE * 2, null);



            // MENU
            text = "NEW GAME";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                // We'd use drawImage for an icon rather than this text indicator
                g2.drawString(">", x - 36, y); // Three-quarters of a tile
            }

            text = "LOAD GAME";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                // We'd use drawImage for an icon rather than this text indicator
                g2.drawString(">", x - 36, y); // Three-quarters of a tile
            }

            text = "QUIT";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                // We'd use drawImage for an icon rather than this text indicator
                g2.drawString(">", x - 36, y); // Three-quarters of a tile
            }
        }

        else if(titleScreenState == 1) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = gp.TILE_SIZE * 2;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE * 3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - 36, y);
            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x - 36, y);
            }

            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x - 36, y);
            }

            text = "BACK";
            x = getXforCenteredText(text);
            y += gp.TILE_SIZE * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3) {
                g2.drawString(">", x - 36, y);
            }
        }
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));

        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.SCREEN_HEIGHT / 2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.TILE_SIZE;
        int y = gp.TILE_SIZE / 2;
        int width = gp.SCREEN_WIDTH - (gp.TILE_SIZE * 2);
        int height = gp.TILE_SIZE * 3;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.TILE_SIZE;
        y += gp.TILE_SIZE;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gp.TILE_SIZE;
        final int frameY = gp.TILE_SIZE;
        final int frameWidth = gp.TILE_SIZE * 5;
        final int frameHeight = gp.TILE_SIZE * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 16;
        int textY = frameY + gp.TILE_SIZE;
        final int lineHeight = 36;

        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;

        g2.drawString("Life", textX, textY);
        textY += lineHeight;

        g2.drawString("Strength", textX, textY);
        textY += lineHeight;

        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;

        g2.drawString("Attack", textX, textY);
        textY += lineHeight;

        g2.drawString("Defense", textX, textY);
        textY += lineHeight;

        g2.drawString("Exp", textX, textY);
        textY += lineHeight;

        g2.drawString("Next Lvl", textX, textY);
        textY += lineHeight;

        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 15;

        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;

        g2.drawString("Shield", textX, textY);

        // VALUES
        int tailX = (frameX + frameWidth) - 32;
        textY = frameY + gp.TILE_SIZE;
        String value; // Using a String variable to hold even numeric data makes these writing functions way more convenient and versatile

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - 36, textY - 21, null);
        textY += gp.TILE_SIZE;

        g2.drawImage(gp.player.currentShield.down1, tailX - 36, textY - 21, null);
    }
    public void drawBattleScreen() {
        // DEBUG
//        System.out.println(visibleBattleInventory);
//        System.out.println(gp.player.inventory.get(2));

        updateColorCounter(colorCounter);

        // Transition in FROM Play State
        if (battleCounter == 0) {
            if (genericCounter <= 45) {
                genericCounter++;
                g2.setColor(new Color(0, 0, 0, genericCounter * 5));
                g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
            }
            if (genericCounter > 45) {
                battleCounter++;
                genericCounter = 0;
                currentDialogue = gp.monster[battleMonsterID].name + " approaches!";
            }


        }

        // Idle loop, menu selections
        if (battleCounter == 1) {
            gp.player.usedItem = false;
            if(!gp.player.battleItemMenu) {
                if (colorCounter == 0) {
                    drawBattleBase(battleMonsterID, currentDialogue, 1);

                    // Pretty sure THIS is where we sneak in the reverse transition
                    if (genericCounter <= 45) {
                        genericCounter++;
                        g2.setColor(new Color(0, 0, 0, 255 - (genericCounter * 5)));
                        g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
                    } else {
                        gp.player.battleMenuOn = true;
                        // currentDialogue = gp.monster[battleMonsterID].idleMessage;
                    }

                } // Initial color transition from black
                if (colorCounter > 0) {
                    gp.player.battleMenuOn = true;
                    // gp.player.battleMenuOn = true;
                    drawBattleBase(battleMonsterID, gp.monster[battleMonsterID].idleMessage, 1);
                }

                // Got rid of the conditional, since when we shift out we simply won't be in this block of code
                if (gp.player.battleMenuOn) {
                    drawBattleMenu();
                }
            }

            // THIS is where item menu navigation goes, I'm pretty sure
            if(gp.player.battleItemMenu) {
                // gp.player.battleMenuOn = false;

                // This is handled properly if we exit this chunk of code
                drawBattleBase(battleMonsterID, currentDialogue, 1);
                drawBattleMenu();
            }
        }

        // Combat phase
        if (battleCounter == 2) {
            gp.player.battleMenuOn = false;

            // Speed Check code probably goes up here, then assigns the turns accordingly
            if (gp.player.isTakingTurn) {

                // My thought for items is this - using one should ALWAYS go first. In order to accomplish this, a priority system
                // makes sense and would be cool to have eventually anyways, but for NOW I think we can accomplish this by
                // implementing the Speed Check and simply setting the Player's speed stat to 999 when using an item. Likewise,
                // to utilize a damage dealing item, we can set the players attack to whatever damage that item would do. This
                // little maneuver is probably best fit for the Battle Manager, but we'll play around with it. First thing's first
                // though - let's get the menu itself accessible

                turnTimeCounter++;

                if (!gp.player.usedItem) {
                    currentDialogue = gp.player.playerName + " takes a swing --";

                // SUPER basic animation, basically just a screen flash
                if (turnTimeCounter <= 89) {
                    drawBattleBase(battleMonsterID, currentDialogue, 1);
                }
                // Damage calc
                if (turnTimeCounter == 90) {
                    drawBattleBase(battleMonsterID, currentDialogue, 1);

                    currentDamageDealt = gp.battleManager.getDamageCalc(gp.player, gp.monster[battleMonsterID]);
                    gp.playSoundEffect(5);
                    gp.monster[battleMonsterID].life -= currentDamageDealt;
                }
                if (turnTimeCounter > 90 && turnTimeCounter <= 180) {
                    currentDialogue = "BOOM! " + currentDamageDealt + " damage!";
                    drawBattleBase(battleMonsterID, currentDialogue, 1);

                    // Visual effects, SUPER bare bones right now
                    if (turnTimeCounter <= 93) {
                        idleAnimationCounter = 0; // This holds a still image for the first part of this loop, but realistically we'll have a unique "Hurting" sprite
                        enemyFlash();
                        screenFlash(); // Why not both?
                    }
                    if (turnTimeCounter > 97 && turnTimeCounter <= 100) {
                        enemyFlash();
                    }
                    if (turnTimeCounter > 103 && turnTimeCounter <= 106) {
                        enemyFlash();
                    }
                    if (turnTimeCounter == 113 || turnTimeCounter == 120) {
                        idleAnimationCounter = 0;
                    }
                }
            }

                if(gp.player.usedItem) {
                    // currentDialogue = "Used the item - recovered 5HP!";
                    if(turnTimeCounter <= 89) {
                        drawBattleBase(battleMonsterID, currentDialogue, 1);
                    }
                    if (turnTimeCounter > 89 && turnTimeCounter <= 180) {
                        drawBattleBase(battleMonsterID, currentDialogue, 1);
                    }
                }

                // Reset counter, end turn
                if (turnTimeCounter > 180) {
                    drawBattleBase(battleMonsterID, currentDialogue, 1);

                    // End turn, reset counters. If monster is dead, skip to that part
                    if(gp.monster[battleMonsterID].life > 0) {
                        gp.monster[battleMonsterID].isTakingTurn = true;
                    }

                    gp.player.isTakingTurn = false;
                    turnTimeCounter = 0;
                }
            }

            // Monster turn next!!!
            if (gp.monster[battleMonsterID].isTakingTurn) {
                turnTimeCounter++;

                    // Basic animation
                    if (turnTimeCounter <= 10) {
                        currentDialogue = gp.monster[battleMonsterID].name + " attacks --";

                        drawBattleBackground();
                        drawBattleTopFrame(currentDialogue, 0);
                        drawBattleBottomFrame(1, gp.TILE_SIZE * 8);

                        // What goes up...
                        g2.drawImage(uTool.scaleImage(gp.monster[battleMonsterID].down2, (gp.TILE_SIZE * 2) + 24,
                                (gp.TILE_SIZE * 2) + 24), gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5 - (turnTimeCounter * 3), null);
                    }
                    if(turnTimeCounter > 10 && turnTimeCounter <= 15) {
                        drawBattleBackground();
                        drawBattleTopFrame(currentDialogue, 0);
                        drawBattleBottomFrame(1, gp.TILE_SIZE * 8);

                        g2.drawImage(uTool.scaleImage(gp.monster[battleMonsterID].down2, (gp.TILE_SIZE * 2) + 24,
                                (gp.TILE_SIZE * 2) + 24), gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5 - 30, null);
                    }
                    if(turnTimeCounter > 15 && turnTimeCounter < 35) {
                        drawBattleBackground();
                        drawBattleTopFrame(currentDialogue, 0);
                        drawBattleBottomFrame(1, gp.TILE_SIZE * 8);

                        // Two new subwindows, y positions, fade back to resting state
                        g2.drawImage(uTool.scaleImage(gp.monster[battleMonsterID].down2, (gp.TILE_SIZE * 2) + 24,
                                (gp.TILE_SIZE * 2) + 24), gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 4 + (turnTimeCounter * 3) - 30, null);
                    }
                    // Damage calc
                    if (turnTimeCounter == 35) {
                        screenFlash();

                        currentDamageDealt = gp.battleManager.getDamageCalc(gp.monster[battleMonsterID], gp.player);
                        gp.playSoundEffect(5);
                        gp.player.life -= currentDamageDealt;
                    }
                    if (turnTimeCounter > 35 && turnTimeCounter <= 65) {
                        currentDialogue = "OUCH! " + currentDamageDealt + " damage!";

                        // drawBattleBase(battleMonsterID, currentDialogue, 1);

                        // Initial positions
                        drawBattleBackground();
                        drawBattleEnemies(battleMonsterID);

                        drawBattleBottomFrame(1, gp.TILE_SIZE * 8 + 24);
                        drawBattleTopFrame(currentDialogue, 0); // This one is easy

                        // Visual effects, SUPER bare bones right now
//                        if (turnTimeCounter <= 93) {
//                            idleAnimationCounter = 20; // Standing image for attack
//                            //screenFlash();
//                        }
//                        if (turnTimeCounter > 97 && turnTimeCounter <= 100) {
//                            //screenFlash();
//                        }
//                        if (turnTimeCounter > 103 && turnTimeCounter <= 106) {
//                            //screenFlash();
//                        }
//                        if (turnTimeCounter == 113 || turnTimeCounter == 120) {
//                            idleAnimationCounter = 0;
//                        }
                    }
                    if (turnTimeCounter > 65 && turnTimeCounter <= 110) {

                    if(turnTimeCounter % 2 == 0) {
                        turnTimeAssist = turnTimeCounter / 2;
                    }

                    drawBattleBackground();
                    drawBattleEnemies(battleMonsterID);

                    drawBattleBottomFrame(1, (gp.TILE_SIZE * 8 + 56) - turnTimeAssist) ; // 89 being 65 + the initial offset of 24
                    drawBattleTopFrame(currentDialogue, 0);
                }
                    // Reset counter, end turn
                    if (turnTimeCounter > 110) {
                    drawBattleBase(battleMonsterID, currentDialogue, 1);

                    // End turn, reset counters
                    gp.monster[battleMonsterID].isTakingTurn = false;
                    turnTimeCounter = 0;
                    currentDialogue = "";
                }
            }

             // Return to idle once both turns end
            if (!gp.player.isTakingTurn && !gp.monster[battleMonsterID].isTakingTurn) {
                drawBattleBase(battleMonsterID, currentDialogue, 1);
                // THIS is the Monster death check
                if(gp.monster[battleMonsterID].life > 0) {
                    visibleBattleInventory.clear();
                    gp.player.battleItemMenu = false;
                    gp.player.battleMenuOn = false;
                    battleCounter = 1; // Return to main
                } else {
                    battleCounter = 3;
                    battleOver = true;
                    genericCounter = 0;
                }
            }
        }

        // Dying animation, shift back to PLAY_STATE
        if(battleCounter == 3) {
            // We maybe eventually want to drawString to let the player know to hit Enter here
            genericCounter++;

            if(battleOver) {
                if (genericCounter <= 49) {
                    double doubleGCounter = genericCounter;
                    float alphaValue = (float) (100 - (doubleGCounter * 2)) / 100;

                    currentDialogue = gp.monster[battleMonsterID].name + " was defeated!";

                    // Standard battle draw
                    drawBattleBackground();
                    drawBattleTopFrame(currentDialogue, 0);
                    drawBattleBottomFrame(1, gp.TILE_SIZE * 8);

                    // Sprite gets tricky
                    BufferedImage deathSprite = uTool.scaleImage(gp.monster[battleMonsterID].down1, (gp.TILE_SIZE * 2) + 24, (gp.TILE_SIZE * 2) + 24);

                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
                    g2.drawImage(deathSprite, gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5, null);
                }
                else if (genericCounter <= 98) {
                    drawBattleBackground();
                    drawBattleTopFrame(currentDialogue, 0);
                    drawBattleBottomFrame(1, gp.TILE_SIZE * 8);
                }
                else {
                    currentDialogue = "You got some free stuff. Wow!";
                    drawBattleBackground();
                    drawBattleTopFrame(currentDialogue, 0);
                    drawBattleBottomFrame(1, gp.TILE_SIZE * 8);

                    // Adds exp once, dodges null pointer
                    if(gp.monster[battleMonsterID] != null) {
                        gp.player.exp += gp.monster[battleMonsterID].exp;
                        gp.monster[battleMonsterID] = null;
                    }
                }
            }

            if(!battleOver) {
                currentDialogue = "You got away safely!";
                drawBattleBase(battleMonsterID,currentDialogue,1);
            }
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 36, 36);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y +5, width - 10, height - 10, 36, 36);
    }

    public void drawBattleBase(int monsterIndex, String topFrameText, int playerCount) {
        drawBattleBackground();
        drawBattleEnemies(monsterIndex);
        drawBattleTopFrame(topFrameText, 0);
        drawBattleBottomFrame(playerCount, gp.TILE_SIZE * 8);
    }

    // I'd LIKE to write methods for standard transitions in the future, but it isn't a priority at the moment
    public void drawBattleBackground() {
        // Y'all know me, I hop in that bitch and have a HEART ATTACK
        if(redCounter % 2 == 0) {
            bgRedValue = redCounter / 2;
        }
        if(greenCounter % 2 == 0) {
            bgGreenValue = greenCounter / 2;
        }
        if(blueCounter % 2 == 0) {
            bgBlueValue = blueCounter / 2;
        } // Color shift speed controller essentially

        g2.setColor(new Color(bgRedValue, bgGreenValue, bgBlueValue));
        g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.TILE_SIZE * 3); // Background behind top box

        g2.setColor(new Color(bgRedValue + 15, bgGreenValue + 15, bgBlueValue + 15)); // Six rectangles, one tile each
        g2.fillRect(0, gp.TILE_SIZE * 3, gp.SCREEN_WIDTH, gp.TILE_SIZE + 24);

        g2.setColor(new Color(bgRedValue + 30, bgGreenValue + 30, bgBlueValue + 30));
        g2.fillRect(0, (gp.TILE_SIZE * 4) + 24, gp.SCREEN_WIDTH, gp.TILE_SIZE + 24);

        g2.setColor(new Color(bgRedValue + 45, bgGreenValue + 45, bgBlueValue + 45));
        g2.fillRect(0, gp.TILE_SIZE * 6, gp.SCREEN_WIDTH, gp.TILE_SIZE + 24);

        g2.setColor(new Color(bgRedValue + 60, bgGreenValue + 60, bgBlueValue + 60));
        g2.fillRect(0, (gp.TILE_SIZE * 7) + 24, gp.SCREEN_WIDTH, gp.TILE_SIZE + 24);

        g2.setColor(new Color(bgRedValue + 75, bgGreenValue + 75, bgBlueValue + 75));
        g2.fillRect(0, gp.TILE_SIZE * 9, gp.SCREEN_WIDTH, gp.TILE_SIZE + 24);

        g2.setColor(new Color(bgRedValue + 90, bgGreenValue + 90, bgBlueValue + 90)); // Background behind bottom box
        g2.fillRect(0, (gp.TILE_SIZE * 10) + 24, gp.SCREEN_WIDTH, gp.TILE_SIZE + 24);
    }
    public void drawBattleTopFrame(String displayText, int frameY) {
        // This is where text delay and other style things go
        drawSubWindow(0, frameY, gp.SCREEN_WIDTH, gp.TILE_SIZE * 3);

        g2.setColor(Color.white);
        g2.setFont(arial_32);
        g2.drawString(displayText,32, frameY + 48 + 12);
    }

    // I'll probably have to pass the whole Player OBJECT into this eventually, even just for stats and data
    public void drawBattleBottomFrame(int playerCount, int frameY) {
        if(playerCount == 1) {
            battleCharacterText = "";

            drawSubWindow(gp.TILE_SIZE * 6, frameY, gp.TILE_SIZE * 4, (gp.TILE_SIZE * 4) - 24);

            g2.setColor(Color.white);

            g2.setFont(new Font("Monospaced", Font.BOLD, 32));
            g2.drawString(gp.player.displayName, gp.TILE_SIZE * 7 - 18, frameY + 36);

            g2.fillRect(gp.TILE_SIZE * 6 + 6, frameY + 48, gp.TILE_SIZE * 4 - 12, 4);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 27));
            g2.drawString("Life: ", gp.TILE_SIZE * 7 - 32, frameY + 48 + 36);
            g2.drawString("Mana: ", gp.TILE_SIZE * 7 - 32, frameY + 48 + 48 + 36);

            battleCharacterText = "0" + gp.player.life;
            g2.drawString(battleCharacterText, gp.TILE_SIZE * 9, frameY + 48 + 36);

            battleCharacterText = "--"; // This is for Mana or PP or whatever, it's not even coded yet
            g2.drawString(battleCharacterText, gp.TILE_SIZE * 9, frameY + 48 + 48 + 36);
        }

        if(playerCount == 2) { } // These are for adjusting the displayed screens based on party size
        if(playerCount == 3) { }
        if(playerCount == 4) { }
    }
    public void drawBattleMenu() {
        // Main menu
        if(!gp.player.battleItemMenu) {
            visibleBattleInventory.clear();

            // No conditional for item usage yet, one thing at a time Kurt
            final int INITIAL_TEXT_Y = gp.TILE_SIZE * 9 - 12;
            int textY = INITIAL_TEXT_Y;

            // Initial sub window
            drawSubWindow(gp.TILE_SIZE * 10, gp.TILE_SIZE * 8, gp.TILE_SIZE * 4, (gp.TILE_SIZE * 3) - 24);

            // Slots
            final int ROW_X = gp.TILE_SIZE * 10 + 12;
            final int rowYstart = gp.TILE_SIZE * 8 + 12; // Y of first row
            int rowY = rowYstart;
            int rowSize = gp.TILE_SIZE - 12; // Sets space between items

            // Looks like it's misaligned, but only because drawString and fillRoundRect use different coordinate systems
            int cursorY = rowYstart + (32 * battleRow);
            int cursorWidth = gp.TILE_SIZE * 4;
            int cursorHeight = gp.TILE_SIZE - 18;

            // DRAW CURSOR
            g2.setColor(Color.gray);
            g2.fillRoundRect(ROW_X, cursorY, cursorWidth - 24, cursorHeight, 25, 25);
            g2.setColor(Color.white);

            for (int i = 0; i < gp.player.BATTLE_MENU_OPTIONS.length; i++) {
                g2.drawString("- " + gp.player.BATTLE_MENU_OPTIONS[i], gp.TILE_SIZE * 11 - 32, textY);
                textY += 32;
            }
        }

        // Item menu
        if(gp.player.battleItemMenu) {
            // No conditional for item usage yet, one thing at a time Kurt
            final int INITIAL_TEXT_Y = gp.TILE_SIZE * 9 - 12;
            int textY = INITIAL_TEXT_Y;

            // Initial sub window
            drawSubWindow(gp.TILE_SIZE * 10, gp.TILE_SIZE * 8, gp.TILE_SIZE * 5 + 24, (gp.TILE_SIZE * 3) - 24);

            // Slots
            final int ROW_X = gp.TILE_SIZE * 10 + 12;
            final int rowYstart = gp.TILE_SIZE * 8 + 12; // Y of first row
            int rowY = rowYstart;
            int rowSize = gp.TILE_SIZE - 12; // Sets space between items

            // Looks like it's misaligned, but only because drawString and fillRoundRect use different coordinate systems
            int cursorY = rowYstart + (32 * battleRow);
            int cursorWidth = gp.TILE_SIZE * 5;
            int cursorHeight = gp.TILE_SIZE - 18;

            // DRAW CURSOR
            g2.setColor(Color.gray);
            g2.fillRoundRect(ROW_X, cursorY, cursorWidth, cursorHeight, 25, 25);

            // Leaving this commented out for now, this is how we displayed the item menu pre-List

            // This probably resembles the scrolling message list more than anything. Let me think...
//            for (int i = 0; i < gp.player.inventory.size(); i++) {
//                // Maybe we assign visibility to the first three items of the inventory automatically somewhere?
//
//                // First check for visibility
//                if(gp.player.inventory.get(i).isBattleMenuVisible) {
//                    // Then check for usability
//                    if(gp.player.inventory.get(i).battleItem) {
//                        g2.setColor(Color.white);
//                    }
//                    if(!gp.player.inventory.get(i).battleItem) {
//                        g2.setColor(Color.lightGray);
//                    }
//
//                    g2.drawString("- " + gp.player.inventory.get(i).name, gp.TILE_SIZE * 11 - 32, textY);
//                    textY += 32;
//                }
//            }

            for(int i = 0; i < visibleBattleInventory.size(); i++) {
                if(visibleBattleInventory.get(i).battleItem) {
                    g2.setColor(Color.white);
                }
                if(!visibleBattleInventory.get(i).battleItem) {
                    g2.setColor(Color.lightGray);
                }
                g2.drawString("- " + visibleBattleInventory.get(i).name,gp.TILE_SIZE * 11 - 32, textY);
                textY += 32;
            }
        }
    }

    // This will eventually have conditional switches for varying groups of enemies
    public void drawBattleEnemies(int monsterId) {
        if(gp.monster[monsterId] != null) {

            // Scaling image in battle for now, probably not ideal for all time
            BufferedImage fightSprite;
            idleAnimationCounter++;

            if (idleAnimationCounter <= 20) {
                fightSprite = uTool.scaleImage(gp.monster[monsterId].down1, (gp.TILE_SIZE * 2) + 24, (gp.TILE_SIZE * 2) + 24);
                g2.drawImage(fightSprite, gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5, null);
            }
            if (idleAnimationCounter > 20 && idleAnimationCounter < 40) {
                fightSprite = uTool.scaleImage(gp.monster[monsterId].down2, (gp.TILE_SIZE * 2) + 24, (gp.TILE_SIZE * 2) + 24);
                g2.drawImage(fightSprite, gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5, null);
            }
            if (idleAnimationCounter == 40) {
                // No real way around drawing this for a single frame while we reset the counter
                fightSprite = uTool.scaleImage(gp.monster[monsterId].down2, (gp.TILE_SIZE * 2) + 24, (gp.TILE_SIZE * 2) + 24);
                g2.drawImage(fightSprite, gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5, null);
                idleAnimationCounter = 0;
            }
        }
    }

    // This is going to require a method JUST for drawing the Monster, and likely another for attack animations

    public void drawInventory() {
        // FRAME
        int frameX = gp.TILE_SIZE * 9;
        int frameY = gp.TILE_SIZE;
        int frameWidth = gp.TILE_SIZE * 6;
        int frameHeight = gp.TILE_SIZE * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 16;
        final int slotYstart = frameY + 16;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.TILE_SIZE + 3; // Sets space between items

        // CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int curseWidth = gp.TILE_SIZE;
        int cursorHeight = gp.TILE_SIZE;

        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX,cursorY,curseWidth,cursorHeight, 10, 10);

        // DRAW PLAYER ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++) {

            // EQUIP CURSOR
            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(Color.orange);
                g2.fillRoundRect(slotX, slotY, gp.TILE_SIZE,gp.TILE_SIZE, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight + 24;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.TILE_SIZE * 3;

        // DESCRIPTION TEXT
        int textX = dFrameX + 24;
        int textY = dFrameY + gp.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(28f));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public void updateColorCounter(int currentColorCount) {
        if(currentColorCount == 0) {
            if (redCounter < 330) { // 165 * 2 (Maximum value floor times play rate inverted)
                redCounter++;
            } else {
                colorCounter++;
            }
        } // Initial fade in
        if(currentColorCount == 1) {
            if (blueCounter < 330) {
                blueCounter++;
                redCounter--;
            } else {
                colorCounter++;
            }
        } // Main loop begins
        if(currentColorCount == 2) {
            if (greenCounter < 330) {
                greenCounter++;
                blueCounter--;
            } else {
                colorCounter++;
            }
        }
        if(currentColorCount == 3) {
            if (redCounter < 330) {
                redCounter++;
                greenCounter--;
            } else {
                colorCounter = 1; // Reset to loop start
            }
        }
    }
    public void screenFlash() {
        g2.setColor(Color.white);
        g2.fillRect(0,0,gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
    }

    // In battle damage flash, only coded to slime type enemy right now
    public void enemyFlash() {
        g2.drawImage(uTool.scaleImage(gp.monster[battleMonsterID].stun, (gp.TILE_SIZE * 2) + 24,
                (gp.TILE_SIZE * 2) + 24), gp.TILE_SIZE * 7 - 12, gp.TILE_SIZE * 5, null);
    }
    public int getItemIndexOnSlot() {
        return slotCol + (slotRow * 5);
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.SCREEN_WIDTH / 2) - (length/2);
        return x;
    }

    public int getXforAlignToRight(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
