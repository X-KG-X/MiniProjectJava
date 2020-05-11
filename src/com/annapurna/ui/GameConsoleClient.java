package com.annapurna.ui;

import com.annapurna.*;

import java.io.Console;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class GameConsoleClient {
    static Console console=System.console();
    static Game game= new Game();
    static Game game1;
    static Rules playerRules=new PlayerRules();
    static Rules dealerRules=new DealerRules();
    static List<Player> players= new ArrayList<>();

    public static void main(String[] args) {
        //Create Console
        if (console == null) {
            System.out.print("No console available");
            return;
        }

        //Welcome Message
        System.out.println();
        System.out.println("WELCOME TO ANNAPURNA CASINO. BLACKJACK ADAPTATION PRESENTED BY TEAM JAVA2K!");
        System.out.println();

        //Create Game for playerCount Intake
        int playerCount;
        while (true) {
            playerCount = Integer.parseInt(console.readLine("Please enter the number of players [" + Game.MIN_PLAYERS + "," + Game.MAX_PLAYERS + "] :"));
            try {
                game.setPlayerCount(playerCount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            //TODO maybe add a catch to handle non-numeric entry
        }

        //Create player list
        for (int i = 0; i < playerCount; i++) {
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

        //Create the actual game with the list of players created
        game1 = new Game(players);

        //Call dealer to shuffle the deck
        game1.dealer.shuffleDeck();

        //Call the dealer to deal the cards
        game1.dealer.deal(game1.getPlayers());

        //Display all players' hands
        game1.getPlayers().forEach(player -> System.out.println(player.getName() + " has " + player.getHand().toString()+"\n"));
        System.out.println();
        System.out.println();


        //After the deal the dealer asks for each players action(HIT or STAND)
        for (Player player : game1.getPlayers()) {
            String status = null;
            if (!player.isDealer()) { //checking to make sure the player is not the dealer
                status = playerRules.checkStatus(player);
                if (status.equals("WIN")) {
                    doWin(player);
                } else if (status.equals("LOSE")) {
                    doLose(player);
                } else {
                    while (playerRules.checkStatus(player).equals("LIVE")) {
                        player.setPlay(Player.Play.valueOf(console.readLine(player.getName() + ", " + "Enter [HIT, STAND]:")));
                        System.out.println();
                        boolean decision = game1.dealer.hit(player);
                        System.out.println(player.getName()+"   "+ player.getHand());
                        System.out.println();
                        if (!decision) {
                            System.out.println(player.getName() + " has decided to STAND.");
                            System.out.println(player.getName() + ", your current hand is: " + player.getHand());
                            System.out.println();
                            break;
                        }
                        if(playerRules.checkStatus(player).equals("WIN")){
                            doWin(player);
                        }
                        if(playerRules.checkStatus(player).equals("LOSE")){
                            doLose(player);
                        }
                    }
                }

            }
            else {//TODO dealer automated actions
                status=dealerRules.checkStatus(player);
                if(status.equals("WIN")){
                    dealerWin(player);
                }
                else if(status.equals("LOSE")){
                    dealerLose(player);
                }
                else{
                    while(dealerRules.checkStatus(player).equals("LIVE")){
                        boolean decision=game1.dealer.hit(player);
                        if(!decision){
                            //TODO compare all the remaining hands
                            System.out.println("Up next compare all the remaining hands!");
                            compareLiveHands();
                        }
                        if(playerRules.checkStatus(player).equals("WIN")){
                            dealerWin(player);
                        }
                        if(playerRules.checkStatus(player).equals("LOSE")){
                            dealerLose(player);
                        }
                    }
                }
//                System.out.println(dealerRules.checkStatus(player));

            }
        }
    }
        //TODO is any other player is still standing then compare dealer car to them and give them a win or lose

    public static void compareLiveHands(){
        players.forEach(player -> System.out.println(player.getName()+" has "+ player.getHand()));
        System.out.println();
        List<Integer> handTotals=new ArrayList<>();

        for(Player player:players){
            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
            if(!player.isDealer() && player.getHand().contains(Card.Rank.ACE)){
                System.out.println(playerRules.checkTotal(player).size());
                handTotals.add(Math.max(playerRules.checkTotal(player).get(0),playerRules.checkTotal(player).get(1)));
            }
            else{
                System.out.println("0000000000000000000000000");
                handTotals.add(playerRules.checkTotal(player).get(0));
            }
        }
        System.out.println("------------------------XXXXXXXXXXXX");

        //If dealer has the highest hand
        if(helperMax(handTotals)==handTotals.get(handTotals.size()-1)){
            System.out.println("------------------------");

            dealerWin(players.get(players.size()-1));
        }

        else{ //else everyone who have higher hand than dealer win
            System.out.println("------------------------MMMMMMMMMM");

            for(Player player:players){
                System.out.println("------------------------111111111111");

                if (playerRules.checkTotal(player).get(0)>playerRules.checkTotal(players.get(players.size()-1)).get(0)||playerRules.checkTotal(player).get(1)>playerRules.checkTotal(players.get(players.size()-1)).get(0)){
                    doWin(player);
                }
                else if(playerRules.checkTotal(player).get(0)==playerRules.checkTotal(players.get(players.size()-1)).get(0)||playerRules.checkTotal(player).get(1)==playerRules.checkTotal(players.get(players.size()-1)).get(0)){
                    doWin(player);
                }
                else{
                    doLose(player);
                }
            }

        }

        System.exit(0);

    }

    public static int helperMax(List<Integer> handTotals){
        int result=0;
        for(var total:handTotals){
            if(total>result){
                result=total;
            }
        }
        return result;
    }


    public static void doWin(Player player){
        System.out.println(player.getHand());
        System.out.println(player.getName()+ ", you got BlackJack. You win!");
        game1.getPlayers().remove(player);
        if(game1.getPlayers().size()==1){
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
    }

    public static void doLose(Player player) {
        System.out.println(player.getHand());
        System.out.println(player.getName() + ", sorry you lose.");
        game1.getPlayers().remove(player);
        if (game1.getPlayers().size() == 1) {
            System.out.println("DEALER WINS! GOOD BYE!");
            System.exit(0);
        }
    }

    public static void dealerWin(Player player){
        System.out.println(player.getHand());
        System.out.println("Dealer "+ Dealer.NAME+ " wins. Better luck next time all!");
        System.out.println("GAME OVER! GOOD BYE!");
        System.exit(0);
    }

    public static void dealerLose(Player player){
        System.out.println(player.getHand());
        System.out.println("Dealer "+ Dealer.NAME+ " loses, all LIVE players win!");
        System.out.println("GAME OVER! GOOD BYE!");
        System.exit(0);
    }


}

