package com.annapurna;

public class Card {

    // INSTANT FIELDS
    private Suit suit;
    private Rank rank;

    // STATIC NESTED CLASSES
    public static enum Suit {HEART,SPADE,DIAMOND,CLUB}
    public static enum Rank{
        ACE(1), //TODO Ace can be 11 or 1
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private int value;
        Rank(int value){
            this.value=value;
        }
        public int value(){ return value;}
    }


    //CONSTRUCTORS
    public Card(Rank rank, Suit suit){
        this.suit=suit;
        this.rank=rank;
    }

    //ACCESSOR METHODS




    //OBJECT OVERRIDE


    @Override
    public String toString() {
        return "Card{" +
                "rank= " + rank +
                ", suit= " + suit +
                '}';
    }
}
