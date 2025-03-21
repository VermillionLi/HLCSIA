package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Brawler {
    String name;
    int power;
    int trophies;

    public Brawler( @JsonProperty("name") String name, @JsonProperty("power") int power, @JsonProperty("trophies") int trophies) {
        this.name = name;
        this.power = power;
        this.trophies = trophies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }
}
