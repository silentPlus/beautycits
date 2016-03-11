package com.zyh.beautycits.utils;

import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;


public class UrlSignTool {

	private final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f' };

	private static final long DIFF_TIME = 600L;

	private UrlSignTool() {
	}

	private static final UrlSignTool urlSignTool = new UrlSignTool();

	synchronized public static UrlSignTool getInstance() {
		return urlSignTool;
	}

	private boolean isEmpty(String key) {
		if (key == null || "".equals(key)) {
			return true;
		}
		return false;
	}

	public String md5(String string) {
		try {
			byte[] bytes = string.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			byte[] updateBytes = messageDigest.digest();
			int len = updateBytes.length;
			char myChar[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = updateBytes[i];
				myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				myChar[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(myChar);
		} catch (Exception e) {

		}
		return "";
	}

	public String map2StrWx(TreeMap<String, String> params) {
		String ret = "";
		if (params == null || params.isEmpty())
			return ret;
		Set<String> keySet = params.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = params.get(key);
			ret += key + "=" + value + "&";
		}
		//return ret;
		return ret.substring(0,ret.length()-1);
	}


	public String map2Str(TreeMap<String, String> params,String sk) {
        String ret = "";
        if (params == null || params.isEmpty())
            return ret;
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);
            ret += key + "=" + value + "&";
        }
        return ret+"sk="+sk;
    }

	public String getSign(TreeMap<String, String> params,String sk){
	    String strForMd5=map2Str(params,sk);
	    return md5(strForMd5);
	}
	public Boolean checkSign(TreeMap<String, String> params,String sk,String sign){
	   String checkstr= getSign(params, sk);
	   return sign.equals(checkstr);
	}

	public String getCommPaySingUrl(String MD5Key, TreeMap<String, String> urlKeyValueMap) {
		String sign = md5(map2StrWx(urlKeyValueMap) + md5(MD5Key));
		return map2StrWx(urlKeyValueMap) + "&sign=" + sign;
	}

	public String getCommPaySingStr(String MD5Key, TreeMap<String, String> urlKeyValueMap) {
		return md5(map2StrWx(urlKeyValueMap) + md5(MD5Key));
	}

	public String strFillZero(Integer target, Integer count) {
        //得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(count);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(count);
        //输出测试语句
        return nf.format(target);
	}

}

