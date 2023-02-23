package com.amway;

import com.amway.command.Command;
import com.amway.command.impl.AddCommand;
import com.amway.command.impl.DivideCommand;
import com.amway.command.impl.MultiplyCommand;
import com.amway.command.impl.SubtractCommand;
import com.amway.entity.Calculator;
import com.amway.enums.OperatorEnum;
import com.amway.service.ExecutionService;
import com.amway.service.impl.ExecutionServiceImpl;
import com.amway.utils.CalculatorUtils;

import java.util.Scanner;

public class CalculatorDemo {

    public static void main(String[] args) {
        displayHelp();
        printEnterNumberTips();

        // 初始化并获取第一个数字
        Scanner scanIn = new Scanner(System.in);
        String curInput = getNumber(scanIn);
        String preInput = curInput;

        double parsedInput = Double.parseDouble(curInput);
        Calculator calculator = new Calculator();
        ExecutionService execService = new ExecutionServiceImpl();
        execService.execute(new AddCommand(calculator, parsedInput));
        printCurrentValue(calculator);

        boolean quitApplication = false;

        while (!quitApplication) {
            System.out.print("> ");
            curInput = scanIn.next();
            if (CalculatorUtils.isQuit(curInput)) {
                quitApplication = true;
            } else if (CalculatorUtils.isNumber(preInput)) {
                // 上一个输入是数字
                if (CalculatorUtils.isUndo(curInput)) {
                    undoLastAction(execService, calculator);
                    preInput = curInput;
                } else if (CalculatorUtils.isRedo(curInput)) {
                    redoLastAction(execService, calculator);
                    preInput = curInput;
                } else if (CalculatorUtils.isOperator(curInput)) {
                    preInput = curInput;
                } else {
                    printTipsWhenPreInputIsNumber();
                }
            } else if (CalculatorUtils.isOperator(preInput)) {
                // 上一个输入是操作符
                if (CalculatorUtils.isNumber(curInput)) {
                    executeCalculate(curInput, preInput, execService, calculator);
                    preInput = curInput;
                } else {
                    printTipsWhenPreInputIsOperator();
                }
            } else if (CalculatorUtils.isUndoOrRedo(preInput)) {
                // 上一个输入是undo/redo
                if (CalculatorUtils.isUndo(curInput)) {
                    undoLastAction(execService, calculator);
                    preInput = curInput;
                } else if (CalculatorUtils.isRedo(curInput)) {
                    redoLastAction(execService, calculator);
                    preInput = curInput;
                } else if (CalculatorUtils.isOperator(curInput)) {
                    preInput = curInput;
                } else {
                    printTipsWhenPreInputIsUndoOrRedo();
                }
            } else {
                printTipsWhenInputIllegal();
            }
        }

        System.out.println("Exiting application...");
        scanIn.close();
    }

    private static void executeCalculate(String curInput, String preInput,
                                         ExecutionService execService, Calculator calculator) {
        execService.execute(getCommand(curInput, preInput, calculator));
        printCurrentValue(calculator);
    }

    private static Command getCommand(String curInput, String preInput, Calculator calculator) {
        double parsedInput = Double.parseDouble(curInput);
        if (OperatorEnum.ADD.getCode().equals(preInput)) {
            return new AddCommand(calculator, parsedInput);
        } else if (OperatorEnum.SUBTRACT.getCode().equals(preInput)) {
            return new SubtractCommand(calculator, parsedInput);
        } else if (OperatorEnum.MULTIPLY.getCode().equals(preInput)) {
            return new MultiplyCommand(calculator, parsedInput);
        } else {
            return new DivideCommand(calculator, parsedInput);
        }
    }

    private static void printTipsWhenInputIllegal() {
        System.out.println("Illegal input");
        displayHelp();
    }

    private static void printTipsWhenPreInputIsUndoOrRedo() {
        System.out.println("Illegal input");
        System.out.println("Please enter an operator or undo/redo operation.");
        System.out.print("> ");
    }

    private static void printTipsWhenPreInputIsOperator() {
        System.out.println("Illegal input");
        System.out.println("Please enter a number.");
    }

    private static void printTipsWhenPreInputIsNumber() {
        System.out.println("Illegal input");
        System.out.println("Please enter an operator or undo/redo operation.");
    }

    private static String getNumber(Scanner scanIn) {
        String number = scanIn.next();
        while (!CalculatorUtils.isNumber(number)) {
            if (CalculatorUtils.isQuit(number)) {
                System.exit(0);
            }
            printEnterNumberTips();
            number = scanIn.next();
        }
        return number;
    }

    private static void printEnterNumberTips() {
        System.out.println("Please enter number");
        System.out.print("> ");
    }


    private static void redoLastAction(ExecutionService execService, Calculator calculator) {
        if (execService.hasRedoCommand()) {
            execService.redo();
            printCurrentValue(calculator);
        } else {
            System.out.println("Redo stack is empty");
        }
    }

    private static void undoLastAction(ExecutionService execService, Calculator calculator) {
        if (execService.hasUndoCommand()) {
            execService.undo();
            printCurrentValue(calculator);
        } else {
            System.out.println("Undo stack is empty");
        }
    }

    private static void printCurrentValue(Calculator calculator) {
        System.out.println(calculator.getCurrentValue());
    }

    private static void displayHelp() {
        System.out.println("Enter a number or commands below:");
        displayCommand("undo", "Undo the last action");
        displayCommand("redo", "Redo the last action");
        displayCommand("quit", "Exit the application");
    }

    private static void displayCommand(String command, String commandDescription) {
        System.out.println("\t" + command + "\t\t" + commandDescription);
    }
}
