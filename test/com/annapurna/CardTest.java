package com.annapurna;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testCard(){
        assertEquals(1,Card.Rank.ACE.value());
        System.out.println(new Card(Card.Rank.ACE, Card.Suit.DIAMOND));
    }

}