package com.annapurna;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testNewDeck() {

        assertEquals(52,Deck.createDeck().size());

        System.out.println(Deck.createDeck().get(51));
    }
}