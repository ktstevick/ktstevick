package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean shiftPressed;

    private boolean debugOn = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // DIRECTIONAL INPUTS
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }

        // GAME STATE CONDITIONALS
        if(gp.getGameState() == gp.getPlayState()) {
            if(code == KeyEvent.VK_ENTER) {
                gp.setGameState(gp.getPauseState());
            }

        } else if (gp.getGameState() == gp.getPauseState()) {
            if(code == KeyEvent.VK_ENTER) {
                gp.setGameState(gp.getPlayState());
            }

        } else if(gp.getGameState() == gp.getDialogueState()) {
            if(code == KeyEvent.VK_ENTER) {
                gp.getPanelUI().setDialogueIndex(gp.getPanelUI().getDialogueIndex() + 1); // Next message
            }
        }

        // DEBUG
        if(code == KeyEvent.VK_P) {
            debugOn = !debugOn;
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
            shiftPressed = false;
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isShiftPressed() {
        return shiftPressed;
    }

    public boolean isDebugOn() {
        return debugOn;
    }
}
