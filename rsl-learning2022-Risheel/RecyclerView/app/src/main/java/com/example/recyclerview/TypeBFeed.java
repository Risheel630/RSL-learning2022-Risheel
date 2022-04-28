package com.example.recyclerview;

public class TypeBFeed implements TypeFeed{
    private String name;
    private int followers;
    private int contributions;
    private String location;

    public TypeBFeed(String name, int followers, int contribute, String location) {
        this.name = name;
        this.followers = followers;
        this.contributions = contribute;
        this.location = location;
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

    public void setContributions(int contribute) {
        this.contributions = contribute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
