package POJO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    public String mode;
    public String map;
    @JsonCreator
    public Event(@JsonProperty("mode") String mode, @JsonProperty("map") String map){
        this.mode = mode;
        this.map = map;
    }

    public String getMap() {
        return map;
    }
    public String getMode() {
        return mode;
    }
    public void setMap(String map) {
        this.map = map;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }



}
