package com.amway.enums;

/**
 * 操作符枚举
 */
public enum OperatorEnum {

    /**
     * 加
     */
    ADD("+"),
    /**
     * 减
     */
    SUBTRACT("-"),
    /**
     * 乘
     */
    MULTIPLY("*"),
    /**
     * 除
     */
    DIVIDE("/");

    private String code;

    OperatorEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
