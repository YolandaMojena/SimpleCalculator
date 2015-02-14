package com.owlsquirrel.simplecalculator;

import android.os.Bundle;

/**
 * Created by usuario on 05/02/2015.
 */
public interface ICalculatorView {
    void printDisplay(String screen);
    String readDisplay();
    void onSaveInstanceState(Bundle outState);
}

