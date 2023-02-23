package com.amway.enums;

/**
 * 命令枚举
 */
public enum CommandEnum {

    /**
     * 撤销
     */
    UNDO("undo"),
    /**
     * 重做
     */
    REDO("redo"),
    /**
     * 退出
     */
    QUIT("quit");

    private String code;

    CommandEnum(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}