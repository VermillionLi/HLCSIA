package POJO;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.Queue;

public class BattleLog {
    ArrayList<Item> items;

    String name;
    //name of user's battlelog
    String tag = "0000000";
    public BattleLog(){
        //JACKSON NEEDS NO ARGUMENT CONSTRUCTOR
    }
    @JsonCreator
    public BattleLog(ArrayList<Item> items){
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }
    public void setNameTag(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }


}


