package com.owlsquirrel.simplecalculator;


import java.math.BigDecimal;

/**
 * Created by usuario on 05/02/2015.
 */
public interface ICalculatorModel {

    String getState();
    void setState(String state);

    String operate(BigDecimal num1, char operator, BigDecimal num2);
    String inputValue(char value);
    String inputOperation(char operator);
    String inputEqual();
    void inputClear();

    void inputMC();
    String inputMR();
    String inputMA();
    String inputMS();
}

