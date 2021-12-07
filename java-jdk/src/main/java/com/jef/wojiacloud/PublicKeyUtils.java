package com.jef.wojiacloud;

import com.jef.util.security.Base64Util;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端公钥
 */
public class PublicKeyUtils {

	private static final String CHARACTER_ENCODING = "UTF-8";
	private static Map keys = new HashMap();

	/**
	 * 1 获取公钥接口   api/users/public_key   GET请求
	 */
	private static PublicKey public_key(String api_address, String api_version, String appKey, String appSecret, long clientTime) throws Exception {
    	
    	/*
	 	参数	说明	类型	是否必须
	    appKey	App Key	String	Y 我家云管理员获取
	    clientTime	访问时间毫秒数	Int	Y在30分钟内有效
	    version	客户端版本	String	Y V1.0
	    signature	签名串	String	Y */

		//签名
		String signature = SignatureUtils.signature(appKey, appSecret, clientTime, api_version);
		System.out.println(signature);

		String url = api_address + "/api/users/public_key?appKey=" + appKey +
				"&clientTime=" + clientTime + "&version=" + api_version + "&signature=" + signature;
		System.out.println(url);

		JSONObject obj = sendGetRequest(url);
		System.out.println(obj);

		//生成密钥
		if (obj != null) {
    		/*
    		   "data":{                        
	              "m": "61258";                 
	              "e": "nrb8ffrgsx9cy40wfywv6bpagkomkgfb";                        
    			}
    		 */
			JSONObject data = JSONObject.fromObject(obj.get("data"));
			String modulus = data.get("m") != null ? data.getString("m") : null;
			String exponent = data.get("e") != null ? data.getString("e") : null;

			return getPublicKey(modulus, exponent);

		}

		return null;
	}


	/**
	 * 跟据m\e生成公钥
	 *
	 * @param m
	 * @param e
	 */
	private static PublicKey getPublicKey(String modulus, String exponent) throws Exception {

		String module16 = new BigInteger(modulus, 16).toString();
		String empoent16 = new BigInteger(exponent, 16).toString();

		BigInteger m = new BigInteger(module16);
		BigInteger e = new BigInteger(empoent16);

		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		return publicKey;

	}

	//加密
	public static String encrypt(String api_address, String api_version, String appKey, String appSecret, long clientTime, String password) throws Exception {

		PublicKey publicKey = null;
		if (keys.containsKey(appKey)) {
			publicKey = (PublicKey) keys.get(appKey);
		} else {
			publicKey = public_key(api_address, appSecret, appKey, appSecret, clientTime);
			if (publicKey == null) {
				return null;
			}
		}

		byte[] en_test = encrypt(publicKey, password.getBytes("ISO-8859-1"));
		String result = new String(en_test, "ISO-8859-1");
		//System.out.println(result);

		String base64 = Base64Util.getBase64(result);
		//System.out.println(base64);

		return base64;
	}

	//算法
	private static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
					: data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i
							* outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i
							* blockSize, raw, i * outputSize);
				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}


	//GET请求
	private static JSONObject sendGetRequest(String url) throws Exception {

		HttpGet post = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String responseStr = EntityUtils.toString(response.getEntity(), CHARACTER_ENCODING);

				String status = JSONObject.fromObject(responseStr).getString("result");
				if ("success".equals(status)) {
					return JSONObject.fromObject(responseStr);
				}

			}
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}
}
