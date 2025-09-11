package com.example.texnoera.repeat.classLoad;

import java.util.Random;

public interface Super {
    int def = 23;

    int INIT = init();

    static int init() {
        int number = new Random().nextInt(5);
        System.out.println("MyInterface initialized, number: " + number);
        return number;
    }
}
