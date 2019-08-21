package com.example.scotiabankplus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Stack;

import butterknife.ButterKnife;



public class CalculatorActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();
    final int TOTAL_BUTTONS = 14;
    final String EMPTY_RESULT = "";
    final String ZERO_RESULT = "0";
    final String CLEAR = "C";
    boolean isDigitDirty = false;
    boolean isOperatorDirty = false;

    final String[] KEYBOARD_BUTTONS = {"7","8","9","x","4","5","6","-","1","2","3","+","C","0",".","="};


    final BinaryOperator stackedBinaryOperator = new BinaryOperator();
    Stack<Long> stackedNumber = new Stack<Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate");

        TextView resultExpression = findViewById(R.id.resultExpression);
        TextView result = findViewById(R.id.result);


        View.OnClickListener event = (view) -> {
                Button button = (Button) view;
                System.out.println(String.format("Clicked button id[%s] Number[%s]",button.getId(),button.getText()));
                action(button,result,stackedBinaryOperator,resultExpression);
        };

        /*
        for (int i = 0; i <= TOTAL_BUTTONS; i++) {
            int id = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button digitButton = (Button) findViewById(id);
            digitButton.setOnClickListener(appendDigitListener);
        }*/
        initCalculatorKeyboard(event);
    }

    public void initCalculatorKeyboard(View.OnClickListener event) {
        TableLayout calculatorKeyboardLayout = findViewById(R.id.calculatorKeyboard);
        int keyboardGridIndex = 0;
        int buttonsPerRow = 4;
        String[][] keyboardGrid = {Arrays.copyOfRange(KEYBOARD_BUTTONS,keyboardGridIndex,keyboardGridIndex+=buttonsPerRow),
        Arrays.copyOfRange(KEYBOARD_BUTTONS,keyboardGridIndex,keyboardGridIndex+=buttonsPerRow),
        Arrays.copyOfRange(KEYBOARD_BUTTONS,keyboardGridIndex,keyboardGridIndex+=buttonsPerRow),
        Arrays.copyOfRange(KEYBOARD_BUTTONS,keyboardGridIndex,keyboardGridIndex+=buttonsPerRow)};
        for (String[] keyboardRow : keyboardGrid) {
            TableRow tableRow = new TableRow(this);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT
            );
            tableRow.setLayoutParams(layoutParams);
            tableRow.setMinimumHeight(100);
            tableRow.setOrientation(LinearLayout.VERTICAL);
            for (String keyboardKey: keyboardRow) {
                Button calculatorButton = new Button(this);
                TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT
                );
                calculatorButton.setLayoutParams(tableRowParams);
                calculatorButton.setText(keyboardKey);
                calculatorButton.setOnClickListener(event);
                tableRow.addView(calculatorButton);

            }
            calculatorKeyboardLayout.addView(tableRow);
        }
    }

    protected void action(Button button,TextView result,BinaryOperator stackedBinaryOperator,TextView resultExpression) {
        String type = button.getText().toString();
        if (CLEAR.equalsIgnoreCase(type)) {
            result.setText(ZERO_RESULT);
            resultExpression.setText(EMPTY_RESULT);
            stackedBinaryOperator.clear();
            stackedNumber.clear();
            isDigitDirty = false;
            isOperatorDirty = false;
            return;
        }
        if (!isOperatorDirty) {
            if (Arrays.asList(BinaryOperator.BINARY_OPERATORS).contains(type)) {
                System.out.println(String.format("Resolving operator [%s]", type));
                System.out.println(String.format("Adding number to stack [%s]", result.getText().toString()));
                stackedNumber.push(Long.valueOf(result.getText().toString()));
                resolveBinary(button, result, stackedBinaryOperator, resultExpression);
                resultAppender(button, result, resultExpression);
                isOperatorDirty = true;
            }
            if (type.equalsIgnoreCase(BinaryOperator.EQUALS)) {
                resultExpression.setText(result.getText());
                isDigitDirty = false;
                isOperatorDirty = false;
            }
        }
        Character digit = type.charAt(0);
        if (Character.isDigit(digit)) {
            if (isDigitDirty) {
                result.setText(EMPTY_RESULT);
                isDigitDirty = false;
            }
            digitAppender(button, result, resultExpression);
            isOperatorDirty = false;
        }
    }

    protected void resultAppender(Button button,TextView result,TextView resultExpression) {
        System.out.println(String.format("resultAppender [%s]",button.getText()));
        resultExpression.append(button.getText());
    }

    protected void digitAppender(Button button,TextView result,TextView resultExpression) {
        System.out.println(String.format("Appending digit [%s]",button.getText()));
        String resultString = String.valueOf(result.getText());
        String digit = String.valueOf(button.getText());
        if (digit.equalsIgnoreCase(ZERO_RESULT) && resultString.startsWith(ZERO_RESULT)) {
            System.out.println(String.format("Left zero not appended digit [%s]",button.getText()));
            return;
        }
        result.setText(resultString.isEmpty()? resultString : String.valueOf((Long.valueOf(resultString))));
        result.append(digit);
        resultExpression.append(digit.isEmpty()? digit : String.valueOf((Long.valueOf(digit))));
    }

    protected void resolveBinary(Button button,TextView result,BinaryOperator stackedBinaryOperator,TextView resultExpression) {
        String operator = button.getText().toString();
        System.out.println(String.format("resolveBinary with operator:[%s]",operator));
        if (!stackedNumber.isEmpty() && !stackedBinaryOperator.valuesSet()) {
            stackedBinaryOperator.setNextValue(stackedNumber.pop());
            System.out.println(String.format("adding next value to binary operator[%s]",stackedBinaryOperator));
        }
        if (stackedBinaryOperator.valuesSet()) {
            Long evaluation = stackedBinaryOperator.evaluate();
            result.setText(String.valueOf(evaluation));
            stackedBinaryOperator.clear();
            stackedNumber.push(evaluation);
            stackedBinaryOperator.setNextValue(stackedNumber.pop());
            System.out.println(String.format("Binary evaluation val[%s] | [%s]",evaluation,stackedBinaryOperator));
        }
        stackedBinaryOperator.setOperator(button.getText().toString());
        isDigitDirty = true;
        System.out.println(String.format("Exiting resolve Binary - [%s]",stackedBinaryOperator));
    }

    protected void clearResult(Button button,TextView result) {
        result.setText(EMPTY_RESULT);
    }
}
