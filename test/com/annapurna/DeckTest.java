package com.annapurna;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testNewDeck() {

        assertEquals(52,Deck.newDeck().size());

        System.out.println(Deck.newDeck().get(51));
    }
}