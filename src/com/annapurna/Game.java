package com.annapurna;

import java.util.ArrayList;
import java.util.List;

public class Game {

    /* TODO
        1. get a list of players from client
        2. delegate dealer to firstDeal, and giveAdditional card according to hit() or stand() condition represented by play()
        3. refer to the rule class to check the conditions
        4. main client facing class
        5. keep track of the turn
    */

    //STATIC FIELDS
    public static final int MAX_PLAYERS=7;
    public static final int MIN_PLAYERS=1;

    //INSTANCE FIELDS
    private int playerCount;
    private List<Player> players= new ArrayList<>();
    public Dealer dealer= new Dealer();


    //CONSTRUCTORS
    public Game(List<Player> players){
        players.add(dealer);
        setPlayers(players);

    }
    public Game(){}
    public Game(int playerCount){
        setPlayerCount(playerCount);
    }

    //BUSINESS METHODS
    public void playerTurn(){
        for(var player:players){
            if(player.hitOrStand()){
                dealer.hit(player);
                //TODO check total for win/lose in Rules
            }
        }
    }


    //ACCESSOR METHODS

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        if(playerCount<MIN_PLAYERS||playerCount>MAX_PLAYERS){
            System.out.println("Valid number of players:["+ MIN_PLAYERS+", "+MAX_PLAYERS+"]. Please enter a valid number.");
            throw new IllegalArgumentException("Valid number of players:["+ MIN_PLAYERS+", "+MAX_PLAYERS+"]. Please enter a valid number.");
        }
        else{
            this.playerCount = playerCount+1;
        }
    }



    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
