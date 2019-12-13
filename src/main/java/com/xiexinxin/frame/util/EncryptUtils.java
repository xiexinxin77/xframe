package com.xiexinxin.frame.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class EncryptUtils {
    private static final String default_charset = "UTF-8";
    public static byte[] encrypt(byte[] content, byte[] key, boolean md5Key, byte[] iv) {
        try {
            if (md5Key) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                key = md.digest(key);
            }

            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            IvParameterSpec ivps = new IvParameterSpec(iv);
            cipher.init(1, skeySpec, ivps);
            return cipher.doFinal(content);
        } catch (Exception var7) {
            var7.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] decrypt(byte[] content, byte[] key, boolean md5Key, byte[] iv) {
        try {
            if (md5Key) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                key = md.digest(key);
            }

            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            IvParameterSpec ivps = new IvParameterSpec(iv);
            cipher.init(2, skeySpec, ivps);
            return cipher.doFinal(content);
        } catch (Exception var7) {
            var7.printStackTrace();
            return new byte[0];
        }
    }

    private static String ensureKey(String key) {
        if (null != key && !"".equals(key)) {
            String tmpKey = key.replaceAll("[^\\x00-\\xff]", "00");
            int len = tmpKey.length();
            if (len == 16) {
                return tmpKey;
            } else if (len > 16) {
                return tmpKey.substring(0, 16);
            } else {
                for(int i = 0; i < 16 - len; ++i) {
                    tmpKey = "0" + tmpKey;
                }

                return tmpKey;
            }
        } else {
            return "We@53&es&esT*7%s";
        }
    }

    public static String encrypt(String strSrc, String key) {
        try {
            byte[] bytes = strSrc.getBytes("UTF-8");
            byte[] kbytes = "We@53&es&esT*7%s".getBytes("UTF-8");
            bytes = encrypt(bytes, kbytes, false, kbytes);
            return Base64.encodeBase64String(bytes);
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
            return "";
        }
    }

    public static String decrypt(String strSrc, String key) {
        try {
            byte[] bytes = Base64.decodeBase64(strSrc.getBytes("UTF-8"));
            byte[] kbytes = "We@53&es&esT*7%s".getBytes("UTF-8");
            bytes = decrypt(bytes, kbytes, false, kbytes);
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
            return "";
        }
    }

}
