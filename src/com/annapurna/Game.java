package com.annapurna;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Game {


    //STATIC FIELDS
    public static final int MAX_PLAYERS=4;
    public static final int MIN_PLAYERS=1;
    public static Console console=System.console();


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


    //BUSINESS METHODS
    public int getPlayerCountFromConsole(){
        int playerCount;
        while (true) {
            playerCount = Integer.parseInt(console.readLine("Please enter the number of players [" + Game.MIN_PLAYERS + "," + Game.MAX_PLAYERS + "] :"));
            try {
                setPlayerCount(playerCount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            //TODO maybe add a catch to handle non-numeric entry
        }
        return playerCount;
    }

    public List<Player> getPlayerListFromConsole(){
        int playerCnt=getPlayerCountFromConsole();
        for (int i = 0; i < playerCnt; i++) {
            Player player;
            while (true) {
                try {
                    player = new Player(console.readLine("Enter player Name:"));
                    System.out.println();
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            players.add(player);
        }
        return players;
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

    public void setPlayerCount(int playerCount) throws IllegalArgumentException {
        if(playerCount<MIN_PLAYERS||playerCount>MAX_PLAYERS){
            throw new IllegalArgumentException("Invalid entry: "+playerCount+"."+" Valid number of players =["+ MIN_PLAYERS+", "+MAX_PLAYERS+"].");
        }
        else{
            this.playerCount = playerCount+1;
        }
    }


    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
