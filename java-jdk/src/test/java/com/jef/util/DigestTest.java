package com.jef.util;

import com.jef.util.security.Digests;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jef
 * @date 2021/9/30
 */
public class DigestTest {

    @Test
    public void getDigestString() throws NoSuchAlgorithmException {
        String str = "jef:123456";
        System.out.println(Digests.getSha1Method2(str));
    }

    @Test
    public void testAll() throws Exception {
        //加密内容
        final String content = "jef:123456";
        /*
         * 单向加密 md5 & sha
         */
        //md5 加密
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] md5SecretStr = md5.digest(content.getBytes());
        System.out.print("md5 加密 : { " + new String(Base64.encodeBase64(md5SecretStr)) + " }\n\r");

        //sha 加密
        MessageDigest sha = MessageDigest.getInstance("sha");
        byte[] shaSecretBytes = sha.digest(content.getBytes());
        System.out.print("sha 加密 : { " + new String(Base64.encodeBase64(shaSecretBytes)) + " }\n\r");

        /*
         * 对称加密 aes & des
         */
        //aes 加密
        KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("aes");
        SecretKey aesSecretKey = aesKeyGenerator.generateKey();
        Cipher aesCipher = Cipher.getInstance("aes");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesSecretKey);
        byte[] aseResultBytes = aesCipher.doFinal(content.getBytes());
        System.out.print("aes 加密 : { " + new String(Base64.encodeBase64(aseResultBytes)) + " }\n\r");

        //aes 解密
        aesCipher.init(Cipher.DECRYPT_MODE, aesSecretKey);
        aseResultBytes = aesCipher.doFinal(aseResultBytes);
        System.out.print("aes 解密: { " + new String(aseResultBytes) + " }\n\r");


        //des 加密
        KeyGenerator desKeyGenerator = KeyGenerator.getInstance("aes");
        SecretKey desSecretKey = desKeyGenerator.generateKey();
        Cipher desCipher = Cipher.getInstance("aes");
        desCipher.init(Cipher.ENCRYPT_MODE, desSecretKey);
        byte[] dseResultBytes = desCipher.doFinal(content.getBytes());
        System.out.print("des 加密 : { " + new String(Base64.encodeBase64(dseResultBytes)) + " }\n\r");

        desCipher.init(Cipher.DECRYPT_MODE, desSecretKey);
        dseResultBytes = desCipher.doFinal(dseResultBytes);
        System.out.print("des 解密: { " + new String(dseResultBytes) + " }\n\r");

    }

}