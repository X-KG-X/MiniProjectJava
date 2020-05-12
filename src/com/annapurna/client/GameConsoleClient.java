package com.annapurna.client;

import com.annapurna.Game;
import com.annapurna.GameFactory;
import com.annapurna.Player;

import java.util.List;

public class GameConsoleClient {
    public static void main(String[] args) throws InterruptedException {
        Game game=GameFactory.createGame();
        if (game.console == null) {
            System.out.print("No console available");
            return;
        }

        //Welcome Message
        System.out.println("****************************************************************************");
        System.out.println("WELCOME TO ANNAPURNA CASINO. BLACKJACK ADAPTATION PRESENTED BY TEAM JAVA2K!");
        System.out.println("             ********************************************");

        //Get list of players
        List<Player> players=game.getPlayerListFromConsole();
        //Construct gameActual with tha List of players including the dealer as one of the players
        Game gameActual= GameFactory.createGame(players);
        //Game delegates the dealer to deal shuffle the card
        gameActual.getDealer().shuffleDeck();

        gameActual.getDealer().deal(gameActual.getPlayers());

        System.out.println("Give our Dealer "+gameActual.getDealer().getName()+" a sec to deal.");
        System.out.println();
        Thread.sleep(2000);

        //Print the hands dealt TODO time permitting hide dealers second card
        gameActual.getPlayers().forEach(player -> System.out.println((player)+"\n"));

        //Begin play
        gameActual.startGame();

    }
}
