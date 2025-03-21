package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Player {
    String tag;
    String name;
    Brawler brawler;

    public Player( @JsonProperty("tag") String tag, @JsonProperty("name") String name, @JsonProperty("brawler") Brawler brawler) {
        this.tag = tag;
        this.name = name;
        this.brawler = brawler;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brawler getBrawler() {
        return brawler;
    }

    public void setBrawler(Brawler brawler) {
        this.brawler = brawler;
    }
}
