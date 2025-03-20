package Algorithms;

import POJO.Battle;


import java.util.Queue;

public class StatCalculator extends Algorithm implements hasStreak{
int netTrophy = 0;
int netWins = 0;


    public StatCalculator(Queue<Battle> battles) {
        super(battles);

    }
    public void findStreak(){
        if(battles.peek() != null)
            findStreak();
    }
    public void findStreak(Node x){

    }

    /**
     * Exclusive to StatCalculator:
     * it sorts a player's battlelog based on the highest trophy gain
     * let's them see their handywork
     */
    public void sortBattles(){
        //DO NOT USE UNTIL STEAKS ARE SET
        //decide if convert into array (it's only 25 objects)
        //
}

}

class Node{
    Battle battle;
    Node reference;
}
