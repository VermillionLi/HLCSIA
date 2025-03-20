package POJO;

public class Name {
    /**
     * BrawlStars API has no easy means to retrieve the name of a player
     * it comes in a large JSON doc and frankly would take time to deserialize
     * this POJO should only be used once per player. After that, the name associated with the tag goes in the DATABASE
     *
     * The servlet will associate the battleLog output of a player with the player's name from the database
     *
     */
    String name;
    public Name(){
        //JACKSON needs this
    }
    public Name(String name){
        this.name = name;
    }
}
