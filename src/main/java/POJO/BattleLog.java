package POJO;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Queue;

public class BattleLog {
    Item[] items;

    String name = "default name";
    //name of user's battlelog
    String tag = "0000000";
    public BattleLog(){
        //JACKSON NEEDS NO ARGUMENT CONSTRUCTOR
    }
    @JsonCreator
    public BattleLog(Item[] items){
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }
    public void setItems(Item[] items) {
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


