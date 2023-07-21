package com.jef.encryption.m2;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.math.BigInteger;

/**
 * 解密
 *
 * @author Jef
 * @date 2023/7/10
 */
public class Verifier {

    private static byte[] encodeDERSignature(byte[] signature) throws Exception {
        byte[] r = new byte[32];
        byte[] s = new byte[32];

        System.arraycopy(signature, 0, r, 0, 32);
        System.arraycopy(signature, 32, s, 0, 32);

        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(new ASN1Integer(new BigInteger(1, r)));
        vector.add(new ASN1Integer(new BigInteger(1, s)));

        try {
            return (new DERSequence(vector)).getEncoded();
        } catch (IOException var6) {
            throw new Exception();
        }
    }

    private static ECDomainParameters getECDomainParameters() {
        ECParameterSpec spec = ECNamedCurveTable.getParameterSpec("sm2p256v1");
        return new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN(), spec.getH(), spec.getSeed());
    }

    private static ECPublicKeyParameters encodePublicKey(byte[] value) {
        byte[] x = new byte[32];
        byte[] y = new byte[32];
        System.arraycopy(value, 1, x, 0, 32);
        System.arraycopy(value, 33, y, 0, 32);
        BigInteger X = new BigInteger(1, x);
        BigInteger Y = new BigInteger(1, y);
        ECPoint Q = ECNamedCurveTable.getParameterSpec("sm2p256v1").getCurve().createPoint(X, Y);
        return new ECPublicKeyParameters(Q, getECDomainParameters());
    }

    public static boolean verify(byte[] pubkey, byte[] msg, byte[] signature) throws Exception {
        // 因为这里传入的是 Hex.decode(PUBLIC_KEY)
        ECPublicKeyParameters publicKey = encodePublicKey(pubkey);
        SM2Signer signer = new SM2Signer();
        ParametersWithID parameters = new ParametersWithID(publicKey, "1234567812345678".getBytes());
        signer.init(false, parameters);
        signer.update(msg, 0, msg.length);
        byte[] signtureEncode = encodeDERSignature(signature);
        return signer.verifySignature(signtureEncode);
    }

    public static void main(String[] args) throws Exception {
        // 私钥加密
        String privateKey = Constants.PRIVATE_KEY;
        String message = Constants.MESSAGE;
        String signature = Signer.sign(privateKey, message);
        System.out.println("signature:" + signature);

        // 公钥解密
        String publicKey = Constants.PUBLIC_KEY;
        byte[] signatureValue = Hex.decode(signature);
        boolean verify = verify(Hex.decode(publicKey), message.getBytes(), signatureValue);
        System.out.println("verified: " + verify);
    }


}