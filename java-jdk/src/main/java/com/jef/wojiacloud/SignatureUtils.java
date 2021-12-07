package com.jef.wojiacloud;

/**
 * 客户端签名
 */
public class SignatureUtils {

	/**
	 * 待加密串为该次请求中的appKey、clientTime、version，以&连接的字符串，按参数的字母先后顺序排序，再连接appSecret
	 * 如:
	 * appKey=ap119jaz6b6te84v&clientTime=1465180217306&version=V1.0&yhegf6ehllfegayh3bem6l3gvswp2r50
	 * 得到和字符串经MD5加密, 最后再进行SHA1加密，得到signature
	 */
	public static String signature(String appKey, String appSecret, long clientTime, String version) throws Exception {
		//String str = "appKey="+appKey+"&clientTime="+clientTime+"&version="+version+"&"+appSecret;

		String pathString = "appKey=" + appKey + "&clientTime=" + clientTime + "&version=" + version + "&" + appSecret;
		String data = MD5Utils.getMD5Code(pathString); // MD5加密
		String digest = new SHA1().getDigestOfString(data.getBytes());

		return digest;
	}

}
