package com.annapurna.ui;

import com.annapurna.Game;
import com.annapurna.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameConsoleClientTest {



    @Test
    public void testHelperMax(){
        List<Integer> integerList=new ArrayList<>();
        integerList.add(0);
        integerList.add(19);
        integerList.add(17);

        assertEquals(19,GameConsoleClient.helperMax(integerList));

    }



}