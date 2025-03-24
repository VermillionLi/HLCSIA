package POJO;

import Algorithms.Streak;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualInformation {
    ArrayList<String> streakCommemoration = new ArrayList<>();
    int netTrophy;
    int netWins;
    double winLoseRate;
    public IndividualInformation(){

    }

    public ArrayList<String> getStreaks() {
        return streakCommemoration;
    }

    public void addStreak(Streak streak){
        streakCommemoration.add(streak.toString());
    }

    public int getNetTrophy() {
        return netTrophy;
    }

    public void setNetTrophy(int netTrophy) {
        this.netTrophy = netTrophy;
    }

    public int getNetWins() {
        return netWins;
    }

    public void setNetWins(int netWins) {
        this.netWins = netWins;
    }

    public double getWinLoseRate() {
        return winLoseRate;
    }

    public void setWinLoseRate(double winLoseRate) {
        this.winLoseRate = winLoseRate;
    }
}
