package com.example.scotiabankplus;

import java.util.Arrays;

public class BinaryOperator {


    public static final String ADD = "+";
    public static final String SUBSTRACT = "-";
    public static final String MULTIPLY = "x";
    public static final String EQUALS = "=";
    public static final String EMPTY_OPERATOR = "";

    public static final String[] BINARY_OPERATORS = {ADD,SUBSTRACT,MULTIPLY};


    public long left;
    private boolean isLeftSet;
    public long right;
    private boolean isRightSet;
    public String operator = EMPTY_OPERATOR;
    private boolean isOperatorSet;

    public void setNextValue(long value) {
        if (!this.isLeftValueSet()) {
            this.setLeftValue(value);
            return;
        }
        if (!this.isRightValueSet()) {
            this.setRightValue(value);
        }
        return;
    }

    public boolean valuesSet() {
       return this.isLeftValueSet() && this.isRightValueSet();
    }


    public void setLeftValue(long left) {
        this.left = left;
        this.isLeftSet = ( this.left == left);
    }

    public void setOperator(String operator) {
        this.operator = operator;
        this.isOperatorSet =  this.operator.equalsIgnoreCase(operator) && Arrays.asList(BINARY_OPERATORS).contains(operator);
    }

    public boolean isOperatorSet() {
        return !EMPTY_OPERATOR.equalsIgnoreCase(this.operator);
    }

    public boolean isLeftValueSet() {
        return isLeftSet;
    }

    public boolean isRightValueSet() {
        return isRightSet;
    }

    public void setRightValue(long right) {
       this.right = right;
       this.isRightSet = ( this.right == right);
    }


    public void clearRightValue() {
        this.right = 0L;
        this.isRightSet = false;
    }

    public void clearOperatorValue() {
        this.operator = EMPTY_OPERATOR;
        this.isOperatorSet = false;
    }

    public void clear() {
        this.isOperatorSet = false;
        this.isLeftSet = false;
        this.isRightSet = false;
    }

    public long evaluate() {
        long result;
        switch(operator) {
            case ADD: result= left+right; break;
            case SUBSTRACT: result= left-right; break;
            case MULTIPLY: result= left*right; break;
            case EQUALS: result= left; break;
            default: result = left;
        }
        setLeftValue(result);
        clearRightValue();
        clearOperatorValue();
        return this.left;
    }

    @Override
    public String toString() {
        return String.format("Operator:[%s] Left:[%s] Right:[%s] isLeftSet:[%s] isRightSet:[%s]",this.operator,this.left,this.right,this.isLeftSet,this.isRightSet);
    }
}
