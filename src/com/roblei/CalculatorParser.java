package com.roblei;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Logger;

public class CalculatorParser {

    public static Logger logger = Logger.getLogger("Calculator_Logger");

    public static void main(String[] args) {

        new CalculatorParser().start();

    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        logger.info("\n\t Please input the expression to calculate ");
        String line = sc.nextLine();
        List<String> postfixString = CalculatorParser.infixToPostfixConvert(line);
        Calculator calculator = Calculator.getInstance();
        calculator.setCurrent(new BigDecimal(0));

        calculate(calculator, postfixString);

        logger.info("Result is " + calculator.getCurrent());
    }

    private void calculate(Calculator calculator, List<String> postfixString) {
        Stack<BigDecimal> stack = new Stack<BigDecimal>();

        for (int i = 0; i < postfixString.size(); i++) {
            String next = postfixString.get(i);

            if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/")) {
                ArithmeticCalculatorCommand cmd = new ArithmeticCalculatorCommand(next.charAt(0), stack.pop(), stack.pop(), calculator);
                Invoker invoker = new Invoker();
                invoker.compute(cmd);
                stack.push(calculator.getCurrent());
            } else if (false) {

            } else {
                stack.push(new BigDecimal(next.trim()));
            }
        }
    }

    private static List<String> infixToPostfixConvert(String input) {

        int priority = 0;
        String postfixBuffer = "";
        Stack<Character> stack = new Stack<Character>();
        List<String> postfixArray = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (postfixBuffer.length() > 0) {
                    postfixArray.add(postfixBuffer);
                }
                postfixBuffer = "";
                // check the precedence
                if (stack.size() <= 0)
                    stack.push(ch);
                else {
                    Character chTop = (Character) stack.peek();
                    if (chTop == '*' || chTop == '/')
                        priority = 1;
                    else
                        priority = 0;
                    if (priority == 1) {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        } else {
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        }
                    } else {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            stack.push(ch);
                        } else {
                            stack.push(ch);
                        }
                    }
                }
            } else {
                postfixBuffer += ch;
            }
        }

        postfixArray.add(postfixBuffer);
        int len = stack.size();
        for (int j = 0; j < len; j++)
            postfixArray.add(stack.pop().toString());

        return postfixArray;
    }
}
