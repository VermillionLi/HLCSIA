package POJO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Queue;

public class BattleList {
    Queue<Item> items;

    public BattleList(){
        //JACKSON NEEDS NO ARGUMENT CONSTRUCTOR
    }
    @JsonCreator
    public BattleList(Queue<Item> items){
        this.items = items;
    }

    public Queue<Item> getItems() {
        return items;
    }
    public void setItems(Queue<Item> items) {
        this.items = items;
    }
}


