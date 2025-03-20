package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class Battle {
public String mode;
public String map;
public Boolean hasWon;
//use JSON result to determine
public Boolean hasDrawn = false;
//too rare a case, will be determined
int duration;
public int trophyChange;
public Player starPlayer;
public ArrayList<ArrayList<Player>> teams;
//account for games with multiple teams


public Battle(){
//DO. NOT. REMOVE. JACKSON PACKAGE REQUIRES A NO PARAMETER CONSTRUCTOR TO FUNCTION
}
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Battle(String mode, String map, String result, int duration, int trophyChange, Player starPlayer, ArrayList<ArrayList<Player>> teams) {
        this.mode = mode;
        this.map = map;
        if(result.equals("victory")){
            hasWon = true;
        }else if(result.equals("draw")){
            hasWon = false;
            hasDrawn = true;
        }else{
            hasWon = false;
        }
        this.duration = duration;
        this.trophyChange = trophyChange;
        this.starPlayer = starPlayer;
        this.teams = teams;
    }
}
