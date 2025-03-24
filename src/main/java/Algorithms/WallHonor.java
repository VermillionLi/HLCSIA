package Algorithms;

import POJO.BattleLog;
import POJO.Player;

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
    private ArrayList<Player> findJudgement(Node reference, ArrayList<Player> x) {
        if(reference == null){
            return x;
        }else{
            if(reference.item.getBattle().getOurPlayer() == reference.item.getBattle().starPlayer){
                x.add(reference.item.getBattle().getOurPlayer());
            }
        }
        //shouldn't reach here
        System.out.println("critical error");
        return x;
    }

    void evaluateStreak(Streak streak){
        //Wall honor, do not record loss streak, loss streak is null
        int minStreak;
        //win streak
        if(streak.streakType = true){
            if(streak.size >= minWinStreak){
                winStreaks.add((WinStreak) streak);
            }
        }else{
            //honor wall, if it's a loss streak, do nothing
        }

    }






}