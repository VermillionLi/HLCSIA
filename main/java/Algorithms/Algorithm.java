package Algorithms;

import POJO.Battle;
import POJO.BattleList;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

abstract class Algorithm {
    //find similarities
    //all Algorithms require the BattleList actually...
    //For now, the algorithm does not require *much* of usages of player stats, only used to find name
    Queue<Battle> battles;
    ArrayList<WinStreak> winStreaks;
    ArrayList<LoseStreak> loseStreaks;

    public Algorithm(Queue<Battle> battles) {
        this.battles = battles;

        //remember to let jackson package ignore null objects in POJO
    }
}
