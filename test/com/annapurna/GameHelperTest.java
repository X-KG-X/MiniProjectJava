package com.annapurna;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GameHelperTest {
    @Test
    public void test(){
        List<Integer> list=new ArrayList<>();
        list.add(20);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(20);

        System.out.println(Collections.frequency(list,20));
        System.out.println(Collections.max(list));
    }

    @Test
    public void testWinLose(){
        List<Player> players=new ArrayList<>();
        players.add(new Player("KG"));

        Game game=new Game(players);

        Card c1=new Card(Card.Rank.ACE, Card.Suit.DIAMOND);
        Card c2=new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND);
        List<Card> dealerHand=new ArrayList<>();
        dealerHand.add(c1);
        dealerHand.add(c2);
        Card c3=new Card(Card.Rank.TEN, Card.Suit.DIAMOND);
        Card c4=new Card(Card.Rank.TWO, Card.Suit.DIAMOND);
        Card c5=new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND);
        List<Card> playerHand=new ArrayList<>();
        playerHand.add(c3);
        playerHand.add(c4);
        playerHand.add(c5);

        game.getPlayers().get(0).setHand(playerHand);
        game.getDealer().setHand(dealerHand);

        GameHelper gameHelper= new GameHelper(game.getPlayers(),game.getDealer(),game);

        gameHelper.winLose(game.getPlayers().get(0),"WIN");
    }

}