package com.annapurna;

import java.util.*;

public class Dealer {
    //STATIC FIELDS //TODO getNumberOfPlayers
    public static final String NAME="Polly";
    public static final int MAX_PLAYERS=7;
    public static final int MIN_PLAYERS=1;

    //INSTANT FIELDS
    private List<Card> deck= Deck.newDeck();
    private int playerCount=1;
    private List<Card> hand;

    //BUSINESS METHODS TODO Respond to HIT, STAND and also DEAL
    public Boolean shuffleDeck(){
        boolean result=false;
        List<Card> checkDeck=List.copyOf(this.getDeck());
        Collections.shuffle(this.getDeck());
        if(!checkDeck.equals(this.deck)){
            result=true;
        }
        return result;
    }

    




    //ACCESSOR METHODS
    public List<Card> getDeck() {
        return deck;
    }

    public int getPlayerCount() {  //TODO get this info from Game class?
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {  //TODO set it in Game class?
        if(playerCount<MIN_PLAYERS||playerCount>MAX_PLAYERS){
            System.out.println("Valid number of players:["+ MIN_PLAYERS+", "+MAX_PLAYERS+"]. Please enter a valid number.");
            throw new IllegalArgumentException("Valid number of players:["+ MIN_PLAYERS+", "+MAX_PLAYERS+"]. Please enter a valid number.");
        }
        else{
            this.playerCount = playerCount;
        }
    }


}
