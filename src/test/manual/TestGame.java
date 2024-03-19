package test.manual;

import gui.MainGUI;

public class TestGame {

    public static void main(String[] args){
  
        MainGUI gameMainGUI = new MainGUI("RPG");

        Thread gameThread = new Thread(gameMainGUI);
        gameThread.start();
    }
}