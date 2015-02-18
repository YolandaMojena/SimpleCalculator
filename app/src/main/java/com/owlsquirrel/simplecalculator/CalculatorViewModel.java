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

    public String getState()
    {
        return view.readDisplay()+ '#' + model.getState();
    }

    public void setState(String state)
    {
        int hashIndex = state.indexOf('#');
        view.printDisplay(state.substring(0,hashIndex));
        model.setState(state.substring(hashIndex+1));
    }



    public void onMCPressed(){
        model.inputMC();
    }
    public void onMRPressed(){
        view.printDisplay(model.inputMR());
    }
    public void onMAPressed(){
        view.printDisplay(model.inputMA());
    }
    public void onMSPressed(){
        view.printDisplay(model.inputMS());
    }

}
