package com.company;
import java.io.IOException;

public class SecondThread extends Thread{

    SecondThread(String name){
        super(name);
    }


    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(15000);
                logic.secondThreadLogic();
            } catch (InterruptedException | IOException e) {
                break;
            }
        }

    }

}
