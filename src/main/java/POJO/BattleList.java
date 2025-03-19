package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BattleList {
    List<Battle> items;

    public BattleList(){

    }
    //JACKSON NEEDS NO ARGUMENT CONSTRUCTOR
    public BattleList(List<Battle> items){
        this.items = items;
    }

    public List<Battle> getItems() {
        return items;
    }
}
