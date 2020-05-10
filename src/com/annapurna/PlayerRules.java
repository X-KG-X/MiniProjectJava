package com.annapurna;

import java.util.ArrayList;
import java.util.List;

public class PlayerRules implements Rules {
    @Override
    public List<Integer> checkTotal(Player player) {
        List<Integer> result=new ArrayList<>();
        Integer sum=0;
        Integer alternateSum=0;
        for(var card: player.getHand()){
            if (card.getRank()== Card.Rank.ACE){
                alternateSum=+11;
            }
            else {
                alternateSum+=card.getRank().value();
            }
            sum+=card.getRank().value();
        }
        result.add(sum);
        result.add(alternateSum);
        return result;
    }

    @Override
    public String checkStatus(Player player) {
        return null;
    }
}
