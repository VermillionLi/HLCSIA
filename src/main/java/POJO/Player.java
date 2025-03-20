package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Player {
    String tag;
    String name;
    Brawler brawler;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Player(String tag, String name, Brawler brawler) {
        this.tag = tag;
        this.name = name;
        this.brawler = brawler;
    }
}
