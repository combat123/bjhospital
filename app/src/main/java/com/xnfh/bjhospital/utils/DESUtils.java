package com.xnfh.bjhospital.utils;

import android.text.TextUtils;


import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DESUtils {

    private static String password = "4639F23A";
    private static String ivKey = "227ED732";


    public static String encrypt(String originalStr) {
        String result = null;
        byte[] tmpOriginalStr;

        if (!TextUtils.isEmpty(originalStr)) {
            try {
                tmpOriginalStr = originalStr.getBytes("utf-8");
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                DESKeySpec dks = new DESKeySpec(password.getBytes());
                SecretKey secretKey = keyFactory.generateSecret(dks);
                IvParameterSpec param = new IvParameterSpec(ivKey.getBytes());
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);
                byte[] tmpEncypt = cipher.doFinal(tmpOriginalStr);
                if (tmpEncypt != null) {
                    result = Base64Utils.encode(tmpEncypt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }




    public static String decrypt(String encrypStr) {
        String result = null;
        byte[] tmpEncrypStr ;
        try {
            if (!TextUtils.isEmpty(encrypStr)) {
                tmpEncrypStr = Base64Utils.decode(encrypStr);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                DESKeySpec dks = new DESKeySpec(password.getBytes());
                SecretKey secretKey = keyFactory.generateSecret(dks);
                IvParameterSpec param = new IvParameterSpec(ivKey.getBytes());
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, param);
                byte[] tmpDecrypt = cipher.doFinal(tmpEncrypStr);
                if (tmpDecrypt != null) {
                    result = new String(tmpDecrypt, "utf-8");
                }
            }
        } catch (Exception e) {        }
        return result;
    }



}