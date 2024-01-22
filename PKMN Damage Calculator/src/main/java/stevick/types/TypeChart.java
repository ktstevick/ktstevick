package stevick.types;

public class TypeChart {
    // The immunity flag doesn't have a function yet, but it will be useful later for sure.
    private String moveType = "";
    private String defenderType1 = "";
    private String defenderType2 = "";
    private double totalMultiplier = 1.0;
    private boolean immunityFlag = false;

    public String getMoveType() {
        return moveType;
    }
    public String getDefenderType1() {
        return defenderType1;
    }
    public String getDefenderType2() {
        return defenderType2;
    }
    public double getTotalMultiplier() {
        return totalMultiplier;
    }
    public boolean getImmunityFlag() {
        return immunityFlag;
    }

    // I could possibly bake it all into the constructor. I'll see where it makes the most sense. Actually yeah, probably here
    public TypeChart(String moveType, String defenderType1, String defenderType2) {
        this.moveType = moveType;
        this.defenderType1 = defenderType1;
        this.defenderType2 = defenderType2;
        // This is gonna be a WILD ride
        this.totalMultiplier = typeMultiplier(moveType, defenderType1) * typeMultiplier(moveType, defenderType2);
    }

    // ********** EMERGENT TYPE CHART **********
    public double typeMultiplier(String moveType, String defenderType) {
        double typeMultiplier = 1.0;

        if (moveType.equals("Normal")) {
            // Here's what I'm thinking. One row for resist, one row for super effective, and one for immunities. Defaults to one so no need for an else
            if (defenderType.equals("Rock") || defenderType.equals("Steel")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Ghost")){
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Fighting")) {
            if (defenderType.equals("Normal") || defenderType.equals("Rock") || defenderType.equals("Dark") || defenderType.equals("Steel") || defenderType.equals("Ice")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Flying") || defenderType.equals("Poison") || defenderType.equals("Bug") || defenderType.equals("Psychic") || defenderType.equals("Fairy")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Ghost")) {
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Flying")) {
            if (defenderType.equals("Fighting") || defenderType.equals("Bug") || defenderType.equals("Grass")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Rock") || defenderType.equals("Steel") || defenderType.equals("Electric")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Poison")) {
            if (defenderType.equals("Grass") || defenderType.equals("Fairy")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Poison") || defenderType.equals("Ground") || defenderType.equals("Rock") || defenderType.equals("Ghost")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Steel")) {
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Ground")) {
            if (defenderType.equals("Poison") || defenderType.equals("Rock") || defenderType.equals("Steel") || defenderType.equals("Fire") || defenderType.equals("Electric")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Bug") || defenderType.equals("Grass")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Flying")) {
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Rock")) {
            if (defenderType.equals("Flying") || defenderType.equals("Bug") || defenderType.equals("Fire") || defenderType.equals("Ice")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Fighting") || defenderType.equals("Ground") || defenderType.equals("Steel")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Bug")) {
            if (defenderType.equals("Grass") || defenderType.equals("Psychic") || defenderType.equals("Dark")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Fighting") || defenderType.equals("Flying") || defenderType.equals("Poison") || defenderType.equals("Ghost") || defenderType.equals("Steel") || defenderType.equals("Fire") || defenderType.equals("Fairy")) {
                // Poor bug types! This is overkill...
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Ghost")) {
            if (defenderType.equals("Ghost") || defenderType.equals("Psychic")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Dark")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Normal")) {
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Steel")) {
            if (defenderType.equals("Rock") || defenderType.equals("Ice") || defenderType.equals("Fairy")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Steel") || defenderType.equals("Fire") || defenderType.equals("Water") || defenderType.equals("Electric")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Fire")) {
            if (defenderType.equals("Bug") || defenderType.equals("Steel") || defenderType.equals("Grass") || defenderType.equals("Ice")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Rock") || defenderType.equals("Fire") || defenderType.equals("Water") || defenderType.equals("Dragon")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Water")) {
            if (defenderType.equals("Ground") || defenderType.equals("Rock") || defenderType.equals("Fire")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Water") || defenderType.equals("Grass") || defenderType.equals("Dragon")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Grass")) {
            if (defenderType.equals("Ground") || defenderType.equals("Rock") || defenderType.equals("Water")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Flying") || defenderType.equals("Poison") || defenderType.equals("Bug") || defenderType.equals("Steel") || defenderType.equals("Fire") || defenderType.equals("Grass") || defenderType.equals("Dragon")) {
                // I take it back, GameFreak hates grass types ;3;
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Electric")) {
            if (defenderType.equals("Flying") || defenderType.equals("Water")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Grass") || defenderType.equals("Electric") || defenderType.equals("Dragon")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Ground")) {
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Psychic")) {
            if (defenderType.equals("Fighting") || defenderType.equals("Poison")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Steel") || defenderType.equals("Psychic")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Dark")) {
                typeMultiplier = 0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Ice")) {
            if (defenderType.equals("Flying") || defenderType.equals("Ground") || defenderType.equals("Grass") || defenderType.equals("Dragon")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Steel") || defenderType.equals("Fire") || defenderType.equals("Water") || defenderType.equals("Ice")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Dragon")) {
            if (defenderType.equals("Dragon")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Steel")) {
                typeMultiplier = 0.5;
            }
            if (defenderType.equals("Fairy")) {
                typeMultiplier = 0.0;
                immunityFlag = true;
            }
        }
        if (moveType.equals("Dark")) {
            if (defenderType.equals("Ghost") || defenderType.equals("Psychic")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Fighting") || defenderType.equals("Dark") || defenderType.equals("Fairy")) {
                typeMultiplier = 0.5;
            }
        }
        if (moveType.equals("Fairy")) {
            if (defenderType.equals("Fighting") || defenderType.equals("Dragon") || defenderType.equals("Dark")) {
                typeMultiplier = 2.0;
            }
            if (defenderType.equals("Poison") || defenderType.equals("Steel") || defenderType.equals("Fire")) {
                typeMultiplier = 0.5;
            }
        }

        return typeMultiplier;
    }
}
