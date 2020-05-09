package com.annapurna;

import java.util.ArrayList;
import java.util.List;

public class Player {

    //INSTANT FIELDS
    private String name;
    private List<Card> hand=new ArrayList<>();
    private Play play=Play.HIT;

    //CONSTRUCTORS
    public Player(){}
    public Player(String name){
        setName(name);
    }

    //BUSINESS METHODS TODO  hit(), stand()
    public Boolean hitOrStand(){  //true for HIT and false for stand
        return play.value();
    }



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

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    //OBJECT OVERRIDE

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                ", hand=" + getHand() +
                '}';
    }


    //STATIC NESTED CLASS
    public static enum Play{
        HIT(true),
        STAND(false);

        private boolean value;
        Play(boolean value){
            this.value=value;
        }

        public boolean value(){ return value;}

    }
}
