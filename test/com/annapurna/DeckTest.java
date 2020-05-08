package com.annapurna;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testNewDeck() {
        Deck deck1= new Deck();

        assertEquals(52,Deck.newDeck().size());

        System.out.println(Deck.newDeck().get(51));
    }
}