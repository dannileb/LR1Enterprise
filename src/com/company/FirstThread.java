package com.company;
import java.io.IOException;

public class FirstThread extends Thread
{
    FirstThread(String name){
        super(name);
    }


    public void run() {
        String text = "the first thread writes the time:";
        while (!isInterrupted()) {
            try {
                logic.firsThreadLogic(text);
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                break;
            }
        }

    }
}
