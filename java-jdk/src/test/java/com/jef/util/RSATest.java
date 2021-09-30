package com.jef.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author Jef
 * @date 2021/9/30
 */
public class RSATest {

    @Test
    public void testRas() throws Exception {
        String context = "jef:123456";

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("rsa");
        KeyPair gkp = kpg.generateKeyPair();

        PrivateKey privateKey = gkp.getPrivate();
        PublicKey publicKey = gkp.getPublic();
        byte[] encode1 = privateKey.getEncoded();
        byte[] encode2 = publicKey.getEncoded();

        byte[] key1 = Base64.encodeBase64(encode1);
        byte[] key2 = Base64.encodeBase64(encode2);
        System.out.println(new String(key1));
        System.out.println(new String(key2));
        /*System.out.println("privateKey:{"+privateKey+"}");
        System.out.println("publicKey:{" +publicKey+"}");*/


        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(1, privateKey);
        byte[] result = cipher.doFinal(context.getBytes());

        byte[] signatrue = Base64.encodeBase64(result);

        System.out.println("result:{" + new String(signatrue) + "}");

        byte[] i = Base64.decodeBase64(signatrue);
        cipher.init(2, publicKey);
        System.out.println(new String(cipher.doFinal(i)));

    }

}
