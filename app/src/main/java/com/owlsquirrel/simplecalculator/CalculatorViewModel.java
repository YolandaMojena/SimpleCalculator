package com.owlsquirrel.simplecalculator;

//import static com.owlsquirrel.simplecalculator.ICalculatorModel.SUM;
//import static com.owlsquirrel.simplecalculator.ICalculatorModel.MUL;

/**
 * Created by usuario on 05/02/2015.
 */
public class CalculatorViewModel {
    private final ICalculatorModel model;
    private final ICalculatorView view;

    public CalculatorViewModel(ICalculatorModel model, ICalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onNumPressed() {

    }

    public void onOperatorPressed() {

    }

    public void onClearPressed() {

    }

}
