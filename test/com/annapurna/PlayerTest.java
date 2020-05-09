package com.annapurna;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPlay() {
        Game g1= new Game(1);
        Player p1= new Player("KG");
        List<Player> players= new ArrayList<>();
        players.add(p1);
        g1.setPlayers(players);
        p1.play("HIT");

    }
}