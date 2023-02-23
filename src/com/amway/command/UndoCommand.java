package com.amway.command;

/**
 * 撤销命令
 */
public interface UndoCommand extends Command {

    /**
     * 撤销方法
     */
    void undo();
}
