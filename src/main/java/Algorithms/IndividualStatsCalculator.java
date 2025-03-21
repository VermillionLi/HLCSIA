package Algorithms;

import POJO.Battle;
import POJO.Item;


import java.util.LinkedList;
import java.util.Queue;

public class IndividualStatsCalculator extends StatsAlgorithm {

    public IndividualStatsCalculator(Queue<Item> item) {
        if(item.peek() != null) {
            first = new Node(item.poll());
            makeBattle(first, item);
        }
        gatherData();
        findStreak();
    }
//gatherStreak for individual data is different from WALL data; instead of keeping steaks when game is tied, it would reset it.
@Override
//this is an alternative, hybrid way of finding Win/LoseStreaks in one iteration
public void findStreak() {
    if (first != null) {
        Streak streak = startNewStreak(first);
        findStreak(first.reference, streak);
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

