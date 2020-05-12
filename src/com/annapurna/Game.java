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
    Game(List<Player> players){
        players.add(dealer);
        setPlayers(players);

    }
    Game(){}


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

    public void startGame(){
        for (Player player : getPlayers()) {
            String status = null;
            if (!player.isDealer()) { //checking to make sure the player is not the dealer
                status = player.checkStatus();
                if (status.equals("WIN")) {
                    doWin(player);
                } else if (status.equals("LOSE")) {
                    doLose(player);
                } else {
                    while (player.checkStatus().equals("LIVE")) {
                        player.setPlay(Player.Play.valueOf(console.readLine(player.getName() + ", " + "Enter [HIT, STAND]:")));
                        System.out.println();
                        boolean decision = dealer.hit(player);
                        System.out.println(player);
                        System.out.println();
                        if (!decision) {
                            System.out.println(player.getName() + " has decided to STAND.");
                            System.out.println(player);
                            System.out.println();
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

            }
            else {
                status=player.checkStatus();
                if(status.equals("WIN")){
                    doWin(player);
                }
                else if(status.equals("LOSE")){
                    doLose(player);
                }
                else{
                    while(player.checkStatus().equals("LIVE")){
                        boolean decision=dealer.hit(player);
                        if(!decision){
                            System.out.println("Up next compare all the remaining hands!");
                            compareLiveHands();
                        }
                        if(player.checkStatus().equals("WIN")){
                            doWin(player);
                        }
                        if(player.checkStatus().equals("LOSE")){
                            doLose(player);
                        }
                    }
                }
            }
        }
    }

    public  void compareLiveHands(){
        players.forEach(System.out::println);
        System.out.println();
        List<Integer> handTotals=new ArrayList<>();
        for(Player player:players){
            if(!player.isDealer() && player.getHand().contains(Card.Rank.ACE)){
                System.out.println(player.checkTotal().size());
                handTotals.add(Math.max(player.checkTotal().get(0),player.checkTotal().get(1)));
            }
            else{
                handTotals.add(player.checkTotal().get(0));
            }
        }
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

        System.exit(0);

    }


    public  void doWin(Player player){
        if(player.isDealer()){
            players.forEach(System.out::println);
            System.out.println("Dealer "+ Dealer.NAME+ " wins. Better luck next time all!");
            System.out.println();
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
        else{
            System.out.println(player.getName()+ ", wins!");
            System.out.println(player);
            getPlayers().remove(player);
        }
        if(getPlayers().size()==1){
            System.out.println();
            players.forEach(System.out::println);
            System.out.println();
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
    }

    public  void doLose(Player player){
        if(player.isDealer()){
            players.forEach(System.out::println);
            System.out.println("Dealer "+Dealer.NAME+" loses.");
            System.out.println();
            System.out.println("Anyone still standing wins!");
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
        else{
            System.out.println(player.getName()+", loses.");
            System.out.println(player);
            getPlayers().remove(player);
        }
        if(getPlayers().size()==1){
            System.out.println();
            players.forEach(System.out::println);
            System.out.println();
            System.out.println("GAME OVER! GOOD BYE!");
            System.exit(0);
        }
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
