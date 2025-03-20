package Algorithms;

public class WallAlgorithm {
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
    public static class WallHonor extends WallAlgorithm{
    }

    public static class WallShame extends WallAlgorithm{
    }

    /**
     * the 'randoms' (random teammates) wall is different and have 3 fields:
     * 1) their name
     * 2) whether they are 'randumbs' or good randoms
     * 3) what they did to earn that title
     * Current available data for calculation:
     * - their brawler
     * - difference between their trophy and avg in battle
     * - whether they have star player
     */
    public static class WallTeammates {
    }
}
