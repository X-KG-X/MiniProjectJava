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

    @Test(expected = IllegalArgumentException.class)
    public void testSetPlay() {
        p1.setPlay("");
        assertEquals(Player.Play.STAND,p1.getPlay());
    }

    @Test
    public void testCheckTotal(){
        List<Player> players=new ArrayList<>();
        players.add(p1);
        Game game= new Game(players);
        game.dealer.shuffleDeck();
        game.dealer.deal(players);

        System.out.println(p1);
        System.out.println(p1.checkTotal());
        game.dealer.hit(p1);
        System.out.println(p1);
        System.out.println(p1.checkTotal());
        System.out.println(p1.checkStatus());
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
        game.dealer.deal(players);
        System.out.println(players.get(0));
    }
}