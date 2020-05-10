package com.annapurna.client;

import com.annapurna.Dealer;
import com.annapurna.Game;
import com.annapurna.Player;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class GameConsoleClient {
    public static void main(String[] args) {
        Game game=new Game();
        List<Player> players= new ArrayList<>();
        int playerCount;

        Console console= System.console();
        if(console == null) {
            System.out.print("No console available");
            return;
        }

        System.out.println("WELCOME TO ANNAPURNA CASINO. BLACKJACK ADAPTATION PRESENTED BY TEAM JAVA2K!");
        System.out.println();

        while (true) {
            playerCount = Integer.parseInt(console.readLine("Please enter the number of players [" + Game.MIN_PLAYERS + "," + Game.MAX_PLAYERS + "] :"));
            try {
                game.setPlayerCount(playerCount);
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            //TODO maybe add a catch to handle non-numeric entry
        }


        for(int i=0; i<playerCount;i++) {
            Player player;
            while (true) {
                try {
                    player = new Player(console.readLine("Enter player Name:"));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            players.add(player);
        }

        Game game1=new Game(players);

        System.out.println(game1.getPlayers());



//        List<Player> players= new ArrayList<>();
//        players.add(new Player("KG"));
//        players.add(new Player("GG"));
//        Game game1= new Game(players);
//
//        game1.dealer.shuffleDeck();
//
//        System.out.println(game1.dealer.getDeck().get(0).toString());
//
//        game1.dealer.deal(players);
//
//        for(var player: game1.getPlayers()){
//
//        }

//
//        System.out.println(players.get(0).getHand().toString());
//        System.out.println(game1.dealer.getDeck().size());


    }
}
