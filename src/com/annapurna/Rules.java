package com.annapurna;

import java.util.List;

public interface Rules {

    /*TODO
       1. maximum and minimum number of players
       2. win and lose conditions
       3. player hit and stand conditions
       4. dealer 17 rule
       5. ace 1 vs 11 player and dealer choice rule
    */

    public List<Integer> checkTotal();

    public String checkStatus();

    //ACCESSOR METHOD



}
