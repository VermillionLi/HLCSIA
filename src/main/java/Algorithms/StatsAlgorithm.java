package Algorithms;

import POJO.Battle;

import java.util.Queue;

/**
 * Algorithm is an abstract class and serves as an abstract data structure that implements a list container
 * It is meant for easy and light-weight iterations through the player battle logs
 * It iterates the battle log without deleting data through in-class iteration, which would then put the response into another POJO class with a 'has a' relation
 *
 */
abstract class StatsAlgorithm implements hasStreak{
    //convert list into custom list as it is more maneuverable
    /**
     * Win/LoseStreaks are used as stacks since the algorithm for reading it is made to be backwards
     * not that it matters internally, it just makes the visuals more chronically appealing when further implementations were made
     */
    Queue<WinStreak> winStreaks;
    Queue<LoseStreak> loseStreaks;

    int netTrophy = 0;
    int netWins = 0;
    //count ties as a loss as it does not yield reward.
    //FOR FUTURE IMPLEMENTATIONS: add some feature if game is tied, especially in gem grab game mode, the glory is real there.

    Node first;

    public StatsAlgorithm(Queue<Battle> battles) {
        if(battles.peek() != null) {
            first = new Node(battles.poll());
            makeBattle(first, battles);
        }


        //remember to let jackson package ignore null objects in POJO
    }
    private void makeBattle(Node node, Queue<Battle> battles) {
        /**
         * Jackson cannot parse JSON into custom non-generic data structures
         * this method transforms a collection of list interface into a more flexible custom linkedlist
         */
        if(battles.peek() == null){
        }else{
            node.reference = new Node(battles.poll());
            //WARNING: for future reference: if editing the list, please use node.reference.battle instead, otherwise it would overwrite the reference and destroy data.
            makeBattle(node.reference, battles);
        }
    }
    void gatherData() {
        gatherData(first);
    }
    private void gatherData(Node node){
        if(node == null){

        }else{
            netTrophy += node.battle.trophyChange;
            //some events do not give trophies
            if(node.battle.hasWon)
                netWins++;
            gatherData(node.reference);
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
        streak.modes.add(node.battle.mode);
        return streak;
    }
    void DocumentStreak(Node node, Streak streak){
        //edge case here:
        if(node == null){
            evaluateStreak(streak);
        }else{
            //if won == won, lose == lose; streak is being maintained?
            if(findIfStreakWon(node) == streak.streakType){
                streak.size++;
                streak.modes.add(node.battle.map);
                DocumentStreak(node, streak);
            }else{
                streak = startNewStreak(node);
                //new streak
                DocumentStreak(node, streak);

            }
        }

    }
    void evaluateStreak(Streak streak){
        int minStreak;
        //win streak
        if(streak.streakType = true){
            if(streak.size >= minWinStreak){
                winStreaks.add((WinStreak) streak);
            }
            //lose streak
        }else{
            if(streak.size >= minLoseStreak){
                loseStreaks.add((LoseStreak) streak);
            }
        }

    }
    public Boolean findIfStreakWon(Node node){
        if (node.battle.hasWon == true) {
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


}
class Node{
    Battle battle;
    Node reference;
    Node(Battle battle){
        this.battle = battle;
    }
}
