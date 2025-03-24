package Algorithms;

import JDBC.SQLite;
import POJO.BattleLog;
import POJO.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public class WallShame extends WallAlgorithm {
    String[] badBrawlers;
    public WallShame(BattleLog[] manyItems) throws SQLException {
        super(manyItems);
        SQLite db = new SQLite();
        badBrawlers = db.retrieveBadBrawlerTable();
    }

    /**
     * The Wall of Shame judgement returns an arraylist to the Individual stats including all the sinful brawlers that the player
     * had committed to using.
     *
     * Default: returns the # of times a player had used a bad brawler.
     */
    private ArrayList<Player> findJudgement(Node reference, ArrayList<Player> x) {
        if(reference == null){
            return x;
        }else{
            //do a binary search of whether it is a bad badbrawler
            if(isBadBrawler(reference.item.getBattle().getOurPlayer().getBrawler().getName())){
                //playing a bad brawler
                x.add(reference.item.getBattle().getOurPlayer());
            }
        }
        //shouldn't reach here
        System.out.println("critical error");
        return x;
    }

    /**
     * Iterative binary search to find if the string is a bad brawler
     */
    private boolean isBadBrawler(String brawler){
        //always match database file
        brawler = brawler.toUpperCase();
        int low = 0;
        int high = badBrawlers.length-1;
        while(low <= high){
            //check mid
            int mid = low + (high/low)/2;
            String currentCompare = badBrawlers[mid];
            if(brawler.equals(currentCompare)){
                //we got 'em boys
                return true;
            }
            //reference brawler is lexicographically smaller than our case
            if(brawler.compareTo(currentCompare) > 0){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return false;
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