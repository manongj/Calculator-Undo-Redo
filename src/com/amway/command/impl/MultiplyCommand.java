package com.amway.command.impl;

import com.amway.command.UndoCommand;
import com.amway.entity.Calculator;

/**
 * 乘法命令
 */
public class MultiplyCommand implements UndoCommand {

    private Calculator calculator;
    private double valueToAdd;

    public MultiplyCommand(Calculator calculator, double valueToAdd) {
        this.calculator = calculator;
        this.valueToAdd = valueToAdd;
    }

    /**
     * 执行乘法
     */
    @Override
    public boolean execute() {
        calculator.multiply(valueToAdd);
        return true;
    }

    /**
     * 撤销乘法
     */
    @Override
    public void undo() {
        calculator.divide(valueToAdd);
    }

}
