/**
 * Title: StringUtil.java
 * File Description: �ַ���������
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-14
 */
package com.wk.cd.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.exc.DataErrorException;
import com.wk.cd.common.util.Assert;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: �ַ���������
 * @author lixl
 */
public class StringUtil {
	private static final Log logger = LogFactory.getLog();

	/**
	 * Double���͸�ʽ���ַ���
	 * @param val
	 * @return
	 */
	public static String double2String(Double val) {
		Assert.assertNotEmpty(val, "val");
		String v = "";

		DecimalFormat f = new DecimalFormat("0.000000");
		String t = f.format(val);
		String[] ary = t.split("\\.");
		String a2 = ary[1].replaceAll("(0)*$", "");
		if (a2 == null || "".equals(a2))
			v = ary[0];
		else
			v = ary[0] + "." + a2;
		return v;
	}

	/**
	 * ȥ��^M
	 * @param s
	 * @return String
	 */
	public static String ustr2wstr(String s) {
		Assert.assertNotEmpty(s, "s");
		return s.replaceAll("\\x0D", "");
	}

	/**
	 * ȥ�ո�
	 * @param s
	 * @return String
	 */
	public static String rpSp(String s) {
		Assert.assertNotEmpty(s, "s");
		return s.replaceAll("\\x20", "");
	}

	/**
	 * ��ʽ��unix�ַ���
	 * @param s
	 * @return String
	 */
	public static String usFmWs(String s) {
		Assert.assertNotEmpty(s, "s");
		return rpH(ustr2wstr(s));
	}

	/**
	 * ȥ��^H
	 * @param s
	 * @return String
	 */
	public static String rpH(String s) {
		Assert.assertNotEmpty(s, "s");
		return s.replaceAll("\\x08", "");
	}

	/**
	 * ȥ�����з�
	 * @param s
	 * @return String
	 */
	public static String rpEnter(String s) {
		Assert.assertNotEmpty(s, "s");
		return s.replaceAll("\\x0A", "");
	}

	/**
	 * �����ַ���������˵����������ֵ
	 * @author lixl (2014-11-18)
	 * @param s
	 * @param type
	 * @return Object
	 */
	public static Object parseVal(String s, String type) {
		Assert.assertNotEmpty(s, "s");
		Assert.assertNotEmpty(type, "type");

		String[] a = type.split("\\.");
		String stype = a[a.length - 1];
		Object obj;
		if (stype.equalsIgnoreCase("Integer") || stype.equalsIgnoreCase("int")) {
			obj = Integer.valueOf(s);
		} else if (stype.equalsIgnoreCase("Double")) {
			obj = Double.valueOf(s);
		} else if (stype.equalsIgnoreCase("Short")) {
			obj = Short.valueOf(s);
		} else if (stype.equalsIgnoreCase("Long")) {
			obj = Long.valueOf(s);
		} else if (stype.equalsIgnoreCase("JaDate")) {
			obj = JaDate.valueOf(s);
		} else if (stype.equalsIgnoreCase("JaTime")) {
			obj = JaTime.valueOf(s);
		} else if (stype.equalsIgnoreCase("JaDateTime")) {
			obj = JaDateTime.valueOf(s);
		} else {
			obj = s;
		}
		return obj;
	}

	/**
	 * ���ַ�������ת�����ַ������м��� sep �ָ�
	 * @author lixl (2014-12-4)
	 * @param a �ַ�������
	 * @param sep �ָ���
	 * @return String �ַ���
	 */
	public static String ary2str(String[] a, String sep) {
		StringBuffer br = new StringBuffer();
		for (String s : a) {
			br.append(s).append(sep);
		}
		return br.toString();
	}
	
	/**
	 * ���ַ�������ת�����ַ������м��� sep �ָ�
	 * @author lixl (2014-12-4)
	 * @param a �ַ�������
	 * @param sep �ָ���
	 * @return String �ַ���
	 */
	public static String[] str2ary(String str, String sep) {
		return str.split(sep);
	}

	/**
	 * ��int����ת�����ַ������м��� sep �ָ�
	 * @param a int����
	 * @param sep �ָ���
	 * @return String �ַ���
	 */
	public static String ary2str(int[] a, String sep) {
		StringBuffer br = new StringBuffer();
		for (int s : a) {
			br.append(s).append(sep);
		}
		return br.toString();
	}

