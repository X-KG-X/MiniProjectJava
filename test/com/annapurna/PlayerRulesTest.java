package com.annapurna;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerRulesTest {



    @Test
    public void testCheckTotal() {
        Player p1=new Player("GG");
        Player p2=new Player("KG");
        List<Player>players=new ArrayList<>();
        players.add(p1);
        players.add(p2);
        Game g1=new Game(players);
        g1.dealer.deal(players);
        PlayerRules pr1= new PlayerRules();

        System.out.println(p1.getHand().toString());
        System.out.println(pr1.checkTotal(g1.getPlayers().get(0)).toString());
    }

    @Test
    public void testsCheckStatus(){
        // if sum of the cards is 21 then win(beat the dealer), if more than 21 lose, and if <21 Live and choose to either hit or stand
        Player p1=new Player("GG");
        Player p2=new Player("KG");
        List<Player>players=new ArrayList<>();
        players.add(p1);
        players.add(p2);
        Game g1=new Game(players);
        g1.dealer.deal(players);
        PlayerRules pr1= new PlayerRules();

        System.out.println(p1.getHand().toString());
        System.out.println(pr1.checkTotal(g1.getPlayers().get(0)).toString());

        assertEquals("LIVE",pr1.checkStatus(p1));

        g1.dealer.hit(p1);
        System.out.println(p1.getHand().toString());
        System.out.println(pr1.checkTotal(g1.getPlayers().get(0)).toString());

        assertEquals("LIVE",pr1.checkStatus(p1));

        g1.dealer.hit(p1);
        System.out.println(p1.getHand().toString());
        System.out.println(pr1.checkTotal(g1.getPlayers().get(0)).toString());

        assertEquals("LIVE",pr1.checkStatus(p1));

        g1.dealer.hit(p1);
        System.out.println(p1.getHand().toString());
        System.out.println(pr1.checkTotal(g1.getPlayers().get(0)).toString());

        assertEquals("LOSE",pr1.checkStatus(p1));

    }
}