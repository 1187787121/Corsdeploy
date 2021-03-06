/**
 * Title: Assert.java
 * File Description:  Assert类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/18/2014
 */

package com.wk.cd.common.util;

import java.util.Collection;
import java.util.Map;

import com.wk.cd.exc.InputParamIsNullException;
import com.wk.db.EnumValue;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 校验是否为空
 * @author lixl
 */
public class Assert {
	
	public static boolean isEmpty(CharSequence s) {
		if (s == null) {
			return true;
		}

		int length = s.length();
		if (length == 0) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (' ' != s.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isEmpty(JaDate obj) {
		return (obj == null);
	}

	public static boolean isEmpty(JaDateTime obj) {
		return (obj == null);
	}

	public static boolean isEmpty(JaTime obj) {
		return (obj == null);
	}

	public static boolean isEmpty(EnumValue<?> obj) {
		return (obj == null);
	}

	public static boolean isEmpty(Collection<?> obj) {
		return (obj == null || obj.size() == 0);
	}

	public static boolean isEmpty(Map<?, ?> obj) {
		return (obj == null || obj.isEmpty());
	}

	public static boolean isEmpty(Object[] obj) {
		return (obj == null || obj.length == 0);
	}

	public static boolean isEmpty(Object obj) {
		return (obj == null);
	}

	public static void assertNotEmpty(CharSequence obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}
	
	public static void assertNotEmpty(Map<?, ?> obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(Object[] obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(Object obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(JaDate obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(JaDateTime obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(JaTime obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(EnumValue<?> obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}

	public static void assertNotEmpty(Collection<?> obj, String name) {
		if (isEmpty(obj)) {
			if (!Assert.isEmpty(name) && name.length() > 2
					&& "_V".equals(name.substring(name.length() - 2, name.length()))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new InputParamIsNullException().addScene("PARM", name);
		}
	}
	
	public static boolean notEmpty(CharSequence s) {
		return !isEmpty(s);
	}
	
	public static boolean notEmpty(JaDate obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(JaDateTime obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(JaTime obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(EnumValue<?> obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(Collection<?> obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(Map<?, ?> obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(Object[] obj) {
		return !isEmpty(obj);
	}
	
	public static boolean notEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
}
