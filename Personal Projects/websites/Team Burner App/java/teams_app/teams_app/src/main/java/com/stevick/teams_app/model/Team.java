package com.stevick.teams_app.model;

public class Team {
    private int id;
    private String name;
    private String roster; // Just a String for now

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRoster() {
        return roster;
    }
    public void setRoster(String roster) {
        this.roster = roster;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                "roster=" + roster +
                "}";
    }
}
