package com.amway.utils;


import com.amway.enums.CommandEnum;

import java.util.Arrays;
import java.util.List;

/**
 * 工具类
 */
public class CalculatorUtils {

    private static final List<String> OPERATOR_LIST = Arrays.asList("+", "-", "*", "/");

    /**
     * 判断字符串是否数字
     */
    public static boolean isNumber(String numberStr) {
        try {
            Double.parseDouble(numberStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否为操作符
     */
    public static boolean isOperator(String str) {
        return OPERATOR_LIST.contains(str);
    }

    /**
     * 是否undo或redo操作
     */
    public static boolean isUndoOrRedo(String command) {
        return CommandEnum.REDO.getCode().equals(command) || CommandEnum.UNDO.getCode().equals(command);
    }

    /**
     * 判断是否执行undo
     */
    public static boolean isUndo(String command) {
        return CommandEnum.UNDO.getCode().equals(command);
    }

    /**
     * 判断是否执行redo
     */
    public static boolean isRedo(String command) {
        return CommandEnum.REDO.getCode().equals(command);
    }

    /**
     * 判断是否执行quit
     */
    public static boolean isQuit(String command) {
        return CommandEnum.QUIT.getCode().equals(command);
    }
}
