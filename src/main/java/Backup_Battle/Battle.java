package Backup_Battle;

import POJO.Player;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Battle {

    //public Event event;
    public Boolean hasWon;
    //use JSON result to determine
    public Boolean hasDrawn = false;
    //too rare a case, will be determined
    public int rank = 0;
    //use rank to determine if player has won
    //if rank = 0, rank is not supported in this type of battle (etc most games: 3v3, 5v5 brawl ball, gem grab etc... )
    //edge case is a friendly showdown match, allowing 2 players to play a 10 player showdown game, where the lowest rank is 2.
    //later implementations can fix this 'undesirable outcome'
    int duration;
    String result;
    public int trophyChange;
    public Player starPlayer;
    public ArrayList<ArrayList<Player>> teams;
//account for games with multiple teams

//sadfhoiadshh ADD NAME IN HRE, MAKE SURE NAME ENTITY IS CORRECT!!!!

    public Battle() {
//DO. NOT. REMOVE. JACKSON PACKAGE REQUIRES A NO PARAMETER CONSTRUCTOR TO FUNCTION
    }
    @JsonCreator
    public Battle(@JsonProperty("mode") String mode, @JsonProperty("map") String map,
                  @JsonProperty("result") String result, @JsonProperty("duration") int duration,
                  @JsonProperty("rank") int rank, @JsonProperty("trophyChange") int trophyChange,
                  @JsonProperty("starPlayer") Player starPlayer, @JsonProperty("teams") ArrayList<ArrayList<Player>> teams) {
        this.result = result;
        //might be bugged, in that case remove int rank in parameter
        this.rank += rank; //initialize rank away from 0
        if (result != null) {
            if (result.equals("victory")) {
                hasWon = true;
            } else {
                hasWon = false;
                if (result.equals("draw"))
                    hasDrawn = true;
            }
        } else if(rank > 0 ) {
            if(rank < teams.size()/3 ){ //scored above 66% of all teams
                hasWon = true;
            }else{
                hasWon = false;
            }

        }else {
            //in cases where there isn't a defined victory condition (very edge case etc: quitting a friendly solo bot match)
            if (trophyChange >= 0) {
                hasWon = true;
            } else {
                hasWon = false;
                if (trophyChange == 0)
                    hasDrawn = true;
            }
        }
        this.duration = duration;
        this.trophyChange = trophyChange;
        this.starPlayer = starPlayer;
        this.teams = teams;
    }


    //only put things you want to send to JavaScript here

    public Boolean getHasWon() {
        return hasWon;
    }
    public int getTrophyChange() {
        return trophyChange;
    }
    public ArrayList<ArrayList<Player>> getTeams() {
        return teams;
    }
    public Player getStarPlayer() {
        return starPlayer;
    }
    public int getDuration() {
        return duration;
    }


  //all setters for reference (in case @JacksonCreator constructor was changed/removed)
    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setHasDrawn(Boolean hasDrawn) {
        this.hasDrawn = hasDrawn;
    }
    public void setHasWon(Boolean hasWon) {
        this.hasWon = hasWon;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setTrophyChange(int trophyChange) {
        this.trophyChange = trophyChange;
    }
    public void setStarPlayer(Player starPlayer) {
        this.starPlayer = starPlayer;
    }
    public void setTeams(ArrayList<ArrayList<Player>> teams) {
        this.teams = teams;
    }
}
