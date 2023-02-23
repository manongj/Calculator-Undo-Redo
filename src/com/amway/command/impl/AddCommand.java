package com.amway.command.impl;

import com.amway.command.UndoCommand;
import com.amway.entity.Calculator;

/**
 * 加法命令
 */
public class AddCommand implements UndoCommand {

    private Calculator calculator;
    private double valueToAdd;

    public AddCommand(Calculator calculator, double valueToAdd) {
        this.calculator = calculator;
        this.valueToAdd = valueToAdd;
    }

    /**
     * 执行加法
     */
    @Override
    public boolean execute() {
        calculator.add(valueToAdd);
        return true;
    }

    /**
     * 撤销加法
     */
    @Override
    public void undo() {
        calculator.subtract(valueToAdd);
    }

}
