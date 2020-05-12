package com.annapurna;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    public static Console console=System.console();
//    private Player player;
    private List<Player> players;
    private Dealer dealer;
    private Game game;

    GameHelper(List<Player> players, Dealer dealer, Game game){
        this.players=players;
        this.dealer=dealer;
        this.game=game;
    }

    public void playTurn(Player player){
        while (player.checkStatus().equals("LIVE")) {
            while(true){
                System.out.println(player);
                try{
                    player.setPlay(console.readLine(player.getName() + ", " + "Enter H/h for HIT, S/s for STAND:"));
                    break;
                }
                catch  (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(); // Spacing only
            boolean decision = dealer.hit(player);
//            System.out.println(player);
            System.out.println(); // Spacing only
            if (!decision) {
                System.out.println(player.getName() + " has decided to STAND.");
                System.out.println(player);
                System.out.println(); // Spacing only
                break;
            }
            if(player.checkStatus().equals("WIN")){
                doWin(player);
            }
            if(player.checkStatus().equals("LOSE")){
                doLose(player);
            }
        }

    }

    public List<Integer> getStandingHandSums(List<Player> players){
        System.out.println(); // Spacing only
        List<Integer> result=new ArrayList<>();
        for(Player player:players){
            if(!player.isDealer() && player.getHand().contains(Card.Rank.ACE)){
                result.add(Math.max(player.checkTotal().get(0),player.checkTotal().get(1)));
            }
            else{
                result.add(player.checkTotal().get(0));
            }
        }
        return result;
    }

    public void compareLiveHands(){
        System.out.println(players);
        List<Integer> handTotals=getStandingHandSums(players);
        //If dealer has the highest hand
        if(helperMax(handTotals)==handTotals.get(handTotals.size()-1)){
            doWin(dealer);
        }
        else{ //else everyone who have higher hand than dealer wins
            for(Player player:players){
                if (player.checkTotal().get(0)>dealer.checkTotal().get(0)||player.checkTotal().get(1)>dealer.checkTotal().get(0)){
                    doWin(player);
                }
                else if(player.checkTotal().get(0)==dealer.checkTotal().get(0)||player.checkTotal().get(1)==dealer.checkTotal().get(0)){
                    doWin(player);
                }
                else{
                    doLose(player);
                }
            }

        }
//        System.exit(0);
    }

    public int helperMax(List<Integer> handTotals){
        int result=0;
        for(var total:handTotals){
            if(total>result){
                result=total;
            }
        }
        return result;
    }

    public void doWin(Player player)  {
        if(player.isDealer()){
            System.out.println("             ********************************************");
            players.forEach(System.out::println);
            System.out.println("             ********************************************");
            System.out.println("Dealer "+ Dealer.NAME+ " wins. Better luck next time all!");
            System.out.println(); // Spacing only
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
        else{
            System.out.println(player.getName()+ ", wins!");
            System.out.println(player);
            game.getPlayers().remove(player);
//            getPlayers().removeIf(player1 -> player1.checkStatus().equals("WIN"));
        }
        if(game.getPlayers().size()==1){
            System.out.println("             ********************************************");
            players.forEach(System.out::println);
            System.out.println("             ********************************************");
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
    }

    public  void doLose(Player player){
        if(player.isDealer()){
            System.out.println("Standing Hand/s********************************************");
            players.forEach(System.out::println);
            System.out.println("               ********************************************");
            System.out.println("Dealer "+Dealer.NAME+" loses.");
            System.out.println(); // Spacing only
            System.out.println("Anyone still standing wins!");
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
        else{
            System.out.println(player.getName()+", loses.");
            System.out.println(player);
            game.getPlayers().remove(player);
//            getPlayers().removeIf(player1 -> player1.checkStatus().equals("LOSE"));
        }
        if(game.getPlayers().size()==1){
            System.out.println("Standing Hand/s********************************************");
            players.forEach(System.out::println);
            System.out.println("               ********************************************");
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
    }

}
