package com.annapurna;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameHelperTest {
    private List<Player> players= new ArrayList<>();
    private Game game;
    private Card c1,c2,c3,c4,c5;
    private List<Card> dealerHand=new ArrayList<>();
    private  List<Card> playerHand=new ArrayList<>();
    private GameHelper gameHelper;



    @Before
    public void setUp() throws Exception {
        players.add(new Player("KG"));

        game=new Game(players);

        c1=new Card(Card.Rank.ACE, Card.Suit.DIAMOND);
        c2=new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND);
        dealerHand.add(c1);
        dealerHand.add(c2);
        c3=new Card(Card.Rank.ACE, Card.Suit.DIAMOND);
        c4=new Card(Card.Rank.TWO, Card.Suit.DIAMOND);
        c5=new Card(Card.Rank.TEN, Card.Suit.DIAMOND);
        playerHand.add(c3);
        playerHand.add(c4);
        playerHand.add(c5);
        game.getPlayers().get(0).setHand(playerHand);
        game.getDealer().setHand(dealerHand);
        gameHelper= new GameHelper(game.getPlayers(),game.getDealer(),game);

    }

    @Test
    public  void testPlayTurn(){
//        gameHelper.playTurn(game.getPlayers().get(0));
    }

    @Test
    public void testGetStandingHandSum(){
        System.out.println(gameHelper.getStandingHandSums(game.getPlayers()));
    }

    @Test
    public  void testCompareLiveHands() throws InterruptedException {
        gameHelper.compareLiveHands();

    }

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
    public void testWinLose() throws InterruptedException {
        List<Player> players=new ArrayList<>();
        players.add(new Player("KG"));

        Game game=new Game(players);

        Card c1=new Card(Card.Rank.SIX, Card.Suit.DIAMOND);
        Card c2=new Card(Card.Rank.TEN, Card.Suit.DIAMOND);
        Card c3=new Card(Card.Rank.ACE, Card.Suit.DIAMOND);
        Card c4=new Card(Card.Rank.TWO, Card.Suit.DIAMOND);
        List<Card> playerHand=new ArrayList<>();
        playerHand.add(c1);
        playerHand.add(c2);
        playerHand.add(c3);
        playerHand.add(c4);


;
        Card c5=new Card(Card.Rank.JACK, Card.Suit.DIAMOND);
        Card c6=new Card(Card.Rank.KING, Card.Suit.DIAMOND);
        List<Card> dealerHand=new ArrayList<>();
        dealerHand.add(c5);
        dealerHand.add(c6);

        game.getPlayers().get(0).setHand(playerHand);
        game.getDealer().setHand(dealerHand);

        GameHelper gameHelper= new GameHelper(game.getPlayers(),game.getDealer(),game);
//
//        gameHelper.winLose(game.getPlayers().get(1),"WIN");
        System.out.println(gameHelper.getStandingHandSums(players));


        gameHelper.compareLiveHands();


    }

}