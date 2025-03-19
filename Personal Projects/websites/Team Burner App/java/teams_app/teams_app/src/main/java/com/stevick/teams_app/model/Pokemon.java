package com.stevick.teams_app.model;

public class Pokemon {
    private int id;
    private int teamId;
    private String name;
    private String nickName;
    private String item;
    private String ability;
    private String teraType;
    private String EVs; // Stat spreads are Strings to coordinate w/ Showdown
    private String IVs;
    private String nature;
    private String move1; // Moves are individual since they may vary in number
    private String move2;
    private String move3;
    private String move4;
    private boolean isShiny;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getAbility() {
        return ability;
    }
    public void setAbility(String ability) {
        this.ability = ability;
    }
    public String getTeraType() {
        return teraType;
    }
    public void setTeraType(String teraType) {
        this.teraType = teraType;
    }
    public String getEVs() {
        return EVs;
    }
    public void setEVs(String EVs) {
        this.EVs = EVs;
    }
    public String getIVs() {
        return IVs;
    }
    public void setIVs(String IVs) {
        this.IVs = IVs;
    }
    public String getNature() {
        return nature;
    }
    public void setNature(String nature) {
        this.nature = nature;
    }
    public String getMove1() {
        return move1;
    }
    public void setMove1(String move1) {
        this.move1 = move1;
    }
    public String getMove2() {
        return move2;
    }
    public void setMove2(String move2) {
        this.move2 = move2;
    }
    public String getMove3() {
        return move3;
    }
    public void setMove3(String move3) {
        this.move3 = move3;
    }
    public String getMove4() {
        return move4;
    }
    public void setMove4(String move4) {
        this.move4 = move4;
    }
    public boolean isShiny() {
        return isShiny;
    }
    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", item='" + item + '\'' +
                ", ability='" + ability + '\'' +
                ", teraType='" + teraType + '\'' +
                ", EVs='" + EVs + '\'' +
                ", IVs='" + IVs + '\'' +
                ", nature='" + nature + '\'' +
                ", move1='" + move1 + '\'' +
                ", move2='" + move2 + '\'' +
                ", move3='" + move3 + '\'' +
                ", move4='" + move4 + '\'' +
                ", isShiny=" + isShiny +
                '}';
    }
}
