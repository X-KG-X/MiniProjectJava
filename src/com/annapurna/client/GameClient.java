package com.annapurna.client;

import com.annapurna.Dealer;
import com.annapurna.Game;
import com.annapurna.Player;

import java.util.ArrayList;
import java.util.List;

public class GameClient {
    public static void main(String[] args) {
        List<Player> players= new ArrayList<>();
        players.add(new Player("KG"));
        players.add(new Player("GG"));
        Game game1= new Game(players);


        Dealer dealer= new Dealer();
        game1.setDealer(dealer);

        game1.dealer.shuffleDeck();

        System.out.println(game1.dealer.getDeck().get(0).toString());

        game1.dealer.deal(players);

        System.out.println(players.get(0).getHand().toString());
        System.out.println(game1.dealer.getDeck().size());


    }
}
