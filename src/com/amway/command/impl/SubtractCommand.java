package com.amway.command.impl;

import com.amway.command.UndoCommand;
import com.amway.entity.Calculator;

/**
 * 减法命令
 */
public class SubtractCommand implements UndoCommand {

    private Calculator calculator;
    private double valueToAdd;

    public SubtractCommand(Calculator calculator, double valueToAdd) {
        this.calculator = calculator;
        this.valueToAdd = valueToAdd;
    }

    /**
     * 执行减法
     */
    @Override
    public boolean execute() {
        calculator.subtract(valueToAdd);
        return true;
    }

    /**
     * 撤销减法
     */
    @Override
    public void undo() {
        calculator.add(valueToAdd);
    }

}