	/**
	 * Description: ��ת����String��ԭΪint����
	 * @param str �ַ���
	 * @param sep �ָ���
	 * @return
	 */
	public static int[] str2intary(String str, String sep) {
		String[] strings = str.split(sep);
		int[] ints = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			ints[i] = Integer.parseInt(strings[i]);
		}
		return ints;
	}

	/**
	 * ��int����ת�����ַ������м��� "|" �ָ�
	 * @param a int����
	 * @param sep �ָ���
	 * @return String �ַ���
	 */
	public static String ary2str(int[] a) {
		return ary2str(a, "|");
	}

	/**
	 * Description: ���� "|" �ָ�ת����String��ԭΪint����
	 * @param str �ַ���
	 * @param sep �ָ���
	 * @return
	 */
	public static int[] str2intary(String str) {
		return str2intary(str, "\\|");
	}
	
	/**
	 * Description: ���� "|" �ָ�ת����String��ԭΪint����
	 * @param str �ַ���
	 * @return
	 */
	public static String[] str2strary(String str) {
		return str2strary(str, "|");
	}
	
	/**
	 * ���ַ�������ת�����ַ������м��� sep �ָ�
	 * @author lixl (2014-12-4)
	 * @param a �ַ�������
	 * @param sep �ָ���
	 * @return String �ַ���
	 */
	public static String[] str2strary(String str, String sep) {
		if (Assert.isEmpty(str)) {
			return new String[0];
		}
		sep = Pattern.quote(sep);
		return str.split(sep);
	}

	/**
	 * ��String����ת�����ַ������м��� "|" �ָ�
	 * @param a String����
	 * @return String �ַ���
	 */
	public static String ary2str(String[] a) {
		return ary2str(a, "|");
	}
	
	/**
	 * ��inputStreamת�����ַ���
	 * @author lixl (2014-12-4)
	 * @param in
	 * @return String
	 */
	public static String stream2str(InputStream in) {
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line;
		StringBuffer br = new StringBuffer();
		// char[] buf = new char[1024];
		try {
			// int len = read.read(buf);
			// System.out.println("len=" + len);
			// logger.debug("len=" + len);
			// if (len >= 0) {
			// logger.debug(new String(buf, 0, len));
			// br.append(buf, 0, len);
			// }

			while ((line = read.readLine()) != null) {
				br.append(line).append("\n");
			}
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		return br.toString();
	}

	/**
	 * ��byte����ת��Ϊ��ʾ16����ֵ���ַ���
	 * @author lixl (2014-12-5)
	 * @param aryB
	 * @return String
	 */
	public static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// �Ѹ���ת��Ϊ����
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// С��0F������Ҫ��ǰ�油0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * ����ʾ16����ֵ���ַ���ת��Ϊbyte���飬
	 * @author lixl (2014-12-5)
	 * @param strIn
	 * @return byte[]
	 */
	public static byte[] hexStr2ByteArr(String strIn) {
		Assert.assertNotEmpty(strIn, "strIn");
		byte[] arrOut = null;

		try {
			byte[] arrB = strIn.getBytes("GBK");
			int iLen = arrB.length;

			// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
			arrOut = new byte[iLen / 2];
			for (int i = 0; i < iLen; i = i + 2) {
				String strTmp = new String(arrB, i, 2);
				arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
			throw new DataErrorException().addScene("input", "getBytes(GBK)");
		}
		return arrOut;
	}

	/**
	 * �жϵ�һλ�Ƿ�Ϊ����
	 * @param s
	 * @return
	 */
	public static boolean startDigit(String s) {
		return s.matches("[0-9]{1,}");
	}

	/**
	 * �ж��Ƿ�ȫΪ����
	 * @param s
	 * @return
	 */
	public static boolean isAllDigit(String s) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) s);
		return matcher.matches();
	}

	public static boolean endWith(String s, String sep) {
		Assert.assertNotEmpty(s, "s");
		Assert.assertNotEmpty(s, "sep");
		return s.endsWith(sep);
	}

	/**
	 * �������ַ���ת��ΪUTF-8�ַ���, һ������GBK->UTF-8
	 * @param str ָ���ַ���(GBK)
	 * @return String ת�����UTF-8�ַ���
	 */
	public static String toU8(String str) {
		try {
			byte[] u8b = com.wk.util.StringUtil.toUTF8(str);// ȷ�������ַ���չΪUTF-8
			return new String(u8b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
			throw new DataErrorException().addScene("input", "getBytes(UTF-8)");
		}
	}

	public static void main(String[] args) {
		System.out.println(startDigit("9"));
		System.out.println(isAllDigit("1111"));
		System.out.println(isAllDigit("1w11"));
		System.out.println(isAllDigit("tw11"));
		System.out.println(endWith("D:/www/ww/", "/"));
	}

}