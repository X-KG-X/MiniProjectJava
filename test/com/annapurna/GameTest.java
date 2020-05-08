package com.annapurna;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Game game1;

    @Before
    public void setUp() {
        game1= new Game();
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