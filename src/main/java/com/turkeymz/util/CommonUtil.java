package com.turkeymz.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.UUID;


public class CommonUtil {

    private static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
    private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    private static final int KEY_SIZE = 128;
    private static final int ITERATION_COUNT = 1;
    private static final AesUtil aesUtil = new AesUtil(KEY_SIZE, ITERATION_COUNT);

    public static String getIV(){
        return IV;
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {

        return aesUtil.encrypt(SALT, IV, password, content);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
        return aesUtil.decrypt(SALT, IV, password, content);
    }

    /**
     * 随机数产生
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isNull(Object o) {
        if (o == null || o.equals("") || StringUtils.isBlank("" + o) || o.equals("null") || o.equals("undefined")) {
            return true;
        } else {
            return false;
        }
    }
}
