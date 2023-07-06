package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an expression: ");
        String expression = scan.nextLine();
        scan.close();

        double result = Calculator.evaluateExpression(expression);
        System.out.println("Result: " + result);
    }
}