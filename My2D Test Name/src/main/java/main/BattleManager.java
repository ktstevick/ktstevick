package main;

import entity.Entity;

// Exists specifically to run Battle State operations
public class BattleManager {
    GamePanel gp;

    public BattleManager(GamePanel gp) {
        this.gp = gp;

        // So far so good.
    }

    // VERY bare bones right now. Should work though
    public int getDamageCalc(Entity attacker, Entity defender) {
        int damageDealt = 0;

        if(attacker != null && defender != null) {
            // Now some bullshit happens
            damageDealt = attacker.attack - defender.defense;
        }

        return damageDealt;
    }
}
