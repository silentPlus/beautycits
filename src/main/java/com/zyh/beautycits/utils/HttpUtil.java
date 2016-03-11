package com.zyh.beautycits.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyh.beautycits.exception.MyException;

public final class HttpUtil {

	private static String isrsa = "0"; 

	private static final Log log = LogFactory.getLog(HttpUtil.class);

	public static final Map<Integer,String> HTTP_RETURN_CODE = new HashMap<Integer,String>();

	static{
		HTTP_RETURN_CODE.put(100, "Continue");
		HTTP_RETURN_CODE.put(101, "Switching Protocols");
		HTTP_RETURN_CODE.put(102, "Processing");

		HTTP_RETURN_CODE.put(200, "OK");
		HTTP_RETURN_CODE.put(201, "Created");
		HTTP_RETURN_CODE.put(202, "Accepted");
		HTTP_RETURN_CODE.put(203, "Non-Authoritative Information");
		HTTP_RETURN_CODE.put(204, "No Content");
		HTTP_RETURN_CODE.put(205, "Reset Content");
		HTTP_RETURN_CODE.put(206, "Partial Content");
		HTTP_RETURN_CODE.put(207, "Multi-Status");

		HTTP_RETURN_CODE.put(300, "Multiple Choices");
		HTTP_RETURN_CODE.put(301, "Moved Permanently");
		HTTP_RETURN_CODE.put(302, "Move temporarily");
		HTTP_RETURN_CODE.put(303, "See Other");
		HTTP_RETURN_CODE.put(304, "Not Modified");
		HTTP_RETURN_CODE.put(305, "Use Proxy");
		HTTP_RETURN_CODE.put(306, "Switch Proxy");
		HTTP_RETURN_CODE.put(307, "Temporary Redirect");

		HTTP_RETURN_CODE.put(400, "Bad Request");
		HTTP_RETURN_CODE.put(401, "Unauthorized");
		HTTP_RETURN_CODE.put(402, "Payment Required");
		HTTP_RETURN_CODE.put(403, "Forbidden");
		HTTP_RETURN_CODE.put(404, "Not Found");
		HTTP_RETURN_CODE.put(405, "Method Not Allowed");
		HTTP_RETURN_CODE.put(406, "Not Acceptable");
		HTTP_RETURN_CODE.put(407, "Proxy Authentication Required");
		HTTP_RETURN_CODE.put(408, "Request Timeout");
		HTTP_RETURN_CODE.put(409, "Conflict");
		HTTP_RETURN_CODE.put(410, "Gone");
		HTTP_RETURN_CODE.put(411, "Length Required");
		HTTP_RETURN_CODE.put(412, "Precondition Failed");
		HTTP_RETURN_CODE.put(413, "Request Entity Too Large");
		HTTP_RETURN_CODE.put(414, "Request-URI Too Long");
		HTTP_RETURN_CODE.put(415, "Unsupported Media Type");
		HTTP_RETURN_CODE.put(416, "Requested Range Not Satisfiable");
		HTTP_RETURN_CODE.put(417, "Expectation Failed");
		HTTP_RETURN_CODE.put(421, "There are too many connections from your internet address");
		HTTP_RETURN_CODE.put(422, "Unprocessable Entity");
		HTTP_RETURN_CODE.put(423, "Locked");
		HTTP_RETURN_CODE.put(424, "Failed Dependency");
		HTTP_RETURN_CODE.put(425, "Unordered Collection");
		HTTP_RETURN_CODE.put(426, "Upgrade Required");
		HTTP_RETURN_CODE.put(449, "Retry With");

		HTTP_RETURN_CODE.put(500, "Internal Server Error");
		HTTP_RETURN_CODE.put(501, "Not Implemented");
		HTTP_RETURN_CODE.put(502, "Bad Gateway");
		HTTP_RETURN_CODE.put(503, "Service Unavailable");
		HTTP_RETURN_CODE.put(504, "Gateway Timeout");
		HTTP_RETURN_CODE.put(505, "HTTP Version Not Supported");
		HTTP_RETURN_CODE.put(506, "Variant Also Negotiates");
		HTTP_RETURN_CODE.put(507, "Insufficient Storage");
		HTTP_RETURN_CODE.put(509, "Bandwidth Limit Exceeded");
		HTTP_RETURN_CODE.put(510, "Not Extended");

		HTTP_RETURN_CODE.put(600, "Unparseable Response Headers");
	}

