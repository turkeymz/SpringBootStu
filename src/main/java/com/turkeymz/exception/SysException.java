package com.turkeymz.exception;

import com.turkeymz.enums.ResultEnum;

/**
 * 异常处理
 * Created by trukeymz on 2017/7/24.
 */
public class SysException extends RuntimeException{

    private Integer code;

    public SysException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public SysException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
