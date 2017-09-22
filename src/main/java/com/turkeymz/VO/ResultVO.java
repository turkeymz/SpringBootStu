package com.turkeymz.VO;

import lombok.Data;

/**
 * http请求返回最外层对象
 * Created by trukeymz on 2017/7/24.
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    /** 错误码 **/
    private Integer code;
    /** 提示信息 **/
    private String msg;
    /** 具体内容 **/
    private T Data;
}
