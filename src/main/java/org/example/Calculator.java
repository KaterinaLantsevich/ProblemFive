package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

    public static double evaluateExpression(String expression) {
        Deque<Double> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        expression = expression.replaceAll("\\s", "");

        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);

                while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    sb.append(expression.charAt(i + 1));
                    i++;
                }

                double operand = Double.parseDouble(sb.toString());
                operandStack.push(operand);
            } else if (isOperator(ch)) {
                while (!operatorStack.isEmpty() && hasPrecedence(operatorStack.peek(), ch)) {
                    performOperation(operandStack, operatorStack);
                }

                operatorStack.push(ch);
            }

            i++;
        }

        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        if (operandStack.size() != 1 || !operatorStack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return operandStack.pop();
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static int getPrecedence(char operator) {
        if (operator == '*' || operator == '/') {
            return 2; // Higher precedence
        } else if (operator == '+' || operator == '-') {
            return 1; // Lower precedence
        } else {
            return 0; // Invalid operator
        }
    }

    public static boolean hasPrecedence(char op1, char op2) {
        return getPrecedence(op1) >= getPrecedence(op2);
    }

    public static void performOperation(Deque<Double> operandStack, Deque<Character> operatorStack) {
        char operator = operatorStack.pop();
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        double result = 0;

        if (operator == '+') {
            result = operand1 + operand2;
        } else if (operator == '-') {
            result = operand1 - operand2;
        } else if (operator == '*') {
            result = operand1 * operand2;
        } else if (operator == '/') {
            result = operand1 / operand2;
        }

        operandStack.push(result);
    }
}
