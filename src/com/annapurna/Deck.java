package com.annapurna;

import java.util.ArrayList;
import java.util.List;

class Deck {
    public static List<Card> createDeck() {
        List<Card> deck= new ArrayList<>(52);
        for (var suit : Card.Suit.values()) {
                for (var rank : Card.Rank.values()) {
                    deck.add(new Card(rank, suit));
                }
        }
        return deck;
    }
}
