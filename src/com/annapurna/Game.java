package com.annapurna;

import java.util.List;

public class Game {

    /* TODO
        1. get a list of players from client
        2. delegate dealer to firstDeal, and giveAdditional card according to hit() or stand() condition
        3. refer to the rule class to check the conditions
        4. main client facing class

    */

    //INSTANCE FIELDS
    private List<Player> players;

    //ACCESSOR METHODS


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
