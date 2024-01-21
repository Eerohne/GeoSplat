package com.example.myapplication.structures.runs;

public class SimpleRun extends Run{
    public SimpleRun(String name, Difficulty difficulty, int duration, String desc){
        super.name = name;
        super.difficulty = difficulty;
        super.duration = duration;
        super.desc = desc;
    }

    public SimpleRun(String name, Difficulty difficulty, int duration){
        this(name, difficulty, duration, "No Description Provided :(");
    }

    public SimpleRun(String name, String description, int picture){
        this(name, Difficulty.EASY, 0, description);
        super.setPicturePath(picture);
    }
}
