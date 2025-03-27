package Algorithms;

import POJO.IndividualInformation;
import POJO.Item;
import POJO.Player;


import java.util.ArrayList;
import java.util.LinkedList;

public class IndividualStatsCalculator extends StatsAlgorithm {
    ArrayList<Player> playerJudgement = new ArrayList<>();
    //Constructor when used as an aggregation inside the WallAlgorithm classes, will include name, and will use constructors overridden by the WallAlgorithms

    IndividualStatsCalculator(LinkedList<WinStreak> winStreaks, LinkedList<LoseStreak> loseStreaks, double winLoseRate, String name){
        this.winStreaks = winStreaks;
        this.loseStreaks = loseStreaks;
        this.winLoseRate = winLoseRate;
        this.name = name;
    }

    //Constructor for sole purpose of calculating individual stats (no name, runs methods inside constructor
     public IndividualStatsCalculator(ArrayList<Item> item) {
         System.out.println("new individual stats checker");
        //Gets the Queue of item (which includes battle and event and such) from BattleLog items
        makeBattle(item);
        findData();
        findStreak();
        winLoseRate = (double) netWins/getSize();
    }
//gatherStreak for individual data is different from WALL data; instead of keeping steaks when game is tied, it would reset it.
@Override
//this is an alternative, hybrid way of finding Win/LoseStreaks in one iteration
public void findStreak() {
    if (first != null) {
        Streak streak = startNewStreak(first);
        findStreak(first.reference, streak);
    }else{
        System.out.println("cannot find individual, is their name right? do they exist? have they battled recently?");
    }

}

@Override
     public boolean findIfStreakWon(Node node){
        if (node.item.battle.hasWon == true || node.item.battle.hasDrawn == true) {
            return true;
        }
        return false;
    }



    /**
     * POJO constructor for Jackson:
     */
public IndividualInformation getInformation(){
    IndividualInformation info = new IndividualInformation();
    while(winStreaks.peek() != null){
        //use poll if pop doesn't work due to exception
        info.addStreak(winStreaks.poll());
    }
    while(loseStreaks.peek() != null){
        info.addStreak(loseStreaks.poll());
    }
    info.setNetTrophy(netTrophy);
    info.setNetWins(netWins);
    info.setWinLoseRate(winLoseRate);
    //if broken, will return NaN
    if(Double.isNaN(info.getWinLoseRate())){
        info.setPOJOStatus("error");
    }
    return info;
}
}

