package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start\n");
        new FirstThread("Первый поток").start();
        new SecondThread("Второй поток").start();
    }
}
