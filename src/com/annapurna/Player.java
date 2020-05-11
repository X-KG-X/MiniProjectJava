package com.annapurna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
        if(!name.isBlank()){
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("Blank not excepted. Please enter valid name:");
        }
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

    public Boolean isDealer(){
        boolean result=false;
        if(getClass()==Dealer.class){
            result=true;
        }
        return result;
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

    //OVERRIDES


    @Override
    public int hashCode() {
        return Objects.hashCode(getHand());
    }


    @Override
    public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Player that = (Player) obj;
            return Objects.equals(getHand(), that.getHand());
    }


}
