package stevick.example;

import stevick.moves.Move;
import stevick.pokemon.Pokemon;
import stevick.types.Nature;
import stevick.types.TypeChart;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("I want to be the very best!");

        // Scanner and File Objects, then the first few Variables
        Scanner userInput = new Scanner(System.in);

        // Pulling from text files for now, but we'll update this to the appropriate databases in the future
        File dexFile = new File("src/main/java/stevick/pokemon/nationaldex.txt");
        File moveFile = new File("src/main/java/stevick/moves/movelist.txt");

        boolean isSpecialMove = false;
        String relevantAttackingStat = "Atk";
        String relevantDefendingStat = "Def";

        // These are the variables used to instantiate the Pokemon Objects later. These are placeholders that get replaced by user input
        String attackerName = "Bulbasaur";
        int attackerBaseHP = 0;
        int attackerBaseAtk = 0;
        int attackerBaseDef = 0;
        int attackerBaseSpA = 0;
        int attackerBaseSpD = 0;
        int attackerBaseSpeed = 0;
        String attackerType1 = "";
        String attackerType2 = "";
        // These are set manually for now, but the full functionality is in there. Just have to make the inputs more seamless I think
        String attackerNatureName = "Adamant";

        int offensiveEVs = 0;

        String defenderName = "Charmander";
        int defenderBaseHP = 0;
        int defenderBaseAtk = 0;
        int defenderBaseDef = 0;
        int defenderBaseSpA = 0;
        int defenderBaseSpD = 0;
        int defenderBaseSpeed = 0;
        String defenderType1 = "";
        String defenderType2 = "";
        String defenderNatureName = "Adamant";

        int hitpointEVs = 0;
        int defensiveEVs = 0;

        // Default attack is set to Tackle
        String moveName = "Tackle";
        int moveBP = 40;
        String moveType = "Normal";
        String moveProperty = "Physical";

        /* This is where all the user prompts and things will be. I'll need to catch exceptions for bad inputs eventually.
        This kind of approach is fine for a CLI, but the truth is that things would be much simpler if the user could only
        select from a pre-determined list. I'll have to think on it more as I learn. At this stage, I think changing the interface
        so that the user creates multiple Pokemon, and can then arrange them at will, will facilitate testing. No sense prompting
        for the same information every time, let alone new and redundant information */
        System.out.println("Attacking Pokemon: ");
        attackerName = userInput.nextLine();

        System.out.println("Offensive EVs: ");
        offensiveEVs = Integer.parseInt(userInput.nextLine());

        System.out.println("What attack is it using?");
        moveName = userInput.nextLine();

        System.out.println("Defending Pokemon: ");
        defenderName = userInput.nextLine();

        System.out.println("HP EVs: ");
        hitpointEVs = Integer.parseInt(userInput.nextLine());

        System.out.println("Defensive EVs: ");
        defensiveEVs = Integer.parseInt(userInput.nextLine());

        System.out.println("Performing calculation...");
        System.out.println("---------------------------------------");

        // Move and move trait assignment
        try (Scanner moveScanner = new Scanner(moveFile)) {
            while (moveScanner.hasNextLine()) {
                String moveLine = moveScanner.nextLine();
                String[] moveData = moveLine.split("\\|");

                // This WILL throw an array exception if there is empty space after the attack names on the list
                if (moveData[1].equals(moveName)) {
                    moveBP = Integer.parseInt(moveData[2]);
                    moveType = moveData[3];
                    moveProperty = moveData[4];
                }
            }

        } catch (IOException e) {
            System.out.println("Can't find the proper Move List file. We'll go with Tackle for now");
        }

        // Pokemon and Pokemon trait assignments
        try (Scanner dexScanner = new Scanner(dexFile)) {
            // @TODO: write better catches to handle exceptions before moving on to the next stage!

            while(dexScanner.hasNextLine()) {
                // I have to strengthen this search soon. Pidgeot and Pidgeotto....
                String dexLine = dexScanner.nextLine();

                if (dexLine.contains(attackerName)) {
                    // String splitting and data assignments
                    String[] attackerData = dexLine.split(" ");

                    attackerBaseHP = Integer.parseInt(attackerData[2]);
                    attackerBaseAtk = Integer.parseInt(attackerData[3]);
                    attackerBaseDef = Integer.parseInt(attackerData[4]);
                    attackerBaseSpA = Integer.parseInt(attackerData[5]);
                    attackerBaseSpD = Integer.parseInt(attackerData[6]);
                    attackerBaseSpeed = Integer.parseInt(attackerData[7]);

                    attackerType1 = attackerData[8];
                    attackerType2 = attackerData[9];
                }

                if (dexLine.contains(defenderName)) {
                    String[] defenderData = dexLine.split(" ");

                    defenderBaseHP = Integer.parseInt(defenderData[2]);
                    defenderBaseAtk = Integer.parseInt(defenderData[3]);
                    defenderBaseDef = Integer.parseInt(defenderData[4]);
                    defenderBaseSpA = Integer.parseInt(defenderData[5]);
                    defenderBaseSpD = Integer.parseInt(defenderData[6]);
                    defenderBaseSpeed = Integer.parseInt(defenderData[7]);

                    defenderType1 = defenderData[8];
                    defenderType2 = defenderData[9];
                }
            }

        } catch (IOException e) {
            System.out.println("Can't find the National Dex file. Printing default calc");
        }

        // ***** CREATING RELEVANT OBJECTS *****
        TypeChart typeChart = new TypeChart(moveType, defenderType1, defenderType2);
        Move usedMove = new Move(moveName, moveBP, moveType, moveProperty);

        Nature attackerNature = new Nature(attackerNatureName);
        Nature defenderNature = new Nature(defenderNatureName);

        Pokemon attacker = new Pokemon(attackerName, attackerBaseHP, attackerBaseAtk, attackerBaseDef, attackerBaseSpA, attackerBaseSpD, attackerBaseSpeed, attackerType1, attackerType2, attackerNature);
        Pokemon defender = new Pokemon(defenderName, defenderBaseHP, defenderBaseAtk, defenderBaseDef, defenderBaseSpA, defenderBaseSpD, defenderBaseSpeed, defenderType1, defenderType2, defenderNature);

        defender.setEvTotalHP(hitpointEVs);

        int attackerLevel = attacker.getLevel();
        int effectiveAttack = 0;
        int effectiveDefense = 0;
        /* Testing for choice band currently, this implementation SEEMS perfect, but since different items have different
        effects, it's going to be a little more involved than I'd hoped. Can't just tag on a catch-all multiplier at the end
        of the damage calc. For type-boosting items, I'll have to directly manipulate the move's base power, and for stat changers,
        we'll have to change the stat as we retrieve it. */
        double itemMultiplier = 1.5;

        // These conditionals check move property and assign EVs accordingly.
        if (usedMove.getProperty().equals("Physical")) {
            attacker.setEvTotalAtk(offensiveEVs);
            defender.setEvTotalDef(defensiveEVs);
            effectiveAttack = (int)(attacker.getEffectiveAttack() * itemMultiplier);
            effectiveDefense = defender.getEffectiveDefense();
        }
        if (usedMove.getProperty().equals("Special")) {
            attacker.setEvTotalSpA(offensiveEVs);
            defender.setEvTotalSpD(defensiveEVs);
            isSpecialMove = true;
            relevantAttackingStat = "SpA";
            relevantDefendingStat = "SpD";
            effectiveAttack = attacker.getEffectiveSpA();
            effectiveDefense = defender.getEffectiveSpDef();
        }

        // ***** MODIFIER MANAGEMENT *****
        int random = 85;
        double stab = 1.0;

        String resultsDisplay = "";

        // STAB check
        if (usedMove.getType().equals(attacker.getType1()) || usedMove.getType().equals(attacker.getType2())) {
            // This works, but seems to push the rolls a touch high again. We'll keep an eye on it
            stab = 1.5;
        }

        // ***** THIS IS THE ONE *****
        for (int i = 0; i < 16; i++) {
            /* This is about to get bloody and kind of brutal. This was originally one long line of code, based on
            the actual handwritten formula - unfortunately, the doubles being processed gave me results that were slightly
            inconsistent with actual test cases. It took me five tries to land on this, but it seems PERFECT. */

            // ***** BASE DAMAGE FORMULA *****
            int initialDamage = ((((2 * attacker.getLevel()) / 5 + 2) * usedMove.getBasePower() * effectiveAttack) / effectiveDefense) / 50 + 2;

            // ***** MODIFIERS *****
            int randomModified = (initialDamage * (random + i)) / 100; // Because this first modifier always multiplies from .85 - 1.00, the high roll is ALWAYS the value of initialDamage
            int stabModified = (int)(randomModified * stab);
            double typeModified = (stabModified * (typeChart.getTotalMultiplier()));

            // Casting to an int
            int trueDamage = (int)typeModified;

            // Updating the String we need for later
            resultsDisplay = (resultsDisplay + trueDamage + ", ");
        }

        // Separate just so it's easier to work with. Dolling it up a bit
        String interactionDescription = (offensiveEVs + " " + relevantAttackingStat + " " + attacker.getName() + " " + usedMove.getName() + " vs. " + defensiveEVs + "HP / " + defender.getEvTotalDef() + " " + relevantDefendingStat + " " + defender.getName());

        // We create an array from the String we made earlier, then parseInt the numbers we need out of it
        String[] damageValues = resultsDisplay.split(", ");
        double lowRoll = Integer.parseInt(damageValues[0]);
        double highRoll = Integer.parseInt(damageValues[15]);

        // Percents for high and low damage dealt, floated to two decimals
        double lowPercent = ((lowRoll / defender.getEffectiveHP()) * 100);
        double highPercent = ((highRoll / defender.getEffectiveHP()) * 100);
        String presentedLowPercent = String.format("%.2f", lowPercent);
        String presentedHighPercent = String.format("%.2f", highPercent);

        String damageDescription = ((int)lowRoll + "-" + (int)highRoll + "(" + presentedLowPercent + "% - " + presentedHighPercent + "%)");

        // Flavor text and conditional assignment
        String effectivenessMessage = "";
        if (typeChart.getTotalMultiplier() > 1) {
            effectivenessMessage = " ---> It's super effective!";
        }
        if (typeChart.getTotalMultiplier() < 1 && typeChart.getTotalMultiplier() > 0) {
            effectivenessMessage = " ---> It's not very effective...";
        }
        if (typeChart.getTotalMultiplier() == 0) {
            effectivenessMessage = (" ---> It didn't affect " + defenderName + "...");
        }

        // Interaction summary, value range, percent estimate, and flavor text. Total spread of possible values follow
        System.out.println(interactionDescription + " = " + damageDescription + effectivenessMessage);
        System.out.println("Possible damage amounts: [" + resultsDisplay.substring(0,resultsDisplay.length() - 2) + "]");
    }
}