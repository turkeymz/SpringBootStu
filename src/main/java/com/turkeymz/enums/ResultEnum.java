package com.turkeymz.enums;

import lombok.Getter;

/**
 * 返回异常
 * Created by trukeymz on 2017/7/24.
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数不正确"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    ResultEnum() {
    }
}
