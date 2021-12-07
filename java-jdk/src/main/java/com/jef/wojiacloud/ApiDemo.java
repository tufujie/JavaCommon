package com.jef.wojiacloud;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 我家云API接口demo
 */
public class ApiDemo {

	//正式地址api.wojiacloud.com 测试地址 http://mhyy.kingdee.com
//	public static final String api_address = "http://api.wojiacloud.com"; 
	public static final String api_address = "http://mhyy.kingdee.com";

	public static final String api_version = "v1.0";

	public static final String CHARACTER_ENCODING = "UTF-8";


	//测试
	public static void main(String[] args) throws Exception {

		//我家云系统个管理员 在系统管理-应用管理创建
		String appKey = "";
		String appSecret = "";
		//账号密码，咨询我家云企业管理员
		String username = "";

		long clientTime = new Date().getTime(); //毫秒

		//测试ticket接口
		String ticket = ticket(appKey, appSecret, clientTime);
		System.out.println("ticket=" + ticket);

		//获取所有我家云已经开通服务的小区 /api/projects/list
		JSONObject data = projects_list(ticket);
		System.out.println("项目列表=" + data);

		//住户所有应用接口 /api/menus/cu_list
		String pid = "eafc07e9962d4c649ca1f708d80f81aa";
		data = cu_list(ticket, pid);
		System.out.println("项目下菜单=" + data);

		//测试access_token接口
		String type = "2"; //1 住户  2 物业管家
		String access_token = access_token(ticket, username, type, pid);
		System.out.println("授权码=" + access_token);

	}


	/**
	 * 2 授权接口 api/users/access_token   GET请求
	 */
	public static String ticket(String appKey, String appSecret, long clientTime) throws Exception {
    	
    	/*
	 	参数	说明	类型	是否必须
	    appKey	App Key	String	Y 我家云管理员获取
	    clientTime	访问时间毫秒数	Int	Y在30分钟内有效
	    version	客户端版本	String	Y V1.0
	    signature	签名串	String	Y */


		//签名
		String signature = SignatureUtils.signature(appKey, appSecret, clientTime, api_version);
		System.out.println(signature);

		String url = api_address + "/api/users/ticket?appKey=" + appKey + "&clientTime=" + clientTime + "&version=" + api_version + "&signature=" + signature;
		System.out.println(url);

		//发送请求
		JSONObject obj = sendGetRequest(url);
		System.out.println(obj);

		if (obj != null) {

			String status = obj.getString("result");
			if ("success".equals(status)) {
				//System.out.println( obj.get("data") );

				JSONObject data = obj.getJSONObject("data");

				return (String) data.get("ticket");
			}
		}

		return null;
	}

	/**
	 * 2 授权接口 api/users/access_token   POST请求
	 */
	public static String access_token(String ticket, String username, String type, String pid) throws Exception {


		String url = api_address + "/api/users/access_token";
		System.out.println(url);

		//建立一个NameValuePair数组，用于存储欲传递的参数
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		//添加参数
		params.add(new BasicNameValuePair("ticket", ticket));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("pid", pid));

		//发送请求
		JSONObject obj = sendPostRequest(url, params);
		System.out.println(obj);

		if (obj != null) {

			String status = obj.getString("result");
			if ("success".equals(status)) {
				//System.out.println( obj.get("data") );
				JSONObject data = obj.getJSONObject("data");

				return (String) data.get("access_token");
			}
		}

		return null;
	}

	/**
	 * 3 授权接口 api/users/access_token   GET请求
	 */
	public static JSONObject cu_list(String ticket, String pid) throws Exception {


		String url = api_address + "/api/menus/cu_list?ticket=" + ticket + "&pid=" + pid;
		System.out.println(url);

		//发送请求
		JSONObject obj = sendGetRequest(url);
		System.out.println(obj);

		if (obj != null) {

			String status = obj.getString("result");
			if ("success".equals(status)) {
				//System.out.println( obj.get("data") );

				JSONObject data = obj.getJSONObject("data");

				return data;
			}
		}

		return null;
	}


	/**
	 * 3 授权接口 api/users/access_token   GET请求
	 */
	public static JSONObject projects_list(String ticket) throws Exception {


		String url = api_address + "/api/projects/list?ticket=" + ticket;
		System.out.println(url);

		//发送请求
		JSONObject obj = sendGetRequest(url);
		System.out.println(obj);

		if (obj != null) {

			String status = obj.getString("result");
			if ("success".equals(status)) {
				//System.out.println( obj.get("data") );

				JSONObject data = obj.getJSONObject("data");

				return data;
			}
		}

		return null;
	}


	/**
	 * 3 提交工单接口 api/workorders/add    POST请求
	 */
	public static void workorders_add(String access_token) throws Exception {

		String url = api_address + "/api/workorders/add";
		System.out.println(url);


		//建立一个NameValuePair数组，用于存储欲传递的参数
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		//必须参数
		params.add(new BasicNameValuePair("access_token", access_token));
		//其他参数


		//发送请求
		JSONObject obj = sendPostRequest(url, params);
		System.out.println(obj);

		if (obj != null) {

			String status = obj.getString("result");
			if ("success".equals(status)) {
				//System.out.println( obj.get("data") );

			}
		}

	}


	//POST请求
	public static JSONObject sendPostRequest(String url, List<BasicNameValuePair> params) throws Exception {

		HttpPost post = new HttpPost(url);

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, CHARACTER_ENCODING);
		post.setEntity(entity);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String responseStr = EntityUtils.toString(response.getEntity(), CHARACTER_ENCODING);

				return JSONObject.fromObject(responseStr);
			}
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}

	//GET请求
	public static JSONObject sendGetRequest(String url) throws Exception {

		HttpGet post = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String responseStr = EntityUtils.toString(response.getEntity(), CHARACTER_ENCODING);
				return JSONObject.fromObject(responseStr);

			}
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}
}
