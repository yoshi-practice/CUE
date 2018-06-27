package com.developer.yoshi1125hisa.healthcare;

import java.io.Serializable;

public class Post implements Serializable{

    private String walkCount;
    private String sleepTime;

    public Post(String walkCount,String sleepTime) {
        this.walkCount = walkCount;
        this.sleepTime = sleepTime;

    }

    public String getWalkCount() {
        return walkCount;
    }

    public void setWalkCount(String walkCount){
        this.walkCount = walkCount;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime){
        this.sleepTime = sleepTime;
    }


}


