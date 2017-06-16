package com.mishkaowner.appbase;

import android.util.Base64;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void encryptionTest() throws Exception {
        String testValue = "";
        String resultValue = "";
        try {
            String pass = "";
            byte[] initParam = pass.getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            SecretKeySpec key = new SecretKeySpec(pass.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            InputStream is = new ByteArrayInputStream(testValue.getBytes("UTF-8"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherInputStream cis = new CipherInputStream(is, cipher);
            IOUtils.copy(cis, outputStream);
            resultValue = outputStream.toString("UTF-8");
            cis.close();
            is.close();
            outputStream.close();
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("REsult " + resultValue);
        assertEquals("", resultValue);
    }

    @Test
    public void encryptionTest2() throws Exception {
        String testValue = "";
        byte[] resultValue = encrypt(testValue);
        System.out.println("REsult " + resultValue);
        String result2 = decrypt(resultValue);
        System.out.println("REsult " + result2);
        assertEquals("", result2);
    }

    public byte[] encrypt(String original) {
        try {
            String pass = "";
            byte[] initParam = pass.getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            SecretKeySpec key = new SecretKeySpec(pass.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

            byte[] result = cipher.doFinal(original.getBytes("UTF-8"));
            String resultVal = Base64.encodeToString(result, Base64.DEFAULT);
            System.out.println(resultVal);
            return result;
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String decrypt(byte[]  original) {
        String resultValue = "";
        try {
            String pass = "1234567890123456";
            byte[] initParam = pass.getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            SecretKeySpec key = new SecretKeySpec(pass.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
           resultValue = new String(cipher.doFinal(original));
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return resultValue;
    }
}