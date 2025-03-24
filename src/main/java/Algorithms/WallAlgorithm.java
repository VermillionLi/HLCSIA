package Algorithms;

import POJO.BattleLog;
import POJO.Item;
import POJO.Player;

import java.util.ArrayList;

/**
 * For all wall algorithm, we pop the Queues as we go along because
 * we only use the queues once.
 *
 * Current factors for Wall:
 * 1) steaks [calculates by following over multiple rounds] (most important)
 * 2) net wins (if below x or exceeding y) [calculates holistically]
 * 3a) times starplayer was obtained [calculates holistically]
 * 3b) playing certain brawlers too many times (ex: hank = good) [calculates holistically]
 *
 * (not yet implemented (outside project scopt)
 * x) losing/winning as an over/under dog [calculates per battle]
 */

abstract class WallAlgorithm extends StatsAlgorithm{

/**
 * Because wall of shame & wall of honor uses data of many different players
 * it will use an array of different battle lists of different players
 *
 * Unlike a list container, an array of BattleLogs (manyBattles) lets us iterate through the player lists multiple time, which helps ensure that the first player in the data structure
 * won't cover the first few tables on the wall
 *
 *
 * When gathering data, it uses the custom LinkedList to iterate through calculations.
 *
 *
**/
IndividualStatsCalculator[] manyItems;
public WallAlgorithm(BattleLog[] manyItems) {
    this.manyItems = new IndividualStatsCalculator[manyItems.length];
    //Evaluate data and parse them
    for (int i = 0; i < manyItems.length; i++) {
        BattleLog x = manyItems[i];
        makeBattle(x.getItems());
        setOurGuy(x);
        //'first' is automatically overwritten when makeBattle is called
        findData();
        findStreak();
        this.name = x.getName();
        this.manyItems[i] = new IndividualStatsCalculator(winStreaks, loseStreaks, winLoseRate, name);
        this.manyItems[i].playerJudgement = findJudgement();
    }
}




    /**
     * The WallAlgorithm constructor collects the information itself before sending it to an array of IndividualStatsCalculator, which then holds the data.
     * It does not let the IndividualsStatsCalculator calculate the information due to Dynamic Method Dispatch (runtime polymorphism or some jargon), meaning
     * that class does not let WallAlgorithm classes override anything, so the only way to properly calculate is to spoon-feed it information
     */

    /**
     * setOutGuy iterates through the battlelog to determine the players our friends are in the batteLogs.
     * Unfortunately, The Brawl Stars API battlelog battles never explicitly states on which team and with what brawler
     * the player we are searching for is playing. It also returns the teams in an unsorted 2D array, making it difficult to find our player.
     *
     * However, there are only up to 5 teams per game as of March 2025 (that being duo showdown) and a max of 12 players per game in total (that being trio showdown)
     */
     void setOurGuy(BattleLog x) {
         String tag = x.getTag();
         Item[] items = x.getItems();
        /**
         * IN TERMS OF COMPLEXITY: this may look like a O(N^3) complexity, but it's not really, allow me to explain
         * Each battle consists of several teams, but the total # of players NEVER exceed 12. The 2d array is just how JSON
         * organizes these up to 12 (12 for trio showdown) players
         * (sometimes it's 2 (for duel), 4 (arcade mode), 6 (classic teams), or 10 (solo and duo showdown) players depending on the game)
         */
        //iterate all battles
        for (int i = 0; i < items.length; i++) {
            //get team for the i-th battle
            ArrayList<ArrayList<Player>> teams =  items[i].battle.getTeams();
            //iterate the teams
            for (int j = 0; j < teams.size(); j++) {
                //iterate the players in the team
                for (int k = 0; k < teams.get(j).size(); k++) {
                    Player p = teams.get(j).get(k);
                    if(p.getTag().equals(tag)) {
                        items[i].getBattle().setOurPlayer(p);
                        System.out.println(p.getName());
                        //since we found our player, we can move on to the next battle, setting j to max
                        j = teams.size();
                    }
                }
            }

        }
    }


//SUBCLASSES REDEFINE HOW THEY WANT THE SORTING TO GO

    public ArrayList<Player> findJudgement() {
        ArrayList<Player> x = new ArrayList<>();
        return findJudgement(first, x);

    }

    private ArrayList<Player> findJudgement(Node first, ArrayList<Player> x) {
         //nothing here, children will implement features
         return null;
    }

    @Override
    public void findStreak() {
        //exact same as IndividualStatsCalculator but as a sake of 'safeguarding' the integrity of class structure it prevents future 'wrong' classes
        if (first != null) {
            Streak streak = startNewStreak(first);
            findStreak(first.reference, streak);
        }
    }
    //findIfSteakWon maintains that only true winning games count as a part of the streak



    /**
     *
     * [outside project scope]
     *
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
public class WallTeammates {
    }
}
