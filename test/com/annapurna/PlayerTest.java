package com.annapurna;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player p1;

    @Before
    public void setUp() throws Exception {
        p1=new Player("KG");
    }

    @Test
    public void testHitOrStand() {
        assertTrue(p1.hitOrStand());
    }

    @Test
    public void testIsDealer(){
        List<Player> players= new ArrayList<>();
        players.add(new Player("KG"));
        players.add( new Player("GG"));
        Game game = new Game(players);
//        System.out.println(game.getPlayers().get(2).getName());

        assertTrue(game.getPlayers().get(2).isDealer());
    }
}