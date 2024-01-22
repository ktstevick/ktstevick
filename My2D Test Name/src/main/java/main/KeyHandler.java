package main;

import entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.LinkedList;

// Handles all keyboard input
public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;

    // DEBUG
    public boolean checkDrawTime;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Returns number of key pressed
        int code = e.getKeyCode();

        // GAME STATE
        if (gp.gameState == gp.TITLE_STATE) { titleState(code); }
        else if (gp.gameState == gp.PLAY_STATE) { playState(code); }
        else if (gp.gameState == gp.PAUSE_STATE) { pauseState(code); }
        else if(gp.gameState == gp.DIALOGUE_STATE) { dialogueState(code); }
        else if(gp.gameState == gp.CHARACTER_STATE) { characterState(code); }
        else if(gp.gameState == gp.BATTLE_STATE) { battleState(code); }
    }

    // State/Key management
    public void titleState(int code) {
        if(gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) { // Menu logic loop
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    // gp.ui.titleScreenState = 1;
                    gp.gameState = gp.PLAY_STATE;
                    //gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {
                    // Nothing yet
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

//          else if(gp.ui.titleScreenState == 1) {
//                if (code == KeyEvent.VK_W) {
//                    gp.ui.commandNum--;
//                    if (gp.ui.commandNum < 0) { // Menu logic loop
//                        gp.ui.commandNum = 3;
//                    }
//                }
//                if (code == KeyEvent.VK_S) {
//                    gp.ui.commandNum++;
//                    if (gp.ui.commandNum > 3) {
//                        gp.ui.commandNum = 0;
//                    }
//                }
//                if (code == KeyEvent.VK_ENTER) {
//                    if (gp.ui.commandNum == 0) {
//                        System.out.println("Do some fighter specific stuff here!");
//                        gp.gameState = gp.PLAY_STATE;
//                    }
//                    if (gp.ui.commandNum == 1) {
//                        System.out.println("Do some thief specific stuff here!");
//                        gp.gameState = gp.PLAY_STATE;
//                    }
//                    if (gp.ui.commandNum == 1) {
//                        System.out.println("Do some sorcerer specific stuff here!");
//                        gp.gameState = gp.PLAY_STATE;
//                    }
//                    if (gp.ui.commandNum == 3) {
//                        gp.ui.titleScreenState = 0;
//                        gp.stopMusic();
//                    }
//                }
//            }
    }
    public void playState(int code) {
            // Conditionals for operation assignment based on key
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            // PAUSE STATE
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.PAUSE_STATE;
            }
            if (code == KeyEvent.VK_C) {
                // Cute idea - slump and open backpack animation, allow the world to render but stay away?!
                gp.gameState = gp.CHARACTER_STATE;
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT) {
                gp.player.speed = 5;
            }

            if(code == KeyEvent.VK_F) {
                shotKeyPressed = true;
            }

            // DEBUG
            if (code == KeyEvent.VK_T) {
                if (!checkDrawTime) {
                    checkDrawTime = true;
                } else if (checkDrawTime) {
                    checkDrawTime = false;
                }
            }

    }
    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.PLAY_STATE;
        }
    }
    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.PLAY_STATE;
        }
    }
    public void characterState(int code) {
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.PLAY_STATE;
        }

        if(code == KeyEvent.VK_W) {
            if(gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
            } else {
                gp.ui.slotRow = 3;
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
            } else {
                gp.ui.slotCol = 4;
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
            } else {
                gp.ui.slotRow = 0;
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
            } else {
                gp.ui.slotCol = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }
    public void battleState(int code) {
        if(gp.ui.battleCounter == 3 && gp.ui.genericCounter > 98) {
            if (code == KeyEvent.VK_ENTER) {
                    gp.gameState = gp.PLAY_STATE;
            }
        }

        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.PLAY_STATE;
        }

        // NONE of this works if the Battle Menu isn't visible
        if(gp.player.battleMenuOn) {
            // Item menu
            if(gp.player.battleItemMenu) {
                // Handles item sub menu operations
                if (code == KeyEvent.VK_W) {
                    if (gp.ui.battleRow != 0) {
                        gp.ui.battleRow--;
                    } else {
                        // THIS is where it needs to change the visibility of items
                        int outsideCounter = 0;

                        // I'm THINKING of a quick for loop here, that finds the first "true" and switches it to false, and then
                        // finds the last "false" and switches it to true
                        for(int i = 0; i < gp.player.inventory.size(); i++) {
                            if(outsideCounter == 0 && i > 0) {
                                // BECAUSE we're only showing three items right now, the position of the last false is
                                // always EXACTLY two more than the position of the first true
                                if(gp.player.inventory.get(i).isBattleMenuVisible) {
                                    gp.player.inventory.get(i - 1).isBattleMenuVisible = true;
                                    gp.player.inventory.get(i + 2).isBattleMenuVisible = false;

                                    gp.ui.visibleBattleInventory.clear();

                                    gp.ui.visibleBattleInventory.add(gp.player.inventory.get(i - 1));
                                    gp.ui.visibleBattleInventory.add(gp.player.inventory.get(i));
                                    gp.ui.visibleBattleInventory.add(gp.player.inventory.get(i + 1));

                                    outsideCounter++;
                                }
                            }

                            if(i == gp.player.inventory.size()) {
                                outsideCounter = 0;
                            }
                        }
                    }
                }
                if (code == KeyEvent.VK_S) {
                    if (gp.ui.battleRow != 2) {
                        gp.ui.battleRow++;
                    } else {
                        // THIS is where it needs to change the visibility of items
                        int outsideCounter = 0;

                        // I'm THINKING of a quick for loop here, that finds the first "true" and switches it to false, and then
                        // finds the last "false" and switches it to true
                        for(int i = 0; i < gp.player.inventory.size(); i++) {
                            if(outsideCounter == 0 && i < gp.player.inventory.size() - 3) {
                                // BECAUSE we're only showing three items right now, the position of the last false is
                                // always EXACTLY two more than the position of the first true
                                if(gp.player.inventory.get(i).isBattleMenuVisible) {
                                    gp.player.inventory.get(i).isBattleMenuVisible = false;
                                    gp.ui.visibleBattleInventory.remove(0);
                                    // gp.player.inventory.get(i + 1).isBattleMenuVisible = true;
                                    // gp.player.inventory.get(i + 2).isBattleMenuVisible = true;
                                    gp.player.inventory.get(i + 3).isBattleMenuVisible = true;
                                    gp.ui.visibleBattleInventory.add(gp.player.inventory.get(i + 3));
                                    outsideCounter++;
                                }
                            }

                            if(i == gp.player.inventory.size()) {
                                outsideCounter = 0;
                            }
                        }
                    }
                }
                if (code == KeyEvent.VK_A) {
                    gp.ui.battleRow = 1;
                    gp.player.battleItemMenu = false;
                    gp.ui.currentDialogue = gp.monster[gp.ui.battleMonsterID].idleMessage;
                }

                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.battleRow == 0) {
                        if(!gp.ui.visibleBattleInventory.get(0).battleItem) {
                            // Just prints right now
                            gp.ui.currentDialogue = "You can't use the " + gp.ui.visibleBattleInventory.get(0).name + " like this.";
                        }
                        if(gp.ui.visibleBattleInventory.get(0).battleItem) {
                            // isItemUsable can only check for 1 (Healing item) right now
                            if(!gp.player.isItemUsable(1)) {
                                gp.ui.currentDialogue = "The " + gp.ui.visibleBattleInventory.get(0).name + " won't have any effect.";
                            }
                            if(gp.player.isItemUsable(1)) {
                                // I want to use the item, let the monster go, delete the item, update the list, and maybe display a message about the item

                                // Because we always add the first three, it shouldn't matter if we stop drawing the menu and go straight to phase 2
                                gp.ui.battleRow = 1;
                                gp.player.battleItemMenu = false;

                                gp.player.isTakingTurn = true;
                                gp.ui.battleCounter = 2;

                                gp.player.usedItem = true;
                                gp.player.inBattleSelectItem(0);
                            }
                        }

                    }
                    if(gp.ui.battleRow == 1) {
                        if(!gp.ui.visibleBattleInventory.get(1).battleItem) {
                            gp.ui.currentDialogue = "You can't use the " + gp.ui.visibleBattleInventory.get(1).name + " like this.";
                        }
                        if(gp.ui.visibleBattleInventory.get(1).battleItem) {
                            if(!gp.player.isItemUsable(1)) {
                                gp.ui.currentDialogue = "The " + gp.ui.visibleBattleInventory.get(1).name + " won't have any effect.";
                            }
                            if(gp.player.isItemUsable(1)) {
                                gp.player.battleItemMenu = false;

                                gp.player.isTakingTurn = true;
                                gp.ui.battleCounter = 2;

                                gp.player.usedItem = true;
                                gp.player.inBattleSelectItem(1);
                            }
                        }
                    }
                    if(gp.ui.battleRow == 2) {
                        if(!gp.ui.visibleBattleInventory.get(2).battleItem) {
                            gp.ui.currentDialogue = "You can't use the " + gp.ui.visibleBattleInventory.get(2).name + " like this.";
                        }
                        if(gp.ui.visibleBattleInventory.get(2).battleItem) {
                            if(!gp.player.isItemUsable(1)) {
                                gp.ui.currentDialogue = "The " + gp.ui.visibleBattleInventory.get(2).name + " won't have any effect.";
                            }
                            if(gp.player.isItemUsable(1)) {
                                gp.ui.battleRow = 1;
                                gp.player.battleItemMenu = false;

                                gp.player.isTakingTurn = true;
                                gp.ui.battleCounter = 2;

                                gp.player.usedItem = true;
                                gp.player.inBattleSelectItem(2);
                            }
                        }
                    }
                }
            }

            // Main menu
            if(!gp.player.battleItemMenu) {
            if (code == KeyEvent.VK_W) {
                if (gp.ui.battleRow != 0) {
                    gp.ui.battleRow--;
                } else {
                    gp.ui.battleRow = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                if (gp.ui.battleRow != 2) {
                    gp.ui.battleRow++;
                } else {
                    gp.ui.battleRow = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.battleRow == 0) {
                        // gp.player.attemptAttack = true;
                        gp.player.isTakingTurn = true;
                        gp.ui.battleCounter = 2;
                    }
                    if (gp.ui.battleRow == 1) {
                        gp.player.battleItemMenu = true;
                        gp.ui.battleRow = 0;

                        for(int i = 0; i < gp.player.inventory.size(); i++) {
                            if(i < 3) {
                                gp.player.inventory.get(i).isBattleMenuVisible = true;
                                gp.ui.visibleBattleInventory.add(gp.player.inventory.get(i)); // Adds first three to visible list
                            } else {
                                gp.player.inventory.get(i).isBattleMenuVisible = false;
                            }
                        }

                        gp.ui.currentDialogue = gp.monster[gp.ui.battleMonsterID].idleMessage;
                    }
                    if (gp.ui.battleRow == 2) {
                        gp.ui.battleCounter = 3;
                    }
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if(code == KeyEvent.VK_SHIFT) {
            gp.player.speed = 3;
        }

        if(code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }

    }
}
