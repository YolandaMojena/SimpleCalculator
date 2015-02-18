package com.owlsquirrel.simplecalculator;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    CalculatorModel model;

    protected void setUp() throws Exception {
        super.setUp();
        model = new CalculatorModel();
    }

    //  SPECIFICATION TESTS

    public void test1(){
        model.inputValue('1');
        assertEquals("Screen should show 12", "12", model.inputValue('2'));
    }

    public void test2(){
        model.inputValue('0');
        model.inputValue('0');
        model.inputValue('0');
        assertEquals("Screen should show 1", "1", model.inputValue('1'));
    }

    public void test3() {
        model.inputValue('1');
        model.inputValue('.');
        model.inputValue('0');
        model.inputValue('0');
        assertEquals("Screen should show 1.000", "1.000", model.inputValue('0'));
    }

    public void test4() {
        model.inputValue('1');
        model.inputValue('.');
        model.inputValue('0');
        model.inputValue('0');
        model.inputValue('0');
        String result = model.inputEqual();
        assertEquals("Screen should show 1", "1", result);
    }

    public void test5() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputOperation('+');
        model.inputValue('3');
        model.inputValue('4');
        String result = model.inputEqual();
        assertEquals("Screen should show 46", "46", result);
    }

    public void test6() {
        model.inputValue('1');
        model.inputOperation('/');
        model.inputValue('3');
        String result = model.inputEqual();
        assertEquals("Screen should show 0.3333333", "0.3333333", result);
    }

    public void test7() {
        model.inputValue('1');
        model.inputOperation('/');
        model.inputValue('0');
        String result = model.inputEqual();
        assertEquals("Screen should show Error", "Error", result);
    }

    public void test8() {
        model.inputValue('1');
        model.inputOperation('/');
        model.inputValue('0');
        model.inputOperation('+');
        model.inputValue('3');
        String result = model.inputEqual();
        assertEquals("Screen should show 3", "3", result);
    }

    public void test9() {
        model.inputValue('3');
        model.inputValue('4');
        model.inputOperation('+');
        model.inputValue('2');
        model.inputValue('3');
        assertEquals("Screen should show 57", "57", model.inputOperation('/'));
    }

    public void test10() {
        model.inputValue('3');
        model.inputValue('4');
        model.inputOperation('+');
        model.inputValue('2');
        model.inputValue('3');
        model.inputOperation('/');
        model.inputValue('7');
        String result = model.inputEqual();
        assertEquals("Screen should show 8.1428571", "8.1428571", result);
    }

    public void test11() {
        model.inputValue('3');
        model.inputOperation('+');
        assertEquals("Screen should show 3", "3", model.inputOperation('*'));
    }

    public void test12() {
        model.inputValue('3');
        model.inputOperation('+');
        model.inputOperation('*');
        model.inputValue('5');
        String result = model.inputEqual();
        assertEquals("Screen should show 15", "15", result);
    }

    public void test13() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputValue('3');
        model.inputValue('4');
        model.inputValue('5');
        model.inputValue('6');
        model.inputValue('7');
        model.inputValue('8');
        model.inputValue('9');
        assertEquals("Screen should show 12345678", "12345678", model.inputValue('0'));
    }

    public void test14() {
        model.inputValue('1');
        model.inputValue('0');
        model.inputValue('0');
        model.inputValue('0');
        model.inputOperation('*');
        model.inputValue('1');
        model.inputValue('0');
        model.inputValue('0');
        model.inputValue('0');
        model.inputOperation('*');
        model.inputValue('1');
        model.inputValue('0');
        model.inputValue('0');
        model.inputValue('0');
        String result = model.inputEqual();
        assertEquals("Screen should show Error", "Error", result);
    }


    //  MEMORY TESTS

    public void memoryTest1() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputMA();
        model.inputValue('3');
        model.inputValue('2');
        String result = model.inputMR();
        assertEquals("Screen should show 12", "12", result);
    }

    public void memoryTest2() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputOperation('+');
        model.inputValue('3');
        model.inputValue('2');
        String result = model.inputMA();
        assertEquals("Screen should show 44", "44", result);
    }

    public void memoryTest3() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputMA();
        model.inputValue('2');
        model.inputValue('3');
        model.inputMR();
        model.inputOperation('+');
        model.inputValue('5');
        String result = model.inputEqual();
        assertEquals("Screen should show 17", "17", result);
    }

    public void memoryTest4() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputMA();
        model.inputValue('2');
        model.inputValue('3');
        model.inputMC();
        model.inputValue('4');
        model.inputValue('5');
        String result = model.inputEqual();
        assertEquals("Screen should show 2345", "2345", result);
    }

    public void memoryTest5() {
        model.inputValue('1');
        model.inputValue('2');
        model.inputMA();
        model.inputValue('2');
        model.inputValue('3');
        model.inputOperation('+');
        model.inputMR();
        model.inputMS();
        model.inputEqual();
        String result = model.inputMR();
        assertEquals("Screen should show -23", "-23", result);
    }


    //  CUSTOM TESTS

    public void recoverState () {
        model.inputValue('1');
        model.inputOperation('+');
        model.inputValue('4');
        model.inputValue('.');
        model.inputValue('3');

        String result = model.getState();
        String state = "1#4.3#+#true#+#4.3#false#";
        assertEquals(state, result);
    }

    public void clearModel (){
        model.inputValue('1');
        model.inputOperation('+');
        model.inputValue('4');
        model.inputClear();

        String result = model.inputEqual();
        assertEquals("0", result);
    }
}

