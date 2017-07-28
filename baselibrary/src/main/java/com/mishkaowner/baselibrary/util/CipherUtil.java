package com.mishkaowner.baselibrary.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

public class CipherUtil implements ICipherUtil {
    private static Cipher encCipher = null;
    private static Cipher decCipher = null;

    @Inject
    public CipherUtil() {
    }

    @Override
    public String decode(String value, String pass) {
        String resultVal = null;
        try {
            byte[] result = Base64.decode(value, Base64.DEFAULT);
            resultVal = new String(getDecCipher(pass).doFinal(result));
        } catch (Exception e) {
        }
        return resultVal;
    }

    @Override
    public String encode(String value, String pass) {
        String resultVal = null;
        try {
            byte[] result = getEncCipher(pass).doFinal(value.getBytes("UTF-8"));
            resultVal = Base64.encodeToString(result, Base64.DEFAULT);
        } catch (Exception e) {
        }
        return resultVal;
    }

    private Cipher getEncCipher(String pass) {
        if (encCipher == null) {
            try {
                byte[] initParam = pass.substring(0, 16).getBytes("UTF-8");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
                SecretKeySpec key = new SecretKeySpec(initParam, "AES");
                encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                encCipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            } catch (Exception e) {
            }
        }
        return encCipher;
    }

    private Cipher getDecCipher(String pass) {
        if (decCipher == null) {
            try {
                byte[] initParam = pass.substring(0, 16).getBytes("UTF-8");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
                SecretKeySpec key = new SecretKeySpec(initParam, "AES");
                decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                decCipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            } catch (Exception e) {
            }
        }
        return decCipher;
    }
}
