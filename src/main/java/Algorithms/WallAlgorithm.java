package Algorithms;

import POJO.Battle;

import java.util.Queue;

public class WallAlgorithm extends StatsAlgorithm{
/**
 * Because wall of shame & wall of honor uses data of many different players
 * it will use an array of different battle lists of different players
**/
    public WallAlgorithm(Queue<Battle> battles) {
        super(battles);
    }

    /**
     * For all wall algorithm, we pop the Queues as we go along because
     * we only use the queues once.
     *
     * Current factors for Wall:
     * 1) steaks [calculates by following over multiple rounds]
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
            DocumentStreak(first.reference, streak);
        }
    }
    //findIfSteakWon maintains that only true winning games count as a part of the streak
public class WallHonor extends WallAlgorithm{
        public WallHonor(Queue<Battle> battles) {
            super(battles);
        }



    }

public class WallShame extends WallAlgorithm{
        public WallShame(Queue<Battle> battles) {
            super(battles);
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
