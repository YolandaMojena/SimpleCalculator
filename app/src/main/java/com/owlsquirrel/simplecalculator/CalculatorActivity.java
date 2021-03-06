package com.owlsquirrel.simplecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CalculatorActivity extends ActionBarActivity implements ICalculatorView {

    private static final java.lang.String BUNDLE_VIEWMODEL_STATE = "calculatorState";
    TextView display;
    CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView)findViewById((R.id.display));

        viewModel = new CalculatorViewModel(new CalculatorModel(),this);

        display.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        }));

        if (savedInstanceState != null)
            viewModel.setState(savedInstanceState.getString(BUNDLE_VIEWMODEL_STATE));

    }


    public void numberPressed(View view) {

        Button button = (Button)view;
        viewModel.onNumPressed(button.getText().charAt(0));
    }

    public void operatorPressed(View view) {
        Button button = (Button)view;
        viewModel.onOperatorPressed(button.getText().charAt(0));
    }

    public void equalPressed(View view) {
        viewModel.onEqualPressed();
    }


    public void clearPressed(View view) { viewModel.onClearPressed(); }


    public void MCPressed(View view) { viewModel.onMCPressed(); }
    public void MRPressed(View view) { viewModel.onMRPressed(); }
    public void MAPressed(View view) { viewModel.onMAPressed(); }
    public void MSPressed(View view) { viewModel.onMSPressed(); }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void printDisplay(String screen) {
        this.display.setText(screen);
    }

    public String readDisplay() {
        return String.format("%s", this.display.getText());
    }

    @Override
    public void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        String viewModelState = viewModel.getState();
        outState.putString(BUNDLE_VIEWMODEL_STATE, viewModelState);
    }
}



