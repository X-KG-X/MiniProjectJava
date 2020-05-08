package com.annapurna;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DealerTest {
    Dealer dealer;
    List<Player> players= new ArrayList<>();

    @Before
    public void setUp() {
        dealer=new Dealer();
        players.add(new Player("KG"));
        players.add(new Player("GG"));
    }

    @Test
    public void testDealerStaticName(){
        assertEquals("Polly", Dealer.NAME);
    }

    @Test
    public void testDealerNewDeck(){

        assertEquals(52,dealer.getDeck().size());
    }

    @Test
    public void testShuffleDeck(){
        System.out.println(dealer.shuffleDeck());
        assertTrue(dealer.shuffleDeck());
    }



    @Test
    public void testDeal(){
        //Check that each player including the dealer has been given 2 cards in order
        assertTrue(dealer.deal(players));
        assertTrue(dealer.deal(players));
        System.out.println(dealer.getDeck().size());
        System.out.println("-------------");
        System.out.println(players.get(0).getHand().toString());
        System.out.println(players.get(1).getHand().toString());

    }

}