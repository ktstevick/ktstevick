package stevick.pokemon;

import stevick.types.Nature;

// I'm gonna have to change this to a DAO now that I know that's a thing
public class Pokemon {
    // Basic information about this Pokemon. Still not 100% sure how I'm going to use nature here, this is just a placeholder
    private String name = "";
    private String type1 = "";
    private String type2 = "";
    private Nature nature;
    private int LEVEL = 100;

    // Base stat constants. These TRULY never change!
    private int BASE_HP = 0;
    private int BASE_ATK = 0;
    private int BASE_DEF = 0;
    private int BASE_SPA = 0;
    private int BASE_SPD = 0;
    private int BASE_SPEED = 0;

    // IV variables. All set to 31, but eventually you'll be able to adjust them. Maybe. Leaving them final for the time being
    private int IV_FOR_HP = 31;
    private int IV_FOR_ATK = 31;
    private int IV_FOR_DEF = 31;
    private int IV_FOR_SPA = 31;
    private int IV_FOR_SPD = 31;
    private int IV_FOR_SPEED = 31;

    // EV variables. Not setting them to final, since I'll be developing methods to change them with soon
    private int evTotalHP = 0;
    private int evTotalAtk = 0;
    private int evTotalDef = 0;
    private int evTotalSpA = 0;
    private int evTotalSpD = 0;
    private int evTotalSpeed = 0;

    // Getters and setters. Not all of these have setters right now. Be mindful!
    public String getName() {
        return name;
    }
    public String getType1() {
        return type1;
    }
    public String getType2() {
        return type2;
    }
    public String getNature() {
        return this.nature.getNatureName();
    }

    public int getLevel(){
        return LEVEL;
    }
    public void setLevel(int level) {
        this.LEVEL = level;
    }
    public int getEvTotalHP() {
        return evTotalHP;
    }
    public void setEvTotalHP(int EVs) {
        this.evTotalHP = EVs;
    }
    public int getEvTotalAtk() {
        return evTotalAtk;
    }
    public void setEvTotalAtk(int EVs) {
        this.evTotalAtk = EVs;
    }
    public int getEvTotalDef() {
        return evTotalDef;
    }
    public void setEvTotalDef(int EVs) {
        this.evTotalDef = EVs;
    }
    public int getEvTotalSpA() {
        return evTotalSpA;
    }
    public void setEvTotalSpA(int EVs) {
        this.evTotalSpA = EVs;
    }
    public int getEvTotalSpD() {
        return evTotalSpD;
    }
    public void setEvTotalSpD(int EVs) {
        this.evTotalSpD = EVs;
    }
    public int getEvTotalSpeed() {
        return evTotalSpeed;
    }
    public void setEvTotalSpeed(int EVs) {
        this.evTotalSpeed = EVs;
    }

    // Derived Getters
    public int getEffectiveHP() {
        int effectiveHP = 0;

        // GameFreak was on some CRAZY drugs in the late eighties
        effectiveHP = (((((2 * BASE_HP) + IV_FOR_HP + (evTotalHP / 4)) * LEVEL) / 100) + LEVEL + 10);

        return effectiveHP;
    }
    public int getEffectiveAttack() {
        // I'm thinking THIS is actually where we'd wanna put a Nature conditional, to check if it boosts this stat. Not today tho
        int effectiveAttack = 0;

        effectiveAttack = (int)((((((2 * BASE_ATK) + IV_FOR_ATK + (evTotalAtk / 4)) * LEVEL) / 100) + 5) * this.nature.getAtkMultiplier());

        return effectiveAttack;
    }
    public int getEffectiveDefense() {
        // In slightly fewer words
        return (int)((((((2 * BASE_DEF) + IV_FOR_DEF + (evTotalDef / 4)) * LEVEL) / 100) + 5) * this.nature.getDefMultiplier());
    }
    public int getEffectiveSpA() {
        return (int)((((((2 * BASE_SPA) + IV_FOR_SPA + (evTotalSpA / 4)) * LEVEL) / 100) + 5) * this.nature.getSpaMultiplier());
    }
    public int getEffectiveSpDef() {
        return (int)((((((2 * BASE_SPD) + IV_FOR_SPD + (evTotalSpD / 4)) * LEVEL) / 100) + 5) * this.nature.getSpdMultiplier());
    }
    // Speed Getter goes unused for now
    public int getEffectiveSpeed() {
        return (int)((((((2 * BASE_SPEED) + IV_FOR_SPEED + (evTotalSpeed / 4)) * LEVEL) / 100) + 5) * this.nature.getSpeedMultiplier());
    }

    // Constructor. This is gonna be a beast, but the user doesn't see any of this stuff
    public Pokemon(String name, int baseHP, int baseAtk, int baseDef, int baseSpA, int baseSpD, int baseSpeed, String type1, String type2, Nature nature) {
        this.name = name;
        this.BASE_HP = baseHP;
        this.BASE_ATK = baseAtk;
        this.BASE_DEF = baseDef;
        this.BASE_SPA = baseSpA;
        this.BASE_SPD = baseSpD;
        this.BASE_SPEED = baseSpeed;
        this.type1 = type1;
        this.type2 = type2;

        // This gets a bit tricky with the nature, bear with me
        this.nature = nature;
    }
}
