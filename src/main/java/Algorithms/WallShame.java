package Algorithms;

import JDBC.SQLite;
import POJO.BattleLog;
import POJO.Player;
import POJO.WallInformation;

import java.sql.SQLException;
import java.util.ArrayList;

public class WallShame extends WallAlgorithm {

    static final String[] badBrawlers;

    static {
        try {
            badBrawlers = new SQLite().retrieveBadBrawlerTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;
    public WallShame(BattleLog[] manyItems) throws SQLException {
        super(manyItems);

    }

    /**
     * The Wall of Shame judgement returns an arraylist to the Individual stats including all the sinful brawlers that the player
     * had committed to using.
     *
     * Default: returns the # of times a player had used a bad brawler.
     */
    public ArrayList<Player> findJudgement(Node reference, ArrayList<Player> x) {
        if(reference == null){
            return x;
        }else{
            //do a binary search of whether it is a bad badbrawler
            if(reference.item.getBattle().getOurPlayer()!=null) {
                if(isBadBrawler(reference.item.getBattle().getOurPlayer().getBrawler().getName())) {
                    //playing a bad brawler
                    x.add(reference.item.getBattle().getOurPlayer());
                }
            }
            findJudgement(reference.reference, x);
        }
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
            int mid = low + (high - low)/2;
            String currentCompare = badBrawlers[mid];
            //System.out.println(brawler + " matching " + currentCompare);

            if(brawler.equalsIgnoreCase(currentCompare)){
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
        if(streak.streakType == true){
            //if it is a win streak, do nothing
        }else{
            if(streak.size >= minLoseStreak){
                loseStreaks.add((LoseStreak) streak);
            }
        }
    }

    /**
     * To ensure the first player on the list doesn't dominate the front page, the order is sorted by criteria as follows:
     * 1) WLR
     * 2) Streaks
     * 3) BadBrawler amount (last because it is reallllly long)
     *
     *
     * PLEASE PLEASE PLEASE USE THE MANYITEMS ARRAY DATA INSTEAD OF THE DATA IN THIS CLASS, THEY WILL ALL BE 0
     */
public WallInformation getInformation(){
    WallInformation info = new WallInformation();
    info.setTotalPeople(manyItems.length);
    //first check: winLoseRate
    for (int i = 0; i < manyItems.length; i++) {
        if(manyItems[i].winLoseRate < 0.5){
            info.addItems(manyItems[i].name, "Acquired a recent winLoseRate of " + manyItems[i].winLoseRate);
        }
    }
    //2nd check: Streaks
    for (int i = 0; i < manyItems.length ; i++) {
        while(manyItems[i].loseStreaks.peek() != null){
            info.addItems(manyItems[i].name, manyItems[i].loseStreaks.poll().toString());
        }
    }
    for (int i = 0; i < manyItems.length ; i++) {
        int amountBadBrawlerPlayed = manyItems[i].playerJudgement.size();
        if(amountBadBrawlerPlayed > 1) {
            String myGrievance = "They've been a particularly bad individual, chronologically playing a mix of: ";
            for (int j = 0; j < amountBadBrawlerPlayed; j++) {
                myGrievance += "\n" +manyItems[i].playerJudgement.get(j).getBrawler().getName();
            }
            info.addItems(manyItems[i].name, myGrievance);
        }
    }
    return info;
}
}