package com.stevick.teams_app.model;

public class Note {
    private int id;
    private int teamId;
    private String text;

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
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", teamId='" + teamId +
                ", text='" + text +
                '}';
    }
}
