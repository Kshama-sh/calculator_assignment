package org.example;
import java.util.*;

public class Main {
    //function to evaluate the expression using 2 stacks
    public static double evaluate(String exp){
        //convert expression to char array
        char[] arr=exp.toCharArray();
        //initialise 2 stacks, one for operators and one for numbers
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        //for loop to traverse through the char array containing expression tokens
        for(int i=0;i<arr.length;i++){
            //check for decimal
            if ((arr[i] >= '0' && arr[i] <= '9') || arr[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < arr.length && (Character.isDigit(arr[i]) || arr[i] == '.')) {
                    sb.append(arr[i]);
                    i++;
                }
                //push the char to values stack
                values.push(
                        Double.parseDouble(sb.toString()));
                i--;
            }
            //push the parenthesis to operators stack
            else if (arr[i] == '(') {
                operators.push(arr[i]);
            }
            //if the existing operator is '(' pop and perform operation on the values
            else if (arr[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            }
            //if operator is popped then perform necessary operations
            else if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*' || arr[i] == '/') {
                while (!operators.isEmpty() && hasPrecedence(arr[i], operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(arr[i]);
            }
        }
        //if operators stack is not empty and values are still remaining perform operations on the
        // values by popping leftover values and operands
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }
    //function to check precedence
    private static boolean hasPrecedence(char operator1, char operator2)
    {
        if (operator2 == '(' || operator2 == ')')
            return false;
        return (operator1 != '*' && operator1 != '/') || (operator2 != '+' && operator2 != '-');
    }
    //function to apply operators for the operations
    private static double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException();
                return a / b;
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //taking user input
        System.out.println("Enter your expression: ");
        String expression = sc.next();
        try{
            double result=evaluate(expression);
            System.out.println(result);
        }
        catch(ArithmeticException e) {
            System.out.println("ArithmeticException: divide by 0 error");
        }
        catch(Exception e){
            System.out.println("invalid result");
        }
    }
}