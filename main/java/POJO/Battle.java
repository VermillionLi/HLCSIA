package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class Battle {
String mode;
String map;
Boolean hasWon;
//use JSON result to determine
int duration;
int trophyChange;
Player starPlayer;
ArrayList<ArrayList<Player>> teams;
//account for games with multiple teams
public Battle(){

}
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Battle(String mode, String map, String result, int duration, int trophyChange, Player starPlayer, ArrayList<ArrayList<Player>> teams) {
        this.mode = mode;
        this.map = map;
        if(result.equals("victory")){
            hasWon = true;
        }else{
            hasWon = false;
        }
        this.duration = duration;
        this.trophyChange = trophyChange;
        this.starPlayer = starPlayer;
        this.teams = teams;
    }
}
