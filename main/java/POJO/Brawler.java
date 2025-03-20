package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Brawler {
    String name;
    int power;
    int trophies;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Brawler(String name, int power, int trophies) {
        this.name = name;
        this.power = power;
        this.trophies = trophies;
    }
}
