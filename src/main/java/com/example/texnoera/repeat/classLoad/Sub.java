package com.example.texnoera.repeat.classLoad;

public class Sub implements Super {
    static int age = 23;
    String name = "Farid";

    {
        System.out.println(name);
    }

    static {
        System.out.println(age);
    }

    public Sub() {
        System.out.println("Sub constructor");
    }

    public static void main(String[] args) {

        Sub sub = new Sub();

    }
}
