package com.annapurna.ui;

import com.annapurna.Game;
import com.annapurna.Player;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class GameMainTest {
    public static void main(String[] args) throws InterruptedException {
        Game game=new Game();
        if (game.console == null) {
            System.out.print("No console available");
            return;
        }

        //Welcome Message
        System.out.println("********************************************");
        System.out.println("WELCOME TO ANNAPURNA CASINO. BLACKJACK ADAPTATION PRESENTED BY TEAM JAVA2K!");
        System.out.println("********************************************");

        //Get list of players
        List<Player> players=game.getPlayerListFromConsole();
        //Construct gameActual with tha List of players including the dealer as one of the players
        Game gameActual= new Game(players);
        //Game delegates the dealer to deal shuffle the card
        gameActual.dealer.shuffleDeck();

        gameActual.dealer.deal(gameActual.getPlayers());

        System.out.println("Give our Dealer "+gameActual.dealer.getName()+" a sec to deal.");
        System.out.println();
        Thread.sleep(2000);

        //Print the hands dealt TODO time permitting hide dealers second card
        gameActual.getPlayers().forEach(player -> System.out.println((player)+"\n"));

        //Begin play
        gameActual.startGame();

    }
}
