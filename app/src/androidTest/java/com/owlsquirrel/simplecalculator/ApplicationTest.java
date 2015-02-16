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
        assertEquals("Screen should show 45", "45", result);
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
}

