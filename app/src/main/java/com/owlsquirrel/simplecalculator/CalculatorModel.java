package com.owlsquirrel.simplecalculator;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by usuario on 05/02/2015.
 */


public class CalculatorModel implements ICalculatorModel{

   //private BigDecimal num1;
   //private BigDecimal num2;
    private String num1;
    private String num2;
    private char currentOperator = 'n';
    private char lastOperator = 'n';
    private String lastNum = "0";
    private Boolean pointPressed = false;
    private Boolean equal = false;


    @Override
    public String operate(BigDecimal number1, char operator, BigDecimal number2) {

        BigDecimal result = number1;

        if(number1 != null && number2 != null)
        {
            switch(operator)
            {
                case '+':
                    result = number1.add(number2);
                    break;

                case '-':
                    result = number1.subtract(number2);
                    break;
                case '*':
                    result = number1.multiply(number2);
                    break;

                case '/':
                    if(number2.equals(new BigDecimal(0))){
                        inputClear();
                        return "Error";
                    }
                    else
                    {
                        result = number1.divide(number2);
                        break;
                    }

                default:
                    inputClear();
                    return "Error";
            }
        }
        else return result.toString();

        if (result.compareTo(new BigDecimal(99999999)) == 1 || result.compareTo(new BigDecimal(-99999999)) == -1){
            inputClear();
            return "Error";
        }
        return result.toString();
    }

    @Override
    public String inputOperation(char operator) {
        if(currentOperator!='n' && num2 != null){
            num1 = operate(new BigDecimal(num1), currentOperator, new BigDecimal(num2));
            num2 = null;
            num1 = cleanNum(num1);
        }
        else if (num1 == null || num1 == "Error"){
            num1 = "0";
        }
        else num1 = cleanNum(num1);
        currentOperator = operator;
        lastOperator = operator;
        pointPressed = false;
        equal = false;
        //return num1+currentOperator;
        return num1;
    }

    @Override
    public String inputValue(char value) {

        if (equal){
            num1 = null;
            equal = false;
        }

        if (value == '.' && !pointPressed){
            pointPressed = true;
            if (num1 == null || num1 == "Error") {
                num1 = String.format("0%s", value);
                lastNum = num1;
                return num1;
            }
            else if (currentOperator == 'n') {
                if(num1.length()<8) num1 += String.format("%s", value);
                lastNum = num1;
                return num1;
            }
            else if (num2 == null) {
                num2 = String.format("0%s", value);
                lastNum = num2;
                return num2;
            }
            else {
                if(num2.length()<8) num2 += String.format("%s", value);
                lastNum = num2;
                return num2;
            }
        }
        else if (value != '.') {
            if (num1 == null || num1 == "Error") {
                if (value == '0')
                {
                    lastNum = "0";
                    return "0";
                }
                num1 = String.format("%s", value);
                lastNum = num1;
                return num1;
            }
            else if (currentOperator == 'n') {
                if (pointPressed && num1.length()<=8) num1 += String.format("%s", value);
                if (!pointPressed && num1.length()<8) num1 += String.format("%s", value);
                lastNum = num1;
                return num1;
            }
            else if (num2 == null) {
                num2 = String.format("%s", value);
                lastNum = num2;
                return num2;
            }
            else {
                if (pointPressed && num2.length()<=8) num2 += String.format("%s", value);
                if (!pointPressed && num2.length()<8) num2 += String.format("%s", value);
                lastNum = num2;
                return num2;
            }
        }
        else{ // Si pulsas '.' por segunda vez
            if (currentOperator == 'n') {
                return num1;
            }
            else {
                return num2;
            }
        }
    }

    @Override
    public String inputEqual()
    {
        if(equal) {
            if (num1 == null || num1 == "Error") return "0";
            if(lastOperator != 'n') num1 = operate(new BigDecimal(num1), lastOperator, new BigDecimal(lastNum));
            else return num1;
        }
        else{
            equal = true;
            if (num1 == null || num1 == "Error") return "0";
            num1 = cleanNum(num1);
            if (num2 == null) {
                currentOperator='n';
                pointPressed=false;
                return num1;
            }
            if (currentOperator != 'n') {
                num1 = operate(new BigDecimal(num1), currentOperator, new BigDecimal(num2));
                num2 = null;
                num1 = cleanNum(num1);
                currentOperator = 'n';
                pointPressed = false;
            }
        }
        return num1;
    }

    @Override
    public void inputClear()
    {
        num1=null;
        num2=null;
        currentOperator = 'n';
        pointPressed = false;
        lastOperator = 'n';
        lastNum = null;
        equal = false;
    }

    @Override
    public String getState()
    {
        return num1 + "#" + num2 + "#" + currentOperator + "#" + pointPressed + "#" + lastOperator + "#" + lastNum + "#" + equal;
    }

    @Override
    public void setState(String state)
    {

    }


    private String cleanNum(String num){
        int i;
        Boolean exit = true;
        for (i = 0; i < num.length()-1; i++){
            if (num.charAt(i) == '.'){
                exit = false;
                break;
            }
        }
        if (exit) return num;

        for (i = num.length()-1; i > 0; i--){
            if (num.endsWith("0")) num = num.substring(0,num.length()-1);
            else if (num.endsWith(".")){
                num = num.substring(0,num.length()-1);
                break;
            }
            else break;
        }
        return num;
    }

}
