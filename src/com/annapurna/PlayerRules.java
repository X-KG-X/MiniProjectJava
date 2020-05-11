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
                alternateSum=+1;
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
        String result="LIVE";
        List<Integer> sumList=checkTotal(player);
        if(sumList.get(0).equals(21)||sumList.get(1).equals(21)){
            result="WIN";
        }
        else if(sumList.get(0)>21&&sumList.get(1)>21){
            result="LOSE";
        }
        return result;
    }
}