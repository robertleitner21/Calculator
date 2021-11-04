package com.roblei;

import java.math.BigDecimal;

public class ArithmeticCalculatorCommand implements Command {

    private char operator;
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private Calculator calculator;

    public ArithmeticCalculatorCommand(char operator, BigDecimal leftOperand, BigDecimal rightOperand, Calculator calculator) {
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.calculator = calculator;
    }

    @Override
    public void calculate() {
        calculator.operation(operator, leftOperand, rightOperand);
    }
}
