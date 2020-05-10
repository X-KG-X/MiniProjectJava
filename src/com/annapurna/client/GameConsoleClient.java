package com.annapurna.client;

import com.annapurna.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class GameConsoleClient {
    static Console console= System.console();


    public static void main(String[] args) {
        Game game=new Game();
        List<Player> players= new ArrayList<>();
        int playerCount;

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

        game1.dealer.shuffleDeck();
        game1.dealer.deal(game1.getPlayers());
        System.out.println(game1.getPlayers());

        Rules dealerRules= new DealerRules();
        Rules playerRules= new PlayerRules();

        for(Player player:game1.getPlayers()){
            if(!player.isDealer()){
                String status=playerRules.checkStatus(player);
                if(status.equals("WIN")){
                    System.out.println(player.getName()+ ", you got BlackJack. You win!");
                    game1.getPlayers().remove(player);
                    if(game1.getPlayers().size()==1){
                        System.out.println("GAME OVER! GOOD BYE!");
                    }
                }
                else if(status.equals("LOSE")){
                    System.out.println(player.getName()+", sorry you lose.");
                    game1.getPlayers().remove(player);
                    if(game1.getPlayers().size()==1){
                        System.out.println("DEALER WINS! GOOD BYE!");
                    }
                }
                else{
                    while(true){
                        player.setPlay(Player.Play.valueOf(console.readLine(player.getName()+", "+"Enter [HIT, STAND] :")));
                        boolean decision=game1.dealer.hit(player);
                        if(!decision){
                            System.out.println(player.getName()+" has decided to STAND.");
                            break;
                        }
                        System.out.println(player.getName()+"'s hand"+player.getHand().toString()+" " + playerRules.checkStatus(player));
                    }
                }

            }
            else{
                System.out.println(dealerRules.checkStatus(player));
            }
        }




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

//    while (true) {
//        playerCount = Integer.parseInt(console.readLine("Please enter the number of players [" + Game.MIN_PLAYERS + "," + Game.MAX_PLAYERS + "] :"));
//        try {
//            game.setPlayerCount(playerCount);
//            break;
//        }
//        catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//        //TODO maybe add a catch to handle non-numeric entry
//    }
}
