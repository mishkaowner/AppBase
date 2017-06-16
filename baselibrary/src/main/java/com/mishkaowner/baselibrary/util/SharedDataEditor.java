package com.mishkaowner.baselibrary.util;

import android.content.SharedPreferences;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

public class SharedDataEditor implements ISharedDataEditor {
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public SharedDataEditor() {
    }

    @Override
    public void setData(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public String getData(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public void removeData(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    @Override
    public void setSecureData(String key, String value, String password) {
        setData(key, encode(value, password));
    }

    @Override
    public String getSecureData(String key, String password) {
        String retreivedData = sharedPreferences.getString(key, "");
        return TextCompat.isEmpty(retreivedData) ? "" : decode(retreivedData, password);
    }

    private String decode(String value, String pass) {
        String resultVal = null;
        try {
            byte[] result = Base64.decode(value, Base64.DEFAULT);
            resultVal = new String(getDecCipher(pass).doFinal(result));
        }catch (Exception e) {
        }
        return resultVal;
    }

    private String encode(String value, String pass) {
        String resultVal = null;
        try {
            byte[] result = getEncCipher(pass).doFinal(value.getBytes("UTF-8"));
            resultVal = Base64.encodeToString(result, Base64.DEFAULT);
        }catch (Exception e) {
        }
        return resultVal;
    }

    private static Cipher encCipher = null;
    private Cipher getEncCipher(String pass){
        if(encCipher == null) {
            try {
                byte[] initParam = pass.substring(0, 16).getBytes("UTF-8");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
                SecretKeySpec key = new SecretKeySpec(initParam, "AES");
                encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                encCipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            }catch (Exception e) {
            }
        }
        return encCipher;
    }

    private static Cipher decCipher = null;
    private Cipher getDecCipher(String pass){
        if(decCipher == null) {
            try {
                byte[] initParam = pass.substring(0, 16).getBytes("UTF-8");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
                SecretKeySpec key = new SecretKeySpec(initParam, "AES");
                decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                decCipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            }catch (Exception e) {
            }
        }
        return decCipher;
    }
}
