package Algorithms;

import java.util.ArrayList;
import java.util.Stack;

public class Streak{
    //an object of streak will be created every iteration
    //most shall be deleted however since they're not big enough
    int size;
    Stack<String> modes;
    //because recursion of custom linkedlist is FIFO, order of the modes will be switched
    //would not cause any internal errors, however, having the games chronologically set would be more visually appealing
    Boolean streakType;
    public Streak(){
        //account for the first game(s)
        size++;
    }
}

//NECESSARY CLASSES FOR POJO "plain old java object"
class WinStreak extends Streak{
final Boolean streakType = true;
}
class LoseStreak extends  Streak{
final Boolean streakType = false;
}