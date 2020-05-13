package com.annapurna;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameHelper {
    public static Console console=System.console();
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
            System.out.println(); // Spacing only
            if (!decision) {
                System.out.println(player.getName() + " has decided to STAND.");
                System.out.println(player);
                System.out.println(); // Spacing only
                break;
            }
            if(player.checkStatus().equals("WIN")){
                winLose(player,"WIN");
            }
            if(player.checkStatus().equals("LOSE")){
                winLose(player,"LOSE");
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

    public void compareLiveHands() throws InterruptedException {
        players.forEach(System.out::println);

        Thread.sleep(6000);
        List<Integer> handTotals=getStandingHandSums(players);
        //If dealer has the highest hand
        Integer maxHand=Collections.max(handTotals);
        Integer dealerTotal=handTotals.get(handTotals.size()-1);
        if(maxHand.equals(dealerTotal) && Collections.frequency(handTotals,maxHand)==1){
            winLose(dealer,"WIN");
        }
        else{ //else everyone who have higher hand than dealer wins
            for(int i=0;i<handTotals.size()-1;i++){
                if(handTotals.get(i)>=dealerTotal){
                    winLose(players.get(i),"WIN");
                }
                else{
                    winLose(players.get(i),"LOSE");
                }
            }
        }
    }


    public void winLose(Player player, String string)  {
        if(player.isDealer()){
            System.out.println("\n             ********************************************");
            players.forEach(System.out::println);
            System.out.println("\n             ********************************************!");
            System.out.println("\nDealer "+ Dealer.NAME+ " "+ string+"S");
            if(string.equals("LOSE")){
                System.out.println("\n"+players.get(0).getName()+" WINS");
                System.out.println(players.get(0));
            }
            System.out.println(); // Spacing only
            System.out.println("\nGAME OVER! GOOD BYE! :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)");
            System.exit(0);
        }
        else{
            System.out.println("\n"+player.getName()+" "+ string+"S");
            System.out.println(player);
            players.remove(player);
        }
        if(players.size()==1){
//            System.out.println("\n             ********************************************");
//            players.forEach(System.out::println);
//            System.out.println("\n             ********************************************");
            System.out.println("\nGAME OVER! GOOD BYE! :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)");
            System.exit(0);
        }
    }
}
