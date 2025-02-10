package org.example;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String expression = sc.next();
        try{
            double result=evaluate(expression);
            System.out.println(result);
        }
        catch(Exception e){
            System.out.println("invalid result");
        }
    }
}