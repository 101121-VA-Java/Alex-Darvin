package com.revature.models;

public class Human{
    String hairColor;
    int age;
    String name;
    boolean isRightHanded; 

    public Human(){
    }

    public Human(String name){
        this.name = name;
    }

    public static String eat(String food){
        return "I'm eating" + food; 
    }
    public void sleep(){
        System.out.println("zzzz");
    }
}