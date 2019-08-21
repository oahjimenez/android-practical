package com.example.scotiabankplus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Stack;

import butterknife.ButterKnife;



public class CalculatorActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();
    final int TOTAL_BUTTONS = 13;
    final String EMPTY_RESULT = "";

    final BinaryOperator stackedBinaryOperator = new BinaryOperator();
    Stack<Long> stackedNumber = new Stack<Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate");

        TextView result = findViewById(R.id.result);

        Button.OnClickListener appendDigitListener = new Button.OnClickListener() {
            public void onClick(View view) {
                Button button = (Button) view;
                System.out.println(String.format("Clicked button id[%s] Number[%s]",button.getId(),button.getText()));
                action(button,result,stackedBinaryOperator);
            }
        };

        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            int id = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button digitButton = (Button) findViewById(id);
            digitButton.setOnClickListener(appendDigitListener);
        }
    }

    protected void action(Button button,TextView result,BinaryOperator stackedBinaryOperator) {
        String type = button.getText().toString();
        if (Arrays.asList(BinaryOperator.BINARY_OPERATORS).contains(type)) {
            System.out.println(String.format("Resolving operator [%s]",type));
            System.out.println(String.format("Adding number to stack [%s]",result.getText().toString()));
            stackedNumber.push(Long.valueOf(result.getText().toString()));
            resolveBinary(button,result,stackedBinaryOperator);
            return;
        }
        resultAppender(button,result);
    }

    protected void resultAppender(Button button,TextView result) {
        System.out.println(String.format("Appending digit [%s]",button.getText()));
        result.append(button.getText());
    }

    protected void resolveBinary(Button button,TextView result,BinaryOperator stackedBinaryOperator) {
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
            System.out.println(String.format("Binary evaluation val[%s] | [%s]",evaluation,stackedBinaryOperator));

        }
        if (stackedBinaryOperator.isLeftValueSet() && !stackedBinaryOperator.isRightValueSet()) {
            System.out.println(String.format("Exiting resolve Binary - [%s]",String.valueOf(stackedBinaryOperator.left)+operator));
        }
        stackedBinaryOperator.setOperator(button.getText().toString());
        System.out.println(String.format("Exiting resolve Binary - [%s]",stackedBinaryOperator));

    }

    protected void clearResult(Button button,TextView result) {
        result.setText(EMPTY_RESULT);
    }
}
