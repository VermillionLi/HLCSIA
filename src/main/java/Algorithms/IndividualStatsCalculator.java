package Algorithms;

import POJO.Battle;


import java.util.Queue;

public class IndividualStatsCalculator extends StatsAlgorithm {


    public IndividualStatsCalculator(Queue<Battle> battles) {
        super(battles);
        gatherData();
        findStreak();
    }
//gatherStreak for individual data is different from WALL data; instead of keeping steaks when game is tied, it would reset it.
@Override
//this is an alternative, hybrid way of finding Win/LoseStreaks in one iteration
public void findStreak() {
    if (first != null) {
        Streak streak = startNewStreak(first);
        DocumentStreak(first.reference, streak);
    }
}

@Override
     public Boolean findIfStreakWon(Node node){
        if (node.battle.hasWon == true || node.battle.hasDrawn == true) {
            return true;
        }
        return false;
    }


    /**
     * Exclusive to StatCalculator:
     * it sorts a player's battlelog based on the highest trophy gain
     * lets them see their handiwork
     */
    public void sortBattles(){
        //DO NOT USE UNTIL STEAKS ARE SET
        //decide if convert into array (it's only 25 objects)
        //
}


}

