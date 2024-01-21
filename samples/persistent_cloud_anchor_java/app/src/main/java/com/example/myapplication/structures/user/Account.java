package com.example.myapplication.structures.user;

import java.util.List;

/**
 * Basic structure of an account
 */
public class Account {
    private String username;
    private int coins;
    private int energy;
    private int exp;
    private List<Achievement> achievements;

    public Account(String username, int coins, int energy, int exp, List<Achievement> achievementList){
        this.username = username;
        this.coins = coins;
        this.energy = energy;
        this.exp = exp;
        this.achievements = achievementList;
    }
}
