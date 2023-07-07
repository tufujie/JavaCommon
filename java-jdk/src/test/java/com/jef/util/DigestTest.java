package com.jef.util;

import com.jef.util.security.Digests;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jef
 * @date 2021/9/30
 */
public class DigestTest {

    // 加密内容
    public static final String CONTENT = "jef:123456";

    @Test
    public void getDigestString() {
        System.out.println(Digests.getSha1Method2(CONTENT));
    }

    @Test
    public void testAll() throws Exception {
        /*
         * md5 & sha
         * 单向加密 摘要算法
         */
        //md5 加密
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] md5SecretStr = md5.digest(CONTENT.getBytes());
        System.out.print("md5 加密 : { " + new String(Base64.encodeBase64(md5SecretStr)) + " }\n\r");

        //sha 加密
        MessageDigest sha = MessageDigest.getInstance("sha");
        byte[] shaSecretBytes = sha.digest(CONTENT.getBytes());
        System.out.print("sha 加密 : { " + new String(Base64.encodeBase64(shaSecretBytes)) + " }\n\r");


    }

    /**
     * aes
     * 对称加密，加密和解密密钥相同
     *
     * @author Jef
     * @date 2023/7/7
     */
    @Test
    public void testAes() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //aes 加密
        KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("aes");
        SecretKey aesSecretKey = aesKeyGenerator.generateKey();
        Cipher aesCipher = Cipher.getInstance("aes");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesSecretKey);
        byte[] aseResultBytes = aesCipher.doFinal(CONTENT.getBytes());
        System.out.print("aes 加密 : { " + new String(Base64.encodeBase64(aseResultBytes)) + " }\n\r");

        //aes 解密
        aesCipher.init(Cipher.DECRYPT_MODE, aesSecretKey);
        aseResultBytes = aesCipher.doFinal(aseResultBytes);
        System.out.print("aes 解密: { " + new String(aseResultBytes) + " }\n\r");
    }

    /**
     * des
     * 对称加密，加密和解密密钥相同
     *
     * @author Jef
     * @date 2023/7/7
     */
    @Test
    public void testDes() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //des 加密
        KeyGenerator desKeyGenerator = KeyGenerator.getInstance("des");
        SecretKey desSecretKey = desKeyGenerator.generateKey();
        Cipher desCipher = Cipher.getInstance("des");
        desCipher.init(Cipher.ENCRYPT_MODE, desSecretKey);
        byte[] dseResultBytes = desCipher.doFinal(CONTENT.getBytes());
        System.out.print("des 加密 : { " + new String(Base64.encodeBase64(dseResultBytes)) + " }\n\r");

        desCipher.init(Cipher.DECRYPT_MODE, desSecretKey);
        dseResultBytes = desCipher.doFinal(dseResultBytes);
        System.out.print("des 解密: { " + new String(dseResultBytes) + " }\n\r");

    }

}