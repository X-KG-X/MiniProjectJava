package com.annapurna;

import java.util.ArrayList;
import java.util.List;

public class Player {

    //INSTANT FIELDS
    private String name;
    private List<Card> hand=new ArrayList<>();

    //CONSTRUCTORS
    public Player(){}
    public Player(String name){
        setName(name);
    }

    //BUSINESS METHODS TODO  hit(), stand()



    //ACCESSOR METHODS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }


    //OBJECT OVERRIDE

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                ", hand=" + getHand() +
                '}';
    }
}
