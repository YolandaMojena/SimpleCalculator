package com.owlsquirrel.simplecalculator;


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

    public void onNumPressed(char digit) {

        view.printDisplay(model.inputValue(digit));
       // model.setState();
    }

    public void onOperatorPressed(char operator) {

        view.printDisplay(model.inputOperation(operator));
       // model.setState();
    }

    public void onEqualPressed(){
        view.printDisplay(model.inputEqual());
       // model.setState();
    }


    public void onClearPressed() {
        model.inputClear();
        view.printDisplay("0");
        //model.setState();
    }

}
