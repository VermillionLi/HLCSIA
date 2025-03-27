package Algorithms;

import POJO.BattleLog;
import POJO.Player;
import POJO.WallInformation;

import java.util.ArrayList;

public class WallHonor extends WallAlgorithm {
    public WallHonor(BattleLog[] manyItems) {
        super((manyItems));
    }


    /**
     * The wall of honor judgement returns all the player values in which the player had obtained the star player title
     * Use user/implementer discretion on how you want to manage the data
     * Default: simply return the int # of times in which the player has obtained the title
     *
     * Future suggestion: Add the brawler in which the player obtained star player with, perhaps add battle duration as well...
     */
    public ArrayList<Player> findJudgement(Node reference, ArrayList<Player> x) {
        if(reference == null){
            return x;
        }else{
            if(reference.item.getBattle().getOurPlayer()!=null && reference.item.getBattle().starPlayer != null) {
                if (reference.item.getBattle().getOurPlayer().getTag().equals(reference.item.getBattle().starPlayer.getTag())) {
                    x.add(reference.item.getBattle().getOurPlayer());
                }
            }
            findJudgement(reference.reference, x);
        }

        return x;
    }

    void evaluateStreak(Streak streak){
        //Wall honor, do not record loss streak, loss streak is null
        //win streak
        if(streak.streakType == true){
            if(streak.size >= minWinStreak){
                winStreaks.add((WinStreak) streak);
            }
        }else{
            //honor wall, if it's a loss streak, do nothing
        }

    }

    /**
     * To ensure the first player on the list doesn't dominate the front page, the order is sorted by criteria as follows:
     * 1) WLR
     * 2) Streaks
     * 3) Starr player amount
     *
     */
    public WallInformation getInformation(){
        WallInformation info = new WallInformation();
        info.setTotalPeople(manyItems.length);
        //first check: winLoseRate
        for (int i = 0; i < manyItems.length ; i++) {
            if(manyItems[i].winLoseRate >= 0.5){
                info.addItems(manyItems[i].name, "Acquired a decent winLoseRate of " + manyItems[i].winLoseRate + ".");
            }
        }
        //2nd check: Streaks
        for (int i = 0; i < manyItems.length ; i++) {
            while(manyItems[i].winStreaks.peek() != null){
                info.addItems(manyItems[i].name, manyItems[i].winStreaks.poll().toString());
            }
        }
        for (int i = 0; i < manyItems.length ; i++) {
            int starPlayerAmount = manyItems[i].playerJudgement.size();
            if(starPlayerAmount >= 5) {
                info.addItems(manyItems[i].name, "Obtained star player a lot of time! (" + starPlayerAmount + " in the past 25 battles)" );
            }
        }
        return info;

    }






}