	public static String getBcUrl(String url, Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		if ((params != null) && (params.size() > 0)) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		sb.trimToSize();
		return url + "?" +  sb.toString();
	}

	public static String http(String url, Map<String, String> params) throws Exception {
		URL u = null;
		HttpURLConnection con = null;
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if ((params != null) && (params.size() > 0)) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		sb.trimToSize();
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded; text/html; charset=utf-8");
			con.setRequestProperty("isrsa", isrsa);
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "GBK");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		}  finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
			buffer.append("\n");
		}
		buffer.trimToSize();
		return buffer.toString();
	}

	private static byte[] inputStream2Byte(InputStream input) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		while ((len = input.read(data, 0, data.length)) != -1) {
			baos.write(data, 0, len);
		}
		byte[] buffer = baos.toByteArray();
		return buffer;
    }

	private static String urlTransCodeing(String url){
		return url.replace("\r\n", "").replace("\"", "%22").replace("{", "%7b").replace("}", "%7d").replace("|", "%124"); //.replace("&", "%26")
	}

	private static CloseableHttpClient getSslHttpClient() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		}};
		SSLContext sc = SSLContext.getInstance("TLSv1");
//		SSLContext sc = SSLContexts.custom().useTLS().build();
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
        SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(sc, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(30000).build();
        CloseableHttpClient httpclient  = HttpClients.custom().setSSLSocketFactory(scsf).setDefaultRequestConfig(config).build();
        return httpclient;
	}


	public static String doGet(String url, Map<String, String> params) throws Exception {
		if (StringUtils.isBlank(url)) {
			throw new MyException("URL请求地址为空!");
		}
		long l = System.currentTimeMillis();
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					pairs.add(new BasicNameValuePair(entry.getKey(), value));
				}
			}
			url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs,"UTF-8"));
		}
		url = urlTransCodeing(url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("isrsa", isrsa);
        CloseableHttpClient httpClient = getSslHttpClient();
        CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
	        int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
            	httpGet.abort();
            	log.error("Get失败请求耗时" + (System.currentTimeMillis() - l) + "毫秒, url:" + url + ",请求数据:" +params +",状态码:"+statusCode+",描述："+HTTP_RETURN_CODE.get(statusCode));
                throw new MyException("HttpUtils->Get," + "url:" + url +",error status code:" + statusCode);
            }
	        HttpEntity entity = response.getEntity();
	        String result  = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity); //关闭HttpEntity的流
            log.info("Get耗时" + (System.currentTimeMillis() - l) + "毫秒, url:" + url + ",result:" + result);
            return result;
		} finally {
			response.close();
			httpClient.close();
		}
	}


	public static String doGet(String url) throws Exception {
		return doGet(url, null);
	}


	/**
	 *
	 * getInputStream:(读取URL对应数据流). <br/>
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static InputStream getInputStream(String url) throws Exception {
		if (StringUtils.isBlank(url)) {
			throw new MyException("URL请求地址为空!");
		}
		url = urlTransCodeing(url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("isrsa", isrsa);
        CloseableHttpClient httpClient = getSslHttpClient();
        CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
	        int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
            	httpGet.abort();
            	log.error("读取URL对应数据流发生错误：url:" + url + ",状态码:"+statusCode+",描述："+HTTP_RETURN_CODE.get(statusCode));
                throw new MyException("HttpUtils->Get," + "url:" + url +",error status code:" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            // 判断返回类型
            String contentType = response.getFirstHeader("Content-Type").getValue();
            if ("text/plain".equals(contentType)){
    	        //{"errcode":40001,"errmsg":"invalid credential, access_token is invalid or not latest"}
            	String result = EntityUtils.toString(entity, "UTF-8");
            	JSONObject resultJson = JSONObject.parseObject(result);
	        	if (StringUtils.isNotBlank(resultJson.getString("errcode"))) {
	 	        	throw new MyException(resultJson.getString("errmsg"));
	 	        }
	        	return null;
            }
            byte[] bytes = inputStream2Byte(entity.getContent());
	        InputStream instream = new ByteArrayInputStream(bytes);
            EntityUtils.consume(entity);
            return instream;
		} finally {
			response.close();
			httpClient.close();
		}
	}

	/**
	 *
	 * doPost:(带参数POST). <br/>
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> params) throws Exception {
		if (StringUtils.isBlank(url)) {
			throw new MyException("URL请求地址为空!");
		}
		long l = System.currentTimeMillis();
		url = urlTransCodeing(url);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("isrsa", isrsa);
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			if(formParams != null && formParams.size() > 0){
				httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
			}
		}
		CloseableHttpClient httpClient = getSslHttpClient();
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
            	httpPost.abort();
            	log.error("Post失败请求耗时" + (System.currentTimeMillis() - l) + "毫秒, url:" + url + ",请求数据:" + params+",状态码:"+statusCode+",描述："+HTTP_RETURN_CODE.get(statusCode));
                throw new MyException("HttpUtils->Post," + "url:" + url + ",error status code:" + statusCode);
            }
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            log.info("Post耗时" + (System.currentTimeMillis() - l) + "毫秒, url:" + url + ",params:" + params.toString() + ",result:" + result);
            return result;
		} finally {
			response.close();
			httpClient.close();
		}
	}

	/**
	 *
	 * doPost:(Post请求数据流). <br/>
	 * @param url
	 * @param postData
	 * @param contentType
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String postData, String contentType) throws Exception {
		if (StringUtils.isBlank(url)) {
			throw new MyException("URL请求地址为空!");
		}
		long l = System.currentTimeMillis();
		url = urlTransCodeing(url);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("isrsa", isrsa);
		StringEntity postDataEntity = new StringEntity(postData,"utf-8");//解决中文乱码问题
		postDataEntity.setContentEncoding("UTF-8");
		postDataEntity.setContentType(contentType);
		httpPost.setEntity(postDataEntity);
		CloseableHttpClient httpClient = getSslHttpClient();
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
            	httpPost.abort();
            	log.error("Post失败请求耗时" + (System.currentTimeMillis() - l) + "毫秒, url:" + url + ",请求数据:" + postData+",状态码:"+statusCode+",描述："+HTTP_RETURN_CODE.get(statusCode));
                throw new MyException("HttpUtils->PostJson," + "url:" + url + ",error status code:" + statusCode);
            }
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            log.info("Post耗时" + (System.currentTimeMillis() - l) + "毫秒, url:" + url + ",postData:" + postData + ",result:" + result);
            return result;
		} finally {
			response.close();
			httpClient.close();
		}
	}

	/*
	 * 请求JSON数据
	 * */
	public static String doPostJson(String url, String postData) throws Exception {
		return doPost(url, postData, "application/json");
	}

	/* 请求XML数据*/
	public static String doPostXml(String url, String postData) throws Exception {
		return doPost(url, postData, "application/xml");
	}


	/**
	 *
	 * doGetObject:(Get方式返回Object-JavaBean对象). <br/>
	 * @param url
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T doGetObject(String url, Class<T> clazz) throws Exception{
		//return JSON.toJavaObject(doGetJSONObject(url), clazz);
		return JSON.parseObject(doGet(url), clazz);
	}

	/**
	 *
	 * doGetJSONObject:(Get方式返回JSONObject). <br/>
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doGetJSONObject(String url) throws Exception{
		return JSON.parseObject(doGet(url));
	}

	/**
	 *
	 * doGetJSONArray:(Get方式返回JsonArray). <br/>
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONArray doGetJSONArray(String url) throws Exception{
		return JSON.parseArray(doGet(url));
	}


	/**
	 *
	 * doGetArray:(Get方式返回List对象). <br/>
	 * @param url
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> doGetList(String url, Class<T> clazz) throws Exception{
		return JSON.parseArray(doGet(url), clazz);
	}

	/**
	 *
	 * doPostJSONObject:(Post方式返回JSONObject). <br/>
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doPostJSONObject(String url, Map<String, String> params) throws Exception{
		return JSON.parseObject(doPost(url, params));
	}

	/**
	 *
	 * doPostJSONObject:(这里用一句话描述这个方法的作用). <br/>
	 * @param url
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doPostJSONObject(String url, String postData) throws Exception{
		return JSON.parseObject(doPostJson(url, postData));
	}

	/**
	 *
	 * doPostObject:(这里用一句话描述这个方法的作用). <br/>
	 * @param url
	 * @param postData
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T doPostObject(String url, String postData, Class<T> clazz) throws Exception{
		return JSON.parseObject(doPostJson(url, postData), clazz);
	}


	/**
	 *
	 * doGetImageBase64Url:(Base64,URL数据). <br/>
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String doGetUrlToBase64(String url) throws Exception{
		InputStream inStream = getInputStream(url);
		try {
			byte[] bytes = inputStream2Byte(inStream);
			return Base64.encodeBase64String(bytes); // 返回Base64编码过的字节数组字符串
		} finally {
			if (inStream != null)
				inStream.close();
		}
	}

	/**
	 *
	 * doGetImageFileUrl:(存为文件,URL数据). <br/>
	 * @param url
	 * @param fileName
	 * @throws Exception
	 */
	public static void doGetUrlToImageFile(String url, String fileName) throws Exception{
		InputStream inStream = getInputStream(url);
		/*
		FileOutputStream outputStream = new FileOutputStream(fileName);
		int len = 0;
	    try {
	        while((len=inStream.read()) != -1){
	        	outputStream.write(len);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	    	if (inStream != null)
				inStream.close();
			if (outputStream != null)
				outputStream.close();
	    }
		*/
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream outputStream = new FileOutputStream(fileName);
		try {
			while ((len = inStream.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
		} finally {
			if (inStream != null)
				inStream.close();
			if (outputStream != null)
				outputStream.close();
		}
	}

	public static void main(String[] args) {
//		String url = "http://192.168.4.27:888/szslservice/WeiXinInterfaceAction.a?WeiXinCardCusInfo&weixinID=123&cardNo=1001000000000006&cusCount=50";
//		String url = "http://192.168.4.27:666/lbzs/api/user/login.action?param={%22password%22:%22456%22,%22userName%22:%22123%22,%22valCode%22:%22389%22}";
		//String url = "http://192.168.4.187:8080/i5xwxplus/test/testjson.html";
		//System.out.println(doGet(url));

//		String url = "http://192.168.4.187:8080/i5xwxplus/test/testjson.html";
		//String url = "http://192.168.4.27:666/api/tcsl/DownloadItemClassList.htm?data={\"mcID\":%1$s,\"rowsPerPage\":9999,\"pageNo\":1}";
		//String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ONWWEY9o_3DDFQUCHO4fm4ElpLOHYs2Ome2jY9m2dPRyxlA1aGRboRA6YgfIKlGozQxhOb058JPOdmxOzoxeGpipu6kQRU5PeQim5m1_T2k&openid=%1$s&lang=zh_CN";
		//url = String.format(url, "onWmpjs1hzuXiMr1ZaRNmZvJ0Pr0");
		try {
			//System.out.println(doGet(url));

			//JSONObject user = doGetJSONObject(url);
			//System.out.println(user.getString("nickname"));
			//System.out.println(user.getString("sex"));
			//System.out.println(user.getString("headimgurl"));

			//String wsUrl = "http://cs.wuuxiang.com:888/szslservice/WeiXinInterfaceAction.a?WeiXinGetShopInfo&weixinPlatID=gh_81f02eaef2fe";
	       // JSONArray jsonArray = HttpUtil.doGetJSONArray(wsUrl);
	        //JSONObject jsonObj = jsonArray.getJSONObject(0);

	       // System.out.println(jsonObj.getString("shopCode"));

//			System.out.println(doPostJson(url, ""));
/*			String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
			url = url.replace("ACCESS_TOKEN", "A8rb_s-h3oOgdG62b78r6H3dpLl9lSCcfySrhyLDpVvqsfXIRo6rJLc2--WnWHHq4L278Afy6gvZ8ev2rHBFtT9xygTPPn0MQxCrutxNlv0");
			url = url.replace("MEDIA_ID", "74lXJPSPusnJjklnf2EzmEo4L2rJZjH1KvuTD4AdY6kEd1buXvj4D6w6o9231lc-");
			System.out.println(doGetUrlToBase64(url));*/

			//String url = "http://cs.wuuxiang.com:888/szslservice/WeiXinInterfaceAction.a?CommGetCard&open_ID=-dkz35HA8OLCx7UqlqkWOXJo810GYn4ZMWFop0Shb9+Z1MmxvWOQCcSGdzmtKHuP01&pLat_ID=2015030900034621&fromType=1&mobile=&sex=12210&email=&name=%C4%BD%D7%CF&birthday=";


			//http://cs.wuuxiang.com:888/szslservice/WeiXinInterfaceAction.a?CommGetCard&open_ID=-dkz35HA8OLCx7UqlqkWOXJo810GYn4ZMWFop0Shb9+Z1MmxvWOQCcSGdzmtKHuP01&pLat_ID=2015030900034621&fromType=1&mobile=&sex=12210&email=&name=%C4%BD%D7%CF&birthday=


			/*String url = "http://cs.wuuxiang.com:888/szslservice/WeiXinInterfaceAction.a?" + "CommGetCard&open_ID=OPENID&pLat_ID=PLATID&fromType=0&mobile=MOBILE&sex=SEX&email=EMAIL&name=USERNAME&birthday=BIRTHDAY";
			url = url.replace("PLATID", "gh_86d9c77ec638").replace("OPENID", "oR6_JjiMA_Z1OPZBqAgCAhifNxpc").replace("MOBILE", "13821381520");
			url = url.replace("SEX", "12210").replace("BIRTHDAY", "1984-05-18").replace("EMAIL", "ankebeier@qq.com").replace("USERNAME", ToolUtil.encodeGBK("幕紫"));
			JSONObject jsonCardObj = HttpUtil.doGetJSONObject(url);
			System.out.println(jsonCardObj.toJSONString());
			CrmCards crmCards = HttpUtil.doGetObject(url, CrmCards.class);
			//System.out.println(crmCards.toString());
			System.out.println(JSONObject.toJSONString(crmCards));*/


			//String textMsg = "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"CONTENT\"}}";
			//textMsg = textMsg.replace("OPENID", "oR6_JjoEn5HnsZu29RAA5bEk6enA").replace("CONTENT", "正在为您转入多客服系统,请稍后!");
			//String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=abN1qjn8rAb63mTk1xOWfLaXFjdCOo3wEzz_yl-klyGT73aYrDX_WQuSRLbgGft6cPCBDxVUiUV2Pfgg-tyH0Fg8_Gy1biZjK_PZ2gQeXOcQ2yaHJABQMJQMWesjhfwk";
			//JSONObject resultJson = HttpUtil.doPostJSONObject(url, textMsg);
			//System.out.println(resultJson.toString());

			String ssss = "{\"component_appid\":\"wx9c77f4174cce50be\",\"authorizer_appid\":\"wx57175023a9285f89\"}";
			System.out.println(HttpUtil.doPostJson("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=is6ylCnxc3Bewf7dX5cEz-4bAb9RG15UuXOsCt-biY6CglaaftcECoeXCcelEZ2d7iPdJh3fpqS9g63FZ9K9J5iuSXJPXdXx4ZzHdOtoHl0SNRbADAWRV", ssss));;





		/*	oR6_JjoEn5HnsZu29RAA5bEk6enA
			gh_86d9c77ec638*/

			/*String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=jWdcyp03HMB-8l8lr6PJS5ftlcJ-wrl-asfz61NcLGvNmTEjKYL_CsJ9-zYWI_wXq12z98GpxWj9RbuFiM2v__Yn49mSkr_LU7gwqsC71M0";
				System.out.println(doPostJson(url, "{\"component_appid\":\"wx9c77f4174cce50be\",\"authorization_code\":\"queryauthcode@@@QjC87GXtlH_S7wqx2uu2qKrPvoXcYH1nU1VR6S2CKeQ8FPiNXUfuZPKvrBrbNlsC\"}"));
			*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			throw new MyException("123");
		} catch (Exception e) {
			System.out.println("dd" + e.getClass().getName().toString());
			System.out.println("cc" + e.fillInStackTrace().toString());
			System.out.println(e.getClass().getSimpleName());

		}*/

	}
}
