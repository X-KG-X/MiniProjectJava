package com.annapurna;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DealerRulesTest {

//    @Test
//    public void testIsSoft17(){
//        Player p1=new Player("GG");
//        Player p2=new Player("KG");
//        List<Player> players=new ArrayList<>();
//        players.add(p1);
//        players.add(p2);
//        Game g1=new Game(players);
//        g1.dealer.deal(players);
//        DealerRules dr1= new DealerRules();
//
//        System.out.println(g1.getPlayers().get(2).getHand().toString());
//        System.out.println(dr1.isSoft17(g1.getPlayers().get(2)));
//
//        assertFalse(dr1.isSoft17(g1.getPlayers().get(2)));
//    }


    @Test
    public void testCheckStatus() {
        Player p1 = new Player("GG");
//        Player p2 = new Player("KG");
        List<Player> players = new ArrayList<>();
        players.add(p1);
//        players.add(p2);
        Game g1 = new Game(players);
        DealerRules dr1 = new DealerRules();

        g1.dealer.deal(players);
        System.out.println(g1.getPlayers().get(1).getHand());

        System.out.println(dr1.checkStatus(g1.getPlayers().get(1)));
        assertEquals("LIVE", dr1.checkStatus(g1.getPlayers().get(1)));

        g1.dealer.hit(g1.getPlayers().get(1));
        System.out.println(g1.getPlayers().get(1).getHand());

        System.out.println(dr1.checkStatus(g1.getPlayers().get(1)));
        assertEquals("LIVE", dr1.checkStatus(g1.getPlayers().get(1)));

        g1.dealer.hit(g1.getPlayers().get(1));
        System.out.println(g1.getPlayers().get(1).getHand());

        System.out.println(dr1.checkStatus(g1.getPlayers().get(1)));
        assertEquals("LIVE", dr1.checkStatus(g1.getPlayers().get(1)));

        g1.dealer.hit(g1.getPlayers().get(1));
        System.out.println(g1.getPlayers().get(1).getHand());

        System.out.println(dr1.checkStatus(g1.getPlayers().get(1)));
        assertEquals("LIVE", dr1.checkStatus(g1.getPlayers().get(1)));
    }


}