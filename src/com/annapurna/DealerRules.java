package com.annapurna;

import java.util.List;

public class DealerRules extends PlayerRules implements Rules{

    @Override
    public String checkStatus(Player player) {
        String result="LIVE";
        List<Integer> sumList= checkTotal(player);
        if (sumList.get(0)>=17){
            player.setPlay(Player.Play.STAND);
        }
        if(sumList.get(0)==21){
            result="WIN";
        }
        else if(sumList.get(0)>=21){
            result="LOSE";
        }
        return result;
    }


    //ACCESSOR METHODS

//TODO implement soft17/hard17 if more time

//    public Boolean isSoft17(Player player){
//        boolean result=false;
//        List<Integer> sumList=checkTotal(player);
//        if(player.getHand().size()==2 && sumList.get(1).equals(17)){
//            result=true;
//        }
//        return result;
//    }
}
