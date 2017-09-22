package com.turkeymz.util;

import java.util.Random;

/**
 * Created by trukeymz on 2017/7/24.
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
