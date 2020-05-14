package com.annapurna;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    //STATIC FIELDS
    public static final int MAX_PLAYERS=4;
    public static final int MIN_PLAYERS=1;
    public static Console console=System.console();

    //INSTANCE FIELDS
    private int playerCount;
    private List<Player> players= new ArrayList<>();
    private Dealer dealer= new Dealer();

    //CONSTRUCTORS
    Game(List<Player> players){
        players.add(dealer);
        setPlayers(players);
    }
    Game(){}

    //BUSINESS METHODS

    public void startGame() throws InterruptedException {
        setUpGame(); //calling game set up
        GameHelper gameHelper = new GameHelper(players, dealer, this);
        Iterator<Player> it =players.iterator(); // Iterator used instead foreach loop to  avoid ConcurrentModificationException
        while(it.hasNext()){
            String status = null;
            Player player=it.next();
            if (!player.isDealer()) {
                status = player.checkStatus();
                if (status.equals("WIN")) {
                    gameHelper.winLose(player,"WIN");
                    it.remove();
                } else {// "LIVE"
                    gameHelper.runPlayerTurn(player,it);
                }
            }
            else { //Dealer's turn
                status=player.checkStatus();
                if(status.equals("WIN")){
                    gameHelper.winLose(player,"WIN");
                }
                else{ // Dealer "LIVE" -->needs to hit till his/her total is >=17
                    while(player.checkStatus().equals("LIVE")){
                        boolean decision=dealer.hit(player);
                        if(!decision){
                            if(players.size()>1){
                                System.out.println("\nUp next compare all the remaining hands!");
                                gameHelper.compareLiveHands();
                            }
                            System.out.println("\nGAME OVER! GOOD BYE! :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)");
                            System.exit(0);
                        }
                        if(player.checkStatus().equals("WIN")){
                            gameHelper.winLose(player,"WIN");
                        }
                        else if(player.checkStatus().equals("LOSE")){
                            gameHelper.winLose(player,"LOSE");
                        }
                    }
                }
            }
        }
    }

    public void setUpGame() throws InterruptedException {
        //Welcome Message
        System.out.println("****************************************************************************");
        System.out.println("WELCOME TO ANNAPURNA CASINO. BLACKJACK ADAPTATION PRESENTED BY TEAM JAVA2K!");
        System.out.println("             ********************************************");
        System.out.println("                             BASIC RULE\n-> The goal of blackjack is to beat the dealer's hand without going over 21.\n-> Face cards are worth 10.  For players Aces are worth 1 or 11, whichever makes a better hand.\n-> Each player starts with two cards, one of the dealer's cards is hidden until the end.\n-> To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n-> If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.\n-> If your hands totals 21, you got a blackjack.*\n-> Dealer will hit until his/her cards total 17 or higher.\n");
        System.out.println("             ********************************************");
        //Get list of players
        List<Player> players=getPlayerListFromConsole();
        //Construct gameActual with tha List of players including the dealer as one of the players
        Game game= GameFactory.createGame(players);
        //Game delegates the dealer to deal shuffle the card
        game.getDealer().shuffleDeck();
        game.getDealer().deal(game.getPlayers());

        System.out.println("Give our Dealer "+game.getDealer().getName()+" a sec to deal.");
        System.out.println();
        Thread.sleep(2000);

        //Print the hands dealt
        game.displayInitialHands();
    }

    public int getPlayerCountFromConsole(){
        int playerCount;
        while (true) {
            try {
                playerCount = Integer.parseInt(console.readLine("Please enter the number of players [" + Game.MIN_PLAYERS + "," + Game.MAX_PLAYERS + "] :"));
                setPlayerCount(playerCount);
                break;
            } catch (NumberFormatException e){
                System.out.println("Please input a number!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
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
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            players.add(player);
        }
        return players;
    }

    public void displayInitialHands(){
        for(Player player:getPlayers()){
            if(player.isDealer()){
                System.out.println(Dealer.NAME+"'s hand=["+getDealer().getHand().get(0)+",{XXXXXXXXXXX}]\n");
            }
            else {
                System.out.println(player);
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
    public void setPlayerCount(int playerCount) throws IllegalArgumentException {
        if(playerCount<MIN_PLAYERS||playerCount>MAX_PLAYERS){
            throw new IllegalArgumentException("Invalid entry: "+playerCount+"."+" Valid number of players =["+ MIN_PLAYERS+", "+MAX_PLAYERS+"].");
        }
        else{
            this.playerCount = playerCount+1;
        }
    }
    public Dealer getDealer() {
        return this.dealer;
    }
}
