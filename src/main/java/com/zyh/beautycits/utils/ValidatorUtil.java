package com.zyh.beautycits.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.druid.support.http.util.IPAddress;
import com.alibaba.druid.support.http.util.IPRange;

/** 
 * @文件名：ValidatorUtil.java 
 * @备注：验证类 
 */
public class ValidatorUtil {

	private static boolean isMatch(String regex, String orginal){  
        if (StringUtils.isBlank(orginal)) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
	
	/** 
	* 是否是整数 
	* @param orginal 
	*/
	public static boolean isInteger(String orginal){  
		return isMatch("^-?[1-9]\\d*$", orginal);  
	}  
	 
	/** 
    * 是否是正整数 
    * @param orginal 
    */ 
	public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);  
    } 
	
	/** 
    * 是否是负整数 
    * @param orginal 
    */ 
	public static boolean isNegativeInteger(String orginal) {  
		return isMatch("^-[1-9]\\d*", orginal);
    }
	
	
	/** 
    * 是否是数字 
    * @param orginal 
    */  
    public static boolean isNumber(String orginal){  
        return isMatch("^([+-]?)\\d*\\.?\\d+$", orginal);
    }
    
    /** 
     * 是否是正数（正整数 + 0） 
     * @param orginal 
     */  
    public static boolean isPositiveNumber(String orginal){  
        return isMatch("^[1-9]\\d*|0$", orginal);
    }  
      
    /** 
     * 是否是负数（负整数 + 0） 
     * @param orginal 
     */  
    public static boolean isNegativeNumber(String orginal){  
        return isMatch("^-[1-9]\\d*|0$", orginal);
    }  
	
    
	/** 
	* 是否是浮点数 
	* @param orginal 
	*/
	public static boolean isDecimal(String orginal){  
	    return isMatch("^([+-]?)\\d*\\.\\d+$", orginal);  
	    //"[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+"
	    //"^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$"
	} 
		
	/** 
    * 是否是正浮点数 
    * @param orginal 
    */
	public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal); //"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$" 
    }  
	
	/** 
    * 是否是非正浮点数 （负浮点数 + 0） 
    * @param orginal 
    */
	public static boolean isNotPositiveDecimal(String orginal){  
        return isMatch("^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", orginal);  
    } 
		
	/** 
	* 是否是负浮点数 
	* @param orginal 
	*/
    public static boolean isNegativeDecimal(String orginal){  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal); //"^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$" 
    }  
      
	/** 
	* 是否是非负浮点数 （正浮点数 + 0） 
	* @param orginal 
	*/
	public static boolean isNotNegativeDecimal(String orginal){  
	    return isMatch("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", orginal); 
	}  
    
	
	/** 
    * 是否是邮件 
    * @param orginal 
    */  
    public static boolean isEmail(String orginal){  
        return isMatch("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", orginal); 
    }  
      
    /** 
    * 是否是颜色 
    * @param orginal 
    */  
    public static boolean isColor(String orginal){  
        return isMatch("^[a-fA-F0-9]{6}$", orginal); 
    }  
      
    /** 
    * 是否是url 
    * @param orginal 
    */  
    public static boolean isUrl(String orginal){
		orginal = orginal.toLowerCase();
		// String domainRules = "com.cn|net.cn|org.cn|gov.cn|com.hk|公司|中国|网络|com|net|org|int|edu|gov|mil|arpa|Asia|biz|info|name|pro|coop|aero|museum|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cf|cg|ch|ci|ck|cl|cm|cn|co|cq|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|es|et|ev|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gh|gi|gl|gm|gn|gp|gr|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|in|io|iq|ir|is|it|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|ml|mm|mn|mo|mp|mq|mr|ms|mt|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nt|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|pt|pw|py|qa|re|ro|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sy|sz|tc|td|tf|tg|th|tj|tk|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|va|vc|ve|vg|vn|vu|wf|ws|ye|yu|za|zm|zr|zw";
		String regex = "^((https|http|ftp|rtsp|mms)?://)"
				+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
				+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
				+ "|" // 允许IP和DOMAIN（域名）
				+ "(([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]+\\.)?" // 域名- www.
				+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
				// + "(" + domainRules + "))" // first level domain- .com or //
				// .museum
				+ "[a-z]{2,6})" // first level domain- .com or .museum
				+ "(:[0-9]{1,4})?" // 端口- :80
				+ "((/?)|" // a slash isn't required if there is no file name
				+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		return isMatch(regex, orginal);
    	//return isMatch("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", orginal);
    }  
      
    /** 
    * 是否是中文 
    * @param orginal 
    */  
    public static boolean isChinese(String orginal){  
        return isMatch("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$", orginal);   
    }  
      
    /** 
    * 是否是ACSII字符 
    * @param orginal 
    */  
    public static boolean isAscii(String orginal){  
        return isMatch("^[\\x00-\\xFF]+$", orginal);   
    }  
      
    /** 
    * 是否是邮编 
    * @param orginal 
    */  
    public static boolean isZipcode(String orginal){  
        return isMatch("^\\d{6}$", orginal);   
    }  

    /** 
    * 是否是电话号码的函数(包括验证国内区号,国际区号,分机号) 
    * @param orginal 
    */  
    public static boolean isTelphone(String orginal){  
        return isMatch("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", orginal);   
    }  
    
    /** 
    * 是否是手机 
    * @param orginal 
    */  
    public static boolean isMobile(String orginal){
    	return isMatch("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$", orginal);   
        //return isMatch("^(13|14|15|17|18|)[0-9]{9}$", orginal);   
    } 
      
    /** 
     * 是否是ipv4地址 
     * @param orginal 
     */  
    public static boolean isIpv4(String orginal){  
        return isMatch("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", orginal);   
    }  
            
    /** 
     * 是否是图片 扩展名
     * @param orginal 
     */  
    public static boolean isPictureExt(String orginal){  
        return isMatch("(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", orginal);   
    }  
      
    /** 
    * 是否是压缩文件 
    * @param orginal 
    */  
    public static boolean isRarExt(String orginal){  
        return isMatch("(.*)\\.(rar|zip|7zip|tgz)$", orginal);   
    }  
      
    /** 
    * 是否是日期 
    * @param orginal 
    */  
    public static boolean isDate(String orginal){  
        return isMatch("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$", orginal);   
    }  
      
    /** 
    * 是否是QQ号码 
    * @param orginal 
    */  
    public static boolean isQq(String orginal){  
        return isMatch("^[1-9]*[1-9][0-9]*$", orginal);   
    }  
    
    /** 
    * 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串 
    * @param orginal 
    */  
    public static boolean isUsername(String orginal){  
        return isMatch("^\\w+$", orginal);   
    }  
      
    /** 
     * 是否是字母 
     * @param orginal 
     */  
    public static boolean isLetter(String orginal){  
        return isMatch("^[A-Za-z]+$", orginal);   
    }  
      
    /** 
    * 是否是大写字母 
    * @param orginal 
    */  
    public static boolean isLetter_u(String orginal){  
        return isMatch("^[A-Z]+$", orginal);   
    }  
      
    /** 
    * 是否是小写字母 
    * @param orginal 
    */  
    public static boolean isLetter_l(String orginal){  
        return isMatch("^[a-z]+$", orginal);   
    }  
      
    /** 
    * 是否是价格 
    * @param orginal 
    */  
    public static boolean isPrice(String orginal){  
        return isMatch("^([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$", orginal);   
    }  

    
	/**
	 * 是否是汉字
	 * @param a
	 * @return
	 */
	public static boolean isChinese(char a) {
		int v = a;
		return (v >= 19968 && v <= 171941);
	}
	
	/** 
    * 是否包含中文字符 
    * @param orginal 
    */
	public static boolean isContainChinese(String orginal) {
        return isMatch(".*[\\u4e00-\\u9faf].*", orginal);
    }
	
	
	public final static String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	// sha1加密
	public final static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	// 数组转字符串
	public final static String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}
	
	// 数组转字符串
//	public final static String ArrayToString(String[] arr) {
//		StringBuffer bf = new StringBuffer();
//		for (int i = 0; i < arr.length; i++) {
//			bf.append(arr[i]);
//		}
//		return bf.toString();
//	}

	//签名函数
	public static String signature(String[] tmpArr) {
		Arrays.sort(tmpArr);
		return SHA1Encode(ArrayToString(tmpArr)).toLowerCase();
	}
	
}
