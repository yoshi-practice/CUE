package com.developer.yoshi1125hisa.healthcare;


import java.io.Serializable;

public class Post implements Serializable {

    private String walking;
    private String sleeping;
    private String gender;

    public Post(){

    }

    public Post(String walking,String gender) {
        this.walking = walking;
        this.gender = gender;

    }


    public String getWalking() { return  walking; }
    public void setWalking(String walking){ this.walking = walking; }

    public String getSleeping() { return  sleeping; }
    public void setSleeping(String sleeping){ this.sleeping = sleeping; }

    public String getGender() { return  gender; }
    public void setGender(String gender){ this.gender = gender; }

}
