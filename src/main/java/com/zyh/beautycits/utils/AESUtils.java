package com.zyh.beautycits.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * AES加密解密工具包
 * </p>
 *
 */
public class AESUtils {

	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
	private static final int CACHE_SIZE = 1024;

	/**
	 * <p>
	 * 生成随机密钥
	 * </p>
	 *
	 * @return
	 * @throws Exception
	 */
	public static String getSecretKey() throws Exception {
		return getSecretKey(null);
	}

	/**
	 * <p>
	 * 生成密钥
	 * </p>
	 *
	 * @param seed
	 *            密钥种子
	 * @return
	 * @throws Exception
	 */
	public static String getSecretKey(String seed) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		if (StringUtils.isNotBlank(seed)){
			secureRandom.setSeed(seed.getBytes());
		}
		keyGenerator.init(KEY_SIZE, secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		return Base64.encodeBase64String(secretKey.getEncoded());
	}

	/**
	 * <p>
	 * 加密
	 * </p>
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		byte[] raw = k.getEncoded();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		return cipher.doFinal(data);
	}

	/**
	 * <p>
	 * 解密
	 * </p>
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		byte[] raw = k.getEncoded();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * encrypt:(默认加密种子的加密). <br/>
	 * @param encryptText
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String encryptText) throws Exception {
		return Base64.encodeBase64String(encrypt(encryptText.getBytes(), getSecretKey()));
	}
	
	/**
	 * 
	 * encrypt:(自定义密钥种子的加密). <br/>
	 * @param encryptText
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String encryptText, String seed) throws Exception {
		String secretKey = getSecretKey(seed);
		return Base64.encodeBase64String(encrypt(encryptText.getBytes(), secretKey));
	}
	
	/**
	 * 
	 * decrypt:(默认密钥种子解密). <br/>
	 * @param decryptText
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String decryptText) throws Exception {
		return new String(decrypt(Base64.decodeBase64(decryptText),  getSecretKey()));
	}
	
	/**
	 * 
	 * decrypt:(带密钥种子的解密). <br/>
	 * @param decryptText
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String decryptText, String seed) throws Exception {
		String secretKey = getSecretKey(seed);
		return new String(decrypt(Base64.decodeBase64(decryptText), secretKey));
	}
	
	/**
	 * <p>
	 * 文件加密
	 * </p>
	 *
	 * @param key
	 * @param sourceFilePath
	 * @param destFilePath
	 * @throws Exception
	 */
	public static void encryptFile(String key, String sourceFilePath,
			String destFilePath) throws Exception {
		File sourceFile = new File(sourceFilePath);
		File destFile = new File(destFilePath);
		if (sourceFile.exists() && sourceFile.isFile()) {
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			destFile.createNewFile();
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(destFile);
			Key k = toKey(Base64.decodeBase64(key));
			byte[] raw = k.getEncoded();
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			CipherInputStream cin = new CipherInputStream(in, cipher);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = cin.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			cin.close();
			in.close();
		}
	}

	/**
	 * <p>
	 * 文件解密
	 * </p>
	 *
	 * @param key
	 * @param sourceFilePath
	 * @param destFilePath
	 * @throws Exception
	 */
	public static void decryptFile(String key, String sourceFilePath,
			String destFilePath) throws Exception {
		File sourceFile = new File(sourceFilePath);
		File destFile = new File(destFilePath);
		if (sourceFile.exists() && sourceFile.isFile()) {
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			destFile.createNewFile();
			FileInputStream in = new FileInputStream(sourceFile);
			FileOutputStream out = new FileOutputStream(destFile);
			Key k = toKey(Base64.decodeBase64(key));
			byte[] raw = k.getEncoded();
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			CipherOutputStream cout = new CipherOutputStream(out, cipher);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				cout.write(cache, 0, nRead);
				cout.flush();
			}
			cout.close();
			out.close();
			in.close();
		}
	}

	/**
	 * <p>
	 * 转换密钥
	 * </p>
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		return new SecretKeySpec(key, ALGORITHM);
	}

	
	public static void main(String[] args) {
		try {
			
			  	String source = "621";
			  	String enText = encrypt(source, "AES_KEY_SEED");
			  	System.out.println(enText);
			  	String deText = decrypt(enText, "AES_KEY_SEED");
			  	System.out.println(deText);
			  	
			  	//javax.crypto.BadPaddingException: Invalid pad value!
			  	
			  	//String encryiapiid = ToolUtil.decodeUtf8("i3/Fs0+R42RY/zz1teKfFQ==");
	            //String apiid = AESUtils.decrypt("i3/Fs0+R42RY/zz1teKfFQ==", "AES_KEY_SEED");
	           // System.out.println(apiid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
