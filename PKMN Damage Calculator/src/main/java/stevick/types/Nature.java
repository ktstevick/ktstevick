package stevick.types;

// The purpose of this chart is to retrieve Nature data and reassign it elsewhere, like the TypeChart
public class Nature {
    private String natureName = "";

    // I'm gonna type these out to help visualize, may not be permanent fixtures
    private double atkMultiplier = 1.0;
    private double defMultiplier = 1.0;
    private double spaMultiplier = 1.0;
    private double spdMultiplier = 1.0;
    private double speedMultiplier = 1.0;

    public String getNatureName() {
        return  natureName;
    }
    public double getAtkMultiplier() {
        return atkMultiplier;
    }
    public double getDefMultiplier() {
        return defMultiplier;
    }
    public double getSpaMultiplier() {
        return spaMultiplier;
    }
    public double getSpdMultiplier() {
        return spdMultiplier;
    }
    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    // Constructor. This has the basic info, skipping non-changing natures for simplicity's sake
    public Nature (String nature) {
        this.natureName = nature;

        // Boosts first
        if (nature.equals("Lonely") || nature.equals("Brave") || nature.equals("Adamant") || nature.equals("Naughty")) {
            this.atkMultiplier = 1.1;
        }
        if (nature.equals("Bold") || nature.equals("Relaxed") || nature.equals("Impish") || nature.equals("Lax")) {
            this.defMultiplier = 1.1;
        }
        if (nature.equals("Modest") || nature.equals("Mild") || nature.equals("Quiet") || nature.equals("Rash")) {
            this.spaMultiplier = 1.1;
        }
        if (nature.equals("Calm") || nature.equals("Gentle") || nature.equals("Sassy") || nature.equals("Careful")) {
            this.spdMultiplier = 1.1;
        }
        if (nature.equals("Timid") || nature.equals("Hasty") || nature.equals("Jolly") || nature.equals("Naive")) {
            this.speedMultiplier = 1.1;
        }

        // Reductions
        if (nature.equals("Bold") || nature.equals("Timid") || nature.equals("Modest") || nature.equals("Calm")) {
            this.atkMultiplier = 0.9;
        }
        if (nature.equals("Lonely") || nature.equals("Hasty") || nature.equals("Mild") || nature.equals("Gentle")) {
            this.defMultiplier = 0.9;
        }
        if (nature.equals("Adamant") || nature.equals("Impish") || nature.equals("Jolly") || nature.equals("Careful")) {
            this.spaMultiplier = 0.9;
        }
        if (nature.equals("Naughty") || nature.equals("Lax") || nature.equals("Naive") || nature.equals("Rash")) {
            this.spdMultiplier = 0.9;
        }
        if (nature.equals("Brave") || nature.equals("Relaxed") || nature.equals("Quiet") || nature.equals("Sassy")) {
            this.speedMultiplier = 0.9;
        }
    }

    // Four methods, one for each stat that matters, basically getters? And then the setters are really elaborate?


}
