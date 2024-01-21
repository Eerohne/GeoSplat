package com.example.myapplication.structures.runs;

public abstract class Run {
    public enum Difficulty{EASY, MEDIUM, HARD, IMPOSSIBLE}

    protected String name;
    protected String desc;
    protected Difficulty difficulty;
    protected int duration; // duration in minutes
    protected int picture; // path to picture

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPicturePath() {
        return picture;
    }

    public void setPicturePath(int picturePath) {
        this.picture = picturePath;
    }
}
