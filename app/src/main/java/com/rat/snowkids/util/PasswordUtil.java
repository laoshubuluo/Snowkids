package com.rat.snowkids.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 加密工具
 */

public class PasswordUtil {
    private PasswordUtil() {
    }

    /**
     * MD5转换成16进制字符串需要的基础数据
     */
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 将字节数组转换成16进制字符串。
     *
     * @param b
     * @return String 16进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转换成16进制字符串。
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 根据MD5算法转换字符串
     *
     * @param oriStr 要转换的字符串
     * @return String 转换结果字符串
     * @throws NoSuchAlgorithmException 函数抛出的错误，表示不支持该种算法。
     */
    public static String getMD5Str(String oriStr)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToHexString(md.digest(oriStr.getBytes()));
    }

    /**
     * 判断判断源字符串经过MD5加密后与目标MD5加密字符串是否相等。
     *
     * @param oriStr    String 源字符串
     * @param md5String String 目标MD5加密字符串
     * @return boolean 判断结果
     * @throws NoSuchAlgorithmException 函数抛出的错误，表示不支持该种算法。
     */
    public static boolean isEqual(String oriStr, String md5String)
            throws NoSuchAlgorithmException {
        return getMD5Str(oriStr).equals(md5String);

    }
}