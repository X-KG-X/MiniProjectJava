package com.annapurna.client;

public class Scratch {
    //    public void startGame() throws InterruptedException {
//        setUpGame();
//        GameHelper gameHelper = new GameHelper(players, dealer, this);
//        for (Player player : getPlayers()) {
//            String status = null;
//            if (!player.isDealer()) { //Player's turn
//                status = player.checkStatus();
//                if (status.equals("WIN")) {
//                    gameHelper.winLose(player,"WIN");
////                } else if (status.equals("LOSE")) {
////                    gameHelper.winLose(player,"LOSE");
//                } else {// "LIVE"
//                    gameHelper.playTurn(player);
//                }
//            }
//            else { //Dealer's turn
//                status=player.checkStatus();
//                if(status.equals("WIN")){
//                    gameHelper.winLose(player,"WIN");
////                }
////                else if(status.equals("LOSE")){
////                    gameHelper.winLose(player,"LOSE");
//                }
//                else{ // Dealer "LIVE" -->needs to hit till his/her total is >=17
//                    while(player.checkStatus().equals("LIVE")){
//                        boolean decision=dealer.hit(player);
//                        if(!decision){
//                            System.out.println("Up next compare all the remaining hands!");
//                            gameHelper.compareLiveHands();
//                        }
//                        if(player.checkStatus().equals("WIN")){
//                            gameHelper.winLose(player,"WIN");
//                        }
//                        else if(player.checkStatus().equals("LOSE")){
//                            gameHelper.winLose(player,"LOSE");
//                        }
//                    }
//                }
//            }
//        }
//    }
}
