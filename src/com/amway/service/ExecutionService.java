package com.amway.service;

import com.amway.command.Command;

/**
 * 执行接口
 */
public interface ExecutionService {

    /**
     * 执行操作命令
     */
    boolean execute(Command command);

    /**
     * 执行undo操作
     */
    boolean undo();

    /**
     * 执行redo操作
     */
    boolean redo();

    /**
     * 是否存在undo命令
     */
    boolean hasUndoCommand();

    /**
     * 是否存在redo命令
     */
    boolean hasRedoCommand();

}
