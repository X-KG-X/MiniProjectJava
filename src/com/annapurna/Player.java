package com.annapurna;

import java.util.ArrayList;
import java.util.List;

class Player implements Rules {
    //INSTANT FIELDS
    private String name;
    private List<Card> hand=new ArrayList<>();
    private Play play=Play.HIT;

    //CONSTRUCTORS
    public Player(){}
    public Player(String name){
        setName(name);
    }

    //BUSINESS METHODS
    public Boolean hitOrStand(){  //true for HIT and false for stand
        return play.value();
    }

    public List<Integer> checkTotal() {
        List<Integer> result=new ArrayList<>();
        Integer sum=0;
        Integer alternateSum=0;
        for(var card: getHand()){
            if (card.getRank()== Card.Rank.ACE){
                alternateSum+=1;
            }
            else {
                alternateSum+=card.getRank().value();
            }
            sum+=card.getRank().value();
        }
        result.add(sum);
        result.add(alternateSum);
        return result;
    }

    public String checkStatus() {
        String result="LIVE";
        List<Integer> sumList=checkTotal();
        if(sumList.get(0).equals(21)||sumList.get(1).equals(21)){
            result="WIN";
            System.out.println("***********BLACK JACK*************");
        }
        else if(sumList.get(0)>21&&sumList.get(1)>21){
            result="LOSE";
        }
        return result;
    }

    //ACCESSOR METHODS
    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
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
    public void setHand(List<Card> hand) {//TODO remove later
        this.hand = hand;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
    public void setPlay(String play) throws IllegalArgumentException{
        if(play.toUpperCase().equals("H")){
            this.play=Play.HIT;
        }
        else if(play.toUpperCase().equals("S")){
            this.play=Play.STAND;
        }
        else{
            throw new IllegalArgumentException("Invalid entry. Valid entry H/h-->HIT, S/s-->Stand");
        }
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
        return getName() +"\'s"+" hand=" + getHand();
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