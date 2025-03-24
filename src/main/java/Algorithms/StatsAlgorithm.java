package Algorithms;


import POJO.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Algorithm is an abstract class and serves as an abstract data structure that implements a list container
 * It is meant for easy and light-weight iterations through the player battle logs
 * It iterates the battle log without deleting data through in-class iteration, which would then put the response into another POJO class with a 'has a' relation
 *
 * StatsAlgorithm serves as the primary class used to calculate *any one particular* player
 * slightly different rules for both individual and group wall calculations will be implemented in respected classes
 *
 *
 */

abstract class StatsAlgorithm implements hasStreak{
    //convert list into custom list as it is more maneuverable
    /**
     * Win/LoseStreaks are used as stacks since the algorithm for reading it is made to be backwards
     * not that it matters internally, it just makes the visuals more chronically appealing when further implementations were made
     */
    LinkedList<WinStreak> winStreaks = new LinkedList<>();
    LinkedList<LoseStreak> loseStreaks = new LinkedList<>();
    String name;
    int netTrophy = 0;
    int netWins = 0;
    //count ties as a loss as it does not yield reward.
    double  winLoseRate = 0.0;
    Node first;



    //no constructors, I decided that this abstract class is METHOD and VARIABLE only
    /**
     * it's difficult for Jackson to parse JSON into custom non-generic (POJO) data structures
     * this method transforms an array into a more flexible custom linkedlist
     */
    void makeBattle(ArrayList<Item> item) {
        first = new Node(item.get(0));
        Node reference = first;
        for (int i = 1; i < item.size(); i++) {
            reference.reference = new Node(item.get(i));
            reference = reference.reference;
        }
    }

    void findData() {
        findData(first);
    }
    private void findData(Node node){
        if(node == null){

        }else{
            netTrophy += node.item.battle.trophyChange;
            //some events do not give trophies
            if(node.item.battle.hasWon)
                netWins++;
            findData(node.reference);
        }
    }

    /**
     * Below are functions for calculating win/lose streaks, all children of this abstract class (which is every Algorithm based class for now) inherits this
     * It finds and record lengths of streaks as per the parameters of interface 'hasSteak'
     *
     * - findStreak: (from interface) actual class that initiates the search for Streaks, will call startNewStreak
     *      -> this method will only be implemented in CHILD classes
     *
     * - StartNewStreak: determines whether the new streak would be a win/lose steak after the old one was broken (or just started calculating)
     * - findStreak: actual process of recursion that finds streaks. Note: starts from latest game
     * - evaluateStreak: determines if the length of the streak is long enough to be recorded as one (ex: 1 would mean every game is a 'streak'
     *
     * - findIfStreakWon: for individual calculations, the streak will be KEPT in case of a draw
     *      -> will be @overwritten by class 'IndividualStatsCalculator'
     */
    Streak startNewStreak(Node node){
        Streak streak;
        if (findIfStreakWon(node)) {
            streak = new WinStreak();
        } else {
            streak = new LoseStreak();
        }
        //"first" game will always contribute to Streak
        streak.addMode(node.item.event.mode);
        return streak;
    }
    void findStreak(Node node, Streak streak){
        //edge case here:
        if(node == null){
            evaluateStreak(streak);
        }else{
            //if won == won, lose == lose; streak is being maintained?
            if(findIfStreakWon(node) == streak.streakType){
                streak.size++;
                streak.addMode(node.item.event.mode);
                findStreak(node.reference, streak);
            }else{
                evaluateStreak(streak);
                streak = startNewStreak(node);
                //new streak
                findStreak(node.reference, streak);

            }
        }
    }
    void evaluateStreak(Streak streak){
        int minStreak;
        //lose streak
        if(streak.streakType == false){
            if(streak.size >= minLoseStreak){

                loseStreaks.add((LoseStreak) streak);
            }
            //win streak
        }else{
            if(streak.size >= minWinStreak){
                winStreaks.add((WinStreak) streak);
            }
        }

    }
    public boolean findIfStreakWon(Node node){
        if (node.item.battle.hasWon == true) {
            return true;
        }
        return false;
    }



    /**
     *Below are node functions for a flexible implementation of LinkedList that allows flexible extensions of my project
     * For example, if later users found it necessary to make a calculation that would benefit from traversing up and down the linkedlist
     * they could make the 'list' bidirectional
     */
    public Node peek() {
        return first;
    }
    public Node pop() {
        Node x = first;
        first = first.reference;
        return x;
    }

    public int getSize(){
        int x = 0;
        if (first != null) {
            Node reference = first; //no need to copy
            x++;
            while (reference.reference != null) {
                reference = reference.reference;
                x++;
            }
        }
        return x;
    }
}
class Node{
    Item item;
    Node reference;
    Node(Item item){
        this.item = item;
    }
}
