package POJO;

import java.util.ArrayList;

public class WallInformation {

    int totalPeople;

    ArrayList<String[]> items;
    //String[] always 2 in length

    public ArrayList<String[]> getItems() {
        return items;
    }
    public WallInformation(){
        totalPeople = 0;
         items = new ArrayList<>();
    }

    public void addItems(String name, String reason) {
        String[] x = {name, reason};
        items.add(x);
    }
    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }


}
