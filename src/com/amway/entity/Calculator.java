package com.amway.entity;

/**
 * 计算器
 */
public class Calculator {

    /**
     * 当前计算器展示的值
     */
    private double currentValue = 0;

    public double getCurrentValue() {
        return currentValue;
    }

    public void add(double value) {
        currentValue += value;
    }

    public void subtract(double value) {
        currentValue -= value;
    }

    public void divide(double value) {
        currentValue /= value;
    }

    public void multiply(double value) {
        currentValue *= value;
    }

    @Override
    public String toString() {
        return "curValue:" + currentValue;
    }
}
