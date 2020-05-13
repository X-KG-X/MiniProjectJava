package com.annapurna;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GameHelperTest {
    @Test
    public void test(){
        List<Integer> list=new ArrayList<>();
        list.add(20);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(20);

        System.out.println(Collections.frequency(list,20));
        System.out.println(Collections.max(list));


    }

}