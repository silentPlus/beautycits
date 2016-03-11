package com.zyh.beautycits.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * @author Administrator
 *
 */
public class ToolUtil {

	static private String lastBJ = "4G";

	// 包含匹配
	public static boolean containsMatch(String container, String[] regex) {
		for (String str : regex) {
			if (container.contains(str)) {
				return true;
			}
		}
		return false;
	}

	// 全匹配
	public static boolean wholeMatch(String[] container, String regex) {
		for (String str : container) {
			if (str.equals(regex)) {
				return true;
			}
		}
		return false;
	}

	// 全匹配忽略大小写
	public static boolean wholeMatchIgnoreCase(String[] container, String regex) {
		for (String str : container) {
			if (str.equalsIgnoreCase(regex)) {
				return true;
			}
		}
		return false;
	}

	// UTF8格式转码
	public static String encodeUtf8(String content) {
		try {
			return URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// UTF8格式解码
	public static String decodeUtf8(String content) {
		try {
			return URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// GBK格式转码
	public static String encodeGBK(String content) {
		try {
			return URLEncoder.encode(content, "GBK");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// GBK格式解码
	public static String decodeGBK(String content) {
		try {
			return URLDecoder.decode(content, "GBK");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// 加密
	public static String encrypt(String plainText) {
		return Base64.encodeBase64String(plainText.getBytes()).replace("O", "*")
				.replace("N", ":")
				+ lastBJ;
	}

	// 解密
	public static String decrypt(String enStr) {
		if (enStr.lastIndexOf(lastBJ) == enStr.length() - lastBJ.length()) { // 末尾带标记的，为了解决windows
																				// phone
																				// Url地址阶段问题
			enStr = enStr.substring(0, enStr.length() - lastBJ.length());
		}
		return new String(Base64.decodeBase64(enStr.replace("*", "O").replace(
				":", "N")));
	}

	/**
	 * UTF-8转成Unicode
	 * @param utf8
	 * @return
	 */
	public static String utf8ToUnicode(String utf8){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < utf8.length(); i++) {
			if (ValidatorUtil.isChinese(utf8.charAt(i))) {
				sb.append("\\u" + Integer.toHexString(utf8.charAt(i) & 0xffff));
			} else {
				sb.append(utf8.charAt(i));
			}
		}
		sb.trimToSize();
		return sb.toString();
	}

	/**
	 * Unicode转成UTF-8
	 * @param line
	 * @return
	 * http://www.2cto.com/weixin/201408/324640.html
	 */
	public static String unicodeToUtf8(String line) {
		int len = line.length();
		char[] out = new char[len];// 保存解析以后的结果
		int outLen = 0;
		for (int i = 0; i < len; i++) {
			char aChar = line.charAt(i);
			if (aChar == '\\') {
				aChar = line.charAt(++i);
				if (aChar == 'u') {
					int value = 0;
					for (int j = 0; j < 4; j++) {
						aChar = line.charAt(++i);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = aChar;
			}
		}
		return new String(out, 0, outLen);
	}


	/**
	 *
	 * buildWeChatTokenSignature:生成Signature. <br/>
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @return
	 */
	public static String buildWeChatTokenSignature(String timestamp, String nonce, String token) {
		String[] tmpArr = { token, timestamp, nonce };
		return ValidatorUtil.signature(tmpArr);
	}

	/**
	 *
	 * buildWeChatJsApiSignature:(用户生成微信JSAPi的Signature). <br/>
	 * @param jsapi_ticket
	 * @param timestamp
	 * @param nonce
	 * @param url
	 * @return
	 */
	public static String buildWeChatJsApiSignature(String jsapi_ticket, String timestamp, String nonce, String url) {
		//jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg
		//&noncestr=Wm3WZYTPz0wzccnW
		//&timestamp=1414587457
		//&url=http://mp.weixin.qq.com
		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce + "&timestamp=" + timestamp + "&url=" + url;
		return ValidatorUtil.SHA1Encode(string1).toLowerCase();
	}

	/**
	 *
	 * StreamToString:流转字符串. <br/>
	 * @param in
	 * @return
	 */
	public static String InputStreamToString(InputStream in) throws IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
		buffer.trimToSize();
		return buffer.toString();
	}

	/**
	 *
	 * mathCompareByStr:字符串转数字精确对比. <br/>
	 * @return
	 */
	public static Boolean mathCompareByStr(String str1,String str2){
		return (new BigDecimal(str1).compareTo(new BigDecimal(str2))==0);
	}

	/**
	 *
	 * objMapIsNull:检测map对象是否为null. <br/>
	 * @throws Exception
	 * @throw Exception
	 */
	public static String objMapIsNull(Map<String, Object>mapObjs){
			for (Map.Entry<String, Object> entry : mapObjs.entrySet()) {
				if(entry.getValue()==null){
					return entry.getKey();
				}
			}
			return "";
	}
	/**
	 *
	 * strMapIsNull:检测map中参数str对象是否为null. <br/>
	 * @throws Exception
	 * @throw Exception
	 */
	public static String strMapIsNull(Map<String, String>mapObjs){
			for (Map.Entry<String, String> entry : mapObjs.entrySet()) {
				if(StringUtils.isBlank(entry.getValue())){
					return entry.getKey();
				}
			}
			return "";
	}


	/**
	 *
	 * getUUIDString(这里用一句话描述这个方法的作用)
	 * @date 2016年1月19日 下午1:56:58
	 *
	 * @return
	 * @return String
	 */
	public static String getUUIDString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
