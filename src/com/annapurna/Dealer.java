package com.annapurna;

import java.util.*;

public class Dealer extends Player{
    //STATIC FIELDS
    public static final String NAME="Polly";

    //INSTANT FIELDS
    private List<Card> deck= Deck.newDeck();

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

    public Boolean deal(List<Player> players){ // need the List of Players from game
        boolean result=false;
        int initialDeckSize= getDeck().size();
        System.out.println(initialDeckSize+"--------");
        for(var player: players){
            player.getHand().add(getDeck().remove(0));
        }
        if(getDeck().size()==(initialDeckSize-players.size())){
            result=true;
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
