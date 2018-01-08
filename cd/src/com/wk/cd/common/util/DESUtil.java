/**
 * Title: DESUtil.java
 * File Description: DES加解密工具类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 12/5/2014
 */

package com.wk.cd.common.util;

import java.security.Key;

import javax.crypto.*;

import com.wk.cd.common.util.StringUtil;
import com.wk.cd.exc.CryptErrorException;
import com.wk.cd.common.util.DESHelper;

/**
 * Class Description:DES加解密工具类
 * 
 * @author lixl
 */
public class DESUtil {
	/** 字符串默认键值 */
	private final static String strDefaultKey = "corsworkcomwkcmscommonutilDesUtil2014";
	private final static String ALGDEFTKEY16 = "corsworkcomwkcms";

	/**
	 * DES加密算法
	 * 
	 * @author lixl (2014-12-5)
	 * @param key
	 *            密钥
	 * @param data
	 *            加解密数据
	 * @return String
	 */
	public static String docrypt(String key, String data) {
		Assert.assertNotEmpty(data, "data");
		String k;
		if (Assert.isEmpty(key)) {
			k = strDefaultKey;
		} else {
			k = key;
		}

		return encrypt(false, k, data);
	}

	/**
	 * 加解密，支持解密 Description:
	 * 
	 * @param isencrpt
	 * @param key
	 * @param data
	 * @return
	 */
	public static String docryptAllowReverse(boolean isencrpt, String key,
			String data) {
		Assert.assertNotEmpty(data, "data");
		String k;
		if (Assert.isEmpty(key)) {
			k = strDefaultKey;
		} else {
			k = key;
		}

		if (isencrpt) {
			return encrypt(true, k, data);
		} else {
			return decrypt(k, data);
		}
	}

	/**
	 * 获取系统默认密钥
	 * 
	 * @return String
	 */
	public static String getDef16Key() {
		return ALGDEFTKEY16;
	}

	private static byte[] getStrBytes(String data) {
		try {
			return data.getBytes("GBK");
		} catch (Exception e) {
			throw new CryptErrorException().addScene("E", e);
		}
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串，
	 */
	private static String byteArr2HexStr(byte[] arrB) {
		return StringUtil.byteArr2HexStr(arrB);
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组，
	 */
	private static byte[] hexStr2ByteArr(String strIn) {
		return StringUtil.hexStr2ByteArr(strIn);
	}

	/**
	 * 加密字节数组
	 * 
	 * @param isRevse
	 *            是否可逆
	 * @param k
	 *            key
	 * @param input
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	private static byte[] encrypt(boolean isreverse, String k, byte[] input) {
		try {
			Key key = getKey(getStrBytes(k));
			byte[] endt = isreverse ? getLeast16Bytes(input)
					: get8BytesAlg(input);
			Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
			c1.init(Cipher.ENCRYPT_MODE, key);
			return c1.doFinal(endt);
		} catch (Exception e) {
			throw new CryptErrorException().addScene("E", e);
		}
	}

	/**
	 * 加密字符串
	 */
	private static String encrypt(boolean isreverse, String key, String strIn) {
		return byteArr2HexStr(encrypt(isreverse, key, strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 */
	private static byte[] decrypt(String k, byte[] input) {
		try {
			Key key = getKey(getStrBytes(k));
			Cipher c1 = Cipher.getInstance("DES/ECB/NoPadding");
			c1.init(Cipher.DECRYPT_MODE, key);
			return c1.doFinal(input);
		} catch (Exception e) {
			throw new CryptErrorException().addScene("E", e);
		}
	}

	/**
	 * 解密字符串
	 */
	private static String decrypt(String key, String strIn) {
		return new String(decrypt(key, hexStr2ByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 */
	private static Key getKey(byte[] arrBTmp) {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = get8BytesAlg(arrBTmp);

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	/**
	 * 获取8字节输入数据算法
	 * 
	 * @param input
	 *            输入数据
	 * @return byte[]
	 */
	private static byte[] get8BytesAlg(byte[] input) {
		int sum = 0, residue = 0;
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		for (byte b : input) {
			sum += b & 0xff;
		}
		residue = sum % 8;
		// 将原始字节数组转换为8位
		for (int i = 0, j = residue; i < 8; i++, j++) {
			if (j >= input.length) {
				j = 0;
			}
			int ox = sum ^ input[j];
			arrB[i] = (byte) (ox & 0xff);
		}
		return arrB;
	}

	private static byte[] getLeast16Bytes(byte[] input) {
		byte[] a = new byte[16];
		int len = input.length > 16 ? 16 : input.length;
		for (int i = 0; i < len; i++) {
			a[i] = input[i];
		}
		return a;
	}

	/**
	 * 使用默认密钥进行DES加密
	 */
	public static String encrypt(String plainText) {
		try {
			return new DESHelper().encrypt(plainText);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 使用默认密钥进行DES解密
	 */
	public static String decrypt(String plainText) {
		try {
			return new DESHelper().decrypt(plainText);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String s = "cd F:\\workspace-android";
		String s2 = "cd F:\\workspace-android\\Demo";
		String s3 = "112223";
		String s4 = "abc(lfl123";
		String s1 = "abcfertc1234567890123456";

		String rs = DESUtil.docrypt("", s);
		String rs2 = DESUtil.docrypt("", s1);
		String rs3 = DESUtil.docrypt("", s2);
		String rs4 = DESUtil.docrypt("", s3);
		String rs5 = DESUtil.docrypt("", s4);
		System.out.println("加密数据：" + rs);
		System.out.println("加密数据2：" + rs2);
		System.out.println("加密数据2：" + rs3);
		System.out.println("加密数据2：" + rs4);
		System.out.println("加密数据2：" + rs5);
		String rsn = docryptAllowReverse(true, "", s);
		System.out.println("rsn=" + rsn);
		System.out.println("解密数据：" + docryptAllowReverse(false, "", rsn));
		System.out.println("-----------------------------------");
		String rsn1 = docryptAllowReverse(true, "", s2);
		System.out.println("rsn1=" + rsn1);
		System.out.println("解密数据：" + docryptAllowReverse(false, "", rsn1));
	}
}
