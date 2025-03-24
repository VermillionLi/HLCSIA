package Algorithms;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Streak{
    //an object of streak will be created every iteration
    //most shall be deleted however since they're not big enough
    int size;
    Stack<String> modes = new Stack<>();
    //because recursion of custom linkedlist is FIFO, order of the modes will be switched
    //would not cause any internal errors, however, having the games chronologically set would be more visually appealing
     boolean streakType;
    public Streak(){
        //account for the first game(s)
        size++;
    }

    public void addMode(String mode){
        if(mode.equals("unknown")){
            mode = "I don't know what game mode this is...";
        }
        modes.add(mode);
    }

    public String toString(String report) {
        report += "Chronological game modes played: \n";
        while(!modes.empty()){
            report+= modes.pop() + ", ";
        }
        return report;

    }
}

//NECESSARY CLASSES FOR POJO "plain old java object"
 class WinStreak extends Streak{

 WinStreak(){
    streakType = true;
}

    @Override
    public String toString() {
        String report = "";
        report += "Win streak of " + size + "." + "\n";
        return super.toString(report);
    }
}
 class LoseStreak extends  Streak{
 LoseStreak(){
    streakType = false;
}
     public String toString() {

         String report = "";
         report += "Lose streak of " + size + ".";
         report += super.toString(report);
         if(size > 3){
             report+= "\n consider going outside and touching some grass please";
         }

         return report;
     }
}