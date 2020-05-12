package com.annapurna;

import java.util.*;

public class Dealer extends Player{
    //STATIC FIELDS
    public static final String NAME="Polly";

    //INSTANT FIELDS
    private List<Card> deck= Deck.newDeck();

    //BUSINESS METHODS
    public Boolean hit(Player player){
        if(player.hitOrStand()) {
            player.getHand().add(getDeck().remove(0));
            return true;
        }
        return false;
    }

    public Boolean shuffleDeck(){
        boolean result=false;
        List<Card> checkDeck=List.copyOf(this.getDeck());
        Collections.shuffle(this.getDeck());
        if(!checkDeck.equals(this.deck)){
            result=true;
        }
        return result;
    }

    public Boolean deal(List<Player> players){
        boolean result=false;
        int initialDeckSize= getDeck().size();
        for(int i=0; i<2;i++) {
            for (var player : players) {
                player.getHand().add(getDeck().remove(0));
            }
        }
        if(getDeck().size()==(initialDeckSize-players.size()*2)){
            result=true;
        }
        return result;
    }

    @Override
    public String checkStatus() {
        String result="LIVE";
        List<Integer> sumList= checkTotal();
        if (sumList.get(0)>=17){
            setPlay(Player.Play.STAND);
        }
        if(sumList.get(0)==21){
            result="WIN";
        }
        else if(sumList.get(0)>=21){
            result="LOSE";
        }
        return result;
    }

    //ACCESSOR METHODS
    public List<Card> getDeck() {
        return deck;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
