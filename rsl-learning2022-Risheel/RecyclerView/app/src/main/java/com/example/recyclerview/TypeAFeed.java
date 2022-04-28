package com.example.recyclerview;

public class TypeAFeed implements TypeFeed{
    private String name;
    private int followers;
    private int contributions;

    public TypeAFeed(String name, int followers, int contributions) {
        this.name = name;
        this.followers = followers;
        this.contributions = contributions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }
}
