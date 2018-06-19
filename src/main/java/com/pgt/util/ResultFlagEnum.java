package com.pgt.util;

/**
 * Created by CWB on 2016/10/10.
 */
public enum ResultFlagEnum {
    SUCCESS("200"), //成功
    FAILED("500"),//程序、服务器异常
    OTHEREXCEPTION("999");//假象判断

    private String value;

    ResultFlagEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ResultFlagEnum getEnum(String code){
        switch (code){
            case "200":
                return SUCCESS;
            case "500":
                return FAILED;
            default:
                return OTHEREXCEPTION;
        }

    }

}
