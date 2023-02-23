package com.amway.command.impl;

import com.amway.command.UndoCommand;
import com.amway.entity.Calculator;

/**
 * 除法命令
 */
public class DivideCommand implements UndoCommand {

    private Calculator calculator;
    private double valueToAdd;

    public DivideCommand(Calculator calculator, double valueToAdd) {
        this.calculator = calculator;
        this.valueToAdd = valueToAdd;
    }

    /**
     * 执行除法
     */
    @Override
    public boolean execute() {
        calculator.divide(valueToAdd);
        return true;
    }

    /**
     * 撤销除法
     */
    @Override
    public void undo() {
        calculator.multiply(valueToAdd);
    }

}
