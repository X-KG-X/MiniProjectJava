package com.annapurna;

import org.junit.Before;
import org.junit.Test;

import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    private Game game1;
    private List<Player> players=new ArrayList<>();

    @Before
    public void setUp() {
        players.add(new Player("KG"));
        players.add(new Player("GG"));
        game1= new Game(players);
    }

    @Test
    public void testPlayerTurn(){
        game1.dealer.deal(players);
        game1.playerTurn();
        assertEquals(3,players.get(0).getHand().size());
        System.out.println(players.get(0).getHand().toString());
    }

    @Test
    public void testGetSetPlayers() {
        List<Player> l1=new ArrayList<>();
        Player p1=new Player("KG");
        l1.add(p1);
        Game g1= new Game(l1);

        assertEquals(2,g1.getPlayers().size());

        g1.getPlayers().forEach(p-> System.out.println(p.getName()));
    }

    @Test
    public void testSetPlayerCount(){
        game1.setPlayerCount(3);
        assertEquals(4,game1.getPlayerCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPlayerCountException(){
        game1.setPlayerCount(8);
    }
}