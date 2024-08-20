package stevick.moves;

public class Move {
    private String name = "";
    private int basePower = 0;
    private String type = "";
    private String property = "";

    public String getName() {
        return name;
    }
    public int getBasePower() {
        return basePower;
    }
    public String getType() {
        return type;
    }
    public String getProperty() {
        return property;
    }

    // Constructor. This should do for now. Maybe an overload for multi-hits?
    public Move(String name, int basePower, String type, String property) {
        this.name = name;
        this.basePower = basePower;
        this.type = type;
        this.property = property;
    }
}
