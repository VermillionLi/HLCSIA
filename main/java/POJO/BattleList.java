package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Queue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BattleList {
    Queue<Battle> items;

    public BattleList(){

    }
    //JACKSON NEEDS NO ARGUMENT CONSTRUCTOR
    public BattleList(Queue<Battle> items){
        this.items = items;
    }

    public Queue<Battle> getItems() {
        return items;
    }
}
