package com.company;

import java.io.*;
import java.lang.Thread;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    /** Main class
     * */
    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        SecondThread secondThread = new SecondThread();
        firstThread.start();
        secondThread.start();
    }
}


/** Метод для запуска первого потока
 * */
class FirstThread extends Thread {
    String text = "the first thread writes the time: ";
    File file = new File("F:\\Данила\\Учёба\\Enterprise\\LR1\\src\\com\\company\\currentdata.txt");


    public void run(){
        System.out.println("First thread has been started");
        while (!isInterrupted()) {
            try {
                Logic logic = new Logic();
                logic.logicFirstThread(file, text);
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                break;
            }
        }
        System.out.println("First thread has been finished");
    }

}


/** Метод для запуска второго потока
 * */
class SecondThread extends Thread {
    File file = new File("F:\\Данила\\Учёба\\Enterprise\\LR1\\src\\com\\company\\currentdata.txt");
    public void run() {
        System.out.println("Second thread has been started");
        while (!isInterrupted()) {
            try{
                Thread.sleep(15000);
                Logic logic = new Logic();
                logic.logicSecondThread(file);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Second thread has been finished");
    }
}

/**
 * Класс логики потоков
 */
class Logic{


    /** Метод логики первого потока <b>FirstThread</b>
     * @param file: файл для записи текста посимвольно
     * @param text: текст для записи в файл
     * */
    public synchronized void logicFirstThread(File file, String text) throws IOException, InterruptedException {
        try(FileWriter write = new FileWriter(file, true)){
            for (int i=0; i<text.length(); i++){ //цикл для записи по букве в файл
                write.write(text.charAt(i));
                System.out.print(text.charAt(i));
                write.flush();
                Thread.sleep(200);
            }
            Calendar calendar = new GregorianCalendar();
            System.out.print(calendar.getTime());
            System.out.println();
            write.write(calendar.getTime().toString());
            write.flush();
            write.close();
            notify();
        }
    }


    /** Метод логики второго потока <b>SecondThread</b>
     * @param file: файл для роверки объема данных
     * */
    public synchronized void logicSecondThread(File file) throws InterruptedException {
        while(file.length() < 200){
            wait();
        }
        String date = ( new SimpleDateFormat( "yyyy-MM-dd'T'HH-mm-ss" ) ).format( Calendar.getInstance().getTime() );
        File fileNew = new File("F:\\Данила\\Учёба\\Enterprise\\LR1\\src\\com\\company\\", date+".txt");
        try {
            Files.copy(file.toPath(), fileNew.toPath());
            if (fileNew.createNewFile()){
                System.out.println("File "+ date + " successful created!");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

