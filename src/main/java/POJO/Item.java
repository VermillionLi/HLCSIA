package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Item {
    public Event event;
    public Battle battle;
    public Item(){

    }

    public Item(@JsonProperty("event") Event event, @JsonProperty("battle") Battle battle){
        this.event = event;
        this.battle = battle;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
