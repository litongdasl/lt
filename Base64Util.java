package com.lt.jwt;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class Base64Util {
    private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private static final String charset = "utf-8";
    //解码
    public static String decode(String data) {
        try {
            if (null == data) {
                return null;
            }

            return new String(Base64.decodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，解密异常", data), e);
        }

        return null;
    }
    //编码
    public static String encode(String data) {
        try {
            if (null == data) {
                return null;
            }
            return new String(Base64.encodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，加密异常", data), e);
        }
        return null;
    }

    public static void main(String[] args) {
        String s="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiIiwidXNlcm5hbWUiOiJhZG1pbiIsInVzZXJJZCI6MSwic3ViIjoiYWRtaW4iLCJpc3MiOiIwOThmNmJjZDQ2MjFkMzczY2FkZTRlODMyNjI3YjRmNiIsImlhdCI6MTc2NDU3NDkzMywiYXVkIjoibXlhcGl1c2VyIiwiZXhwIjoxNzY0NTc4NTMzLCJuYmYiOjE3NjQ1NzQ5MzN9.Nwg0gp7rkGNgPo0vC89ys0eooLp-eLinB8oirhXrEnE";
        System.out.println(decode(s));
//        System.out.println(decode(encode(s))); //先编码，再解码——恢复原样
    }
}