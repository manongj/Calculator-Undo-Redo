package com.amway.service.impl;

import com.amway.command.Command;
import com.amway.command.UndoCommand;
import com.amway.service.ExecutionService;

import java.util.Stack;

/**
 * 执行方法实现
 */
public class ExecutionServiceImpl implements ExecutionService {
    private Stack<UndoCommand> undoStack = new Stack<>();
    private Stack<UndoCommand> redoStack = new Stack<>();

    @Override
    public boolean execute(Command command) {
        if (command instanceof UndoCommand) {
            undoStack.push((UndoCommand) command);
        }

        if (command.execute()) {
            redoStack.clear();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }

        UndoCommand command = undoStack.pop();
        if (command != null) {
            redoStack.push(command);
            command.undo();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }

        UndoCommand command = redoStack.pop();
        if (command != null) {
            undoStack.push(command);
            command.execute();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasUndoCommand() {
        return !undoStack.isEmpty();
    }

    @Override
    public boolean hasRedoCommand() {
        return !redoStack.isEmpty();
    }

    @Override
    public String toString() {
        return "undo size:" + undoStack.size() + ", redo size:" + redoStack.size();
    }

}
