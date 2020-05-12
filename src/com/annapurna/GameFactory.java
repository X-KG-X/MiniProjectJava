package com.annapurna;

import java.util.List;

public class GameFactory {
    private GameFactory(){}
    public static Game createGame(List<Player> players){
        Game game=null;
        if(!players.isEmpty()){
            game= new Game(players);
            return game;
        }
        else {
            throw new NullPointerException("Need at least one player");
        }
    }
    public static Game createGame(){
            return new Game();
    }

}
