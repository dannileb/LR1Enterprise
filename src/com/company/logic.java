package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class logic {
    static Calendar calendar = new GregorianCalendar();
    static File file = new File("F:\\Данила\\Учёба\\Enterprise\\LR1\\src\\com\\company\\currentdata.txt");


    public static void firsThreadLogic(String text) throws InterruptedException , IOException {
        try(FileWriter writer = new FileWriter(file, true))
        {
            System.out.println("\nFirst Thread has been started!");
            char symbol;
            for (int i=0; i<text.length(); i++) {
                symbol=text.charAt(i);
                writer.append(symbol);
                writer.flush();
                System.out.print(symbol);
                Thread.sleep(200);
            }
            writer.write(calendar.getTime().toString());
            System.out.print(calendar.getTime());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nFirst thread has been finished!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void secondThreadLogic() throws InterruptedException , IOException{
        System.out.println("\nSecond Thread has been started!");
        File fileNew = new File("F:\\Данила\\Учёба\\Enterprise\\LR1\\src\\com\\company\\"+ calendar.getTime().toString()+".txt");
        if (file.length() < 200){
                Files.copy(file.toPath(), fileNew.toPath());
                fileNew.createNewFile();
                PrintWriter pw = new PrintWriter(file);
                pw.close();

        }
        System.out.println("\nSecond Thread has been finished!");
    }
}
