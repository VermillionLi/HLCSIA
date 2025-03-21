package Algorithms;

import POJO.Battle;
import POJO.Item;

import java.util.LinkedList;
import java.util.Queue;

public class WallAlgorithm extends StatsAlgorithm{

/**
 * Because wall of shame & wall of honor uses data of many different players
 * it will use an array of different battle lists of different players
 *
 * Unlike a list container, an array of battlelists (manyBattles) lets us iterate through the player lists multiple time, which helps ensure that the first player in the data structure
 * won't cover the first few tables on the wall
 *
**/
//PLEASE DO NOT USE the default class variables as there are multiple players to evaluate
//we just need the methods



IndividualStatsCalculator[] manyItems;
//later test if we can use same variable name as parent class and if it overrides
     WallAlgorithm(Queue<Item>[] manyItems) {
         for (int i = 0; i < manyItems.length; i++) {
            this.manyItems[i] = new IndividualStatsCalculator(manyItems[i]);
           //uses the IndividualStatsCalculator to find many individual stats, however, uses the StatsAlgorithm to determine how the sorting of each goes
        }
    }
//SUBCLASSES REDEFINE HOW THEY WANT THE SORTING TO GO
    /**
     * For all wall algorithm, we pop the Queues as we go along because
     * we only use the queues once.
     *
     * Current factors for Wall:
     * 1) steaks [calculates by following over multiple rounds] (most important)
     * 2) net trophy (if below x or exceeding y) [calculates holistically]
     * 3) times starplayer was obtained [calculates holistically]
     * 3) losing/winning as an over/under dog [calculates per battle]
     * 4) playing certain brawlers too many times (ex: hank = good) [calculates holistically]
     */

    @Override
    public void findStreak() {
        //exact same as IndividualStatsCalculator but as a sake of 'safeguarding' the integrity of class structure it prevents future 'wrong' classes
        if (first != null) {
            Streak streak = startNewStreak(first);
            findStreak(first.reference, streak);
        }
    }
    //findIfSteakWon maintains that only true winning games count as a part of the streak
public class WallHonor extends WallAlgorithm{
        public WallHonor(Queue<Item>[] manyItems) {
            super(manyItems);
        }

        void evaluateStreak(Streak streak){
            //Wall honor, do not record loss streak, loss streak is null
            int minStreak;
            //win streak
            if(streak.streakType = true){
                if(streak.size >= minWinStreak){
                    winStreaks.add((WinStreak) streak);
                }
                //lose streak
            }else{
                //honor wall, if it's a loss streak, do nothing
            }

        }

    }

public class WallShame extends WallAlgorithm{
        public WallShame(Queue<Item>[] manyItems) {
            super(manyItems);
        }


    void evaluateStreak(Streak streak){
            //DO NO RECORD WIN STREAK (Shame)

        int minStreak;
        //win streak
        if(streak.streakType = true){
           //if it is a win streak, do nothing
        }else{
            if(streak.size >= minLoseStreak){
                loseStreaks.add((LoseStreak) streak);
            }
        }

    }

    }

    /**
     * the 'randoms' (random teammates) wall is different and have 3 fields:
     * 1) their name
     * 2) whether they are 'randumbs' or good randoms
     * 3) what they did to earn that title
     *
     *
     * Current available data for calculation:
     * - their brawler
     * - difference between their trophy and avg in battle
     * - whether they have star player
     */
public static class WallTeammates {
    }
}
