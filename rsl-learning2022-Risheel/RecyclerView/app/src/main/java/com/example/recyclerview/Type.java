package com.example.recyclerview;

public class Type {
    private int feedType;
    private TypeFeed typeFeed;

    public Type(int feedType, TypeFeed object) {
        this.feedType = feedType;
        this.typeFeed = object;
    }
    public int getFeedType() {
        return feedType;
    }

    public TypeFeed getObject() {
        return typeFeed;
    }
}
