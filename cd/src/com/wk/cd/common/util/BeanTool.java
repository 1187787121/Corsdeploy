/**
 * Title: BeanTool.java
 * File Description:  Bean������
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-16
 */
package com.wk.cd.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.common.bean.BFieldNameBean;
import com.wk.cd.common.bean.CLSLSTP;
import com.wk.cd.common.bean.CLSP;
import com.wk.cd.common.bean.ClassPropertiesNBean;
import com.wk.cd.common.bean.FieldBean;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.NewInstanceErrorException;
import com.wk.cd.exc.PropertiesToBeanErrorException;
import com.wk.db.EnumValue;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;
import com.wk.util.BeanProperty;
import com.wk.util.BeanUtil;
import com.wk.util.Class2;
import com.wk.util.ConverterUtil;
import com.wk.util.Field2;
import com.wk.util.JSON;
import com.wk.util.JSONCaseType;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;
import com.wk.util.MapBean;

/**
 * Class Description: Bean������
 * @author lixl
 */
public class BeanTool {
	private static final Log logger = LogFactory.getLog();
	private static final String DEFAULT_KEY = "_DEFAULT_KEY";

	/**
	 * ��Bean����ת����CLASS����
	 * @param _obj Ŀ�����
	 * @return ClassPropertiesNBean CLASS����
	 */
	public static ClassPropertiesNBean bean2Properties(Object _obj) {
		String fval = "";
		String fname = "";
		String ftype = "";
		Object val;
		Class<?> cls, cls2;

		cls = _obj.getClass();
		// ClassPropertiesNBean output = new
		// ClassPropertiesNBean(cls.getName());
		ClassPropertiesNBean output = new ClassPropertiesNBean();
		BeanProperty[] proAry = BeanUtil.getReadProperties(cls);
		for (BeanProperty pro : proAry) {
			fname = "";
			fval = "";
			ftype = "";

			if (pro.isFinal())
				continue;

			fname = pro.getName();

			val = pro.getValue(_obj);
			if (Assert.isEmpty(val))
				continue;

			if (pro.isList()) {
				List<?> lstval = (List<?>) val;
				for (Object lstobj : lstval) {
					// ����ǻ�����������ֱ����ӵ�List�У�����fname����Ϊ"_baseField_"+fname
					cls2 = lstobj.getClass();
					if (isSimpleType(cls2)) {
						fval = getValue(lstobj);
						ftype = getType(cls2);
						output.addList(cls2.getName(), fname, "_baseField_" + fname, fval, ftype);
					} else {
						// �����Bean���͵ݹ����Bean��������
						ClassPropertiesNBean sbean = bean2Properties(lstobj);
						List<String> lstKey = sbean.getAllStruFname();
						for (String key : lstKey) {
							Object v1 = sbean.getStruFval(key);
							if (Assert.isEmpty(v1)) {
								continue;
							}
							fval = getValue(v1);
							ftype = sbean.getStruFtype(key);
							output.addList(lstobj.getClass().getName(), fname, key, fval, ftype);
						}
					}
				}
			} else if (isContain(pro)) {
				ftype = getType(val.getClass());
				fval = getValue(val);
				output.addFstru("", fname, fval, ftype);
			} else if (pro.isJaDateTime()) {
				ftype = getType(val.getClass());
				fval = getValue(val);
				output.addFstru("", fname, fval, ftype);
			} else {
				ClassPropertiesNBean sbean = bean2Properties(val);
				List<CLSP> lst_clp = sbean.getAllStru();
				for (CLSP clp : lst_clp) {
					ftype = clp.getFtype();
					fval = clp.getFval();
					String nfname = clp.getFname();
					CLSP c = new CLSP(nfname, fval, ftype);
					c.setFstruname(fname);
					output.addFstru(c, nfname);
				}
			}
		}

		return output;
	}

	private static String getType(Class<?> type) {
		String ftype = type.getName();
		String[] t = ftype.split("\\.");
		ftype = t[t.length - 1];
		return ftype;
	}

	private static String getValue(Object obj) {
		String fval = "";
		if (obj instanceof Double) {
			fval = StringUtil.double2String((Double) obj);
		} else {
			fval = obj.toString();
		}
		return fval;
	}

	private static boolean isSimpleType(Class<?> type) {
		if (BeanUtil.isSimpleType(type)) {
			return true;
		} else if (EnumValue.class.isAssignableFrom(type)) {
			return true;
		} else if (type == JaDate.class) {
			return true;
		} else if (type == JaDateTime.class) {
			return true;
		} else if (type == JaTime.class) {
			return true;
		} else {
			return false;
		}

	}

	private static boolean isContain(BeanProperty pro) {
		if (BeanUtil.isSimpleType(pro.getType())) {
			return true;
		} else if (pro.isEnumValue()) {
			return true;
		} else if (pro.isJaDate()) {
			return true;
		} else if (pro.isJaDateTime()) {
			return true;
		} else if (pro.isJaTime()) {
			return true;
		}
		return false;
	}

	/**
	 * ����nbean�����ݷ��乹��ԭʼ����
	 * @author lixl (2014-11-18)
	 * @param nbean
	 * @param cls
	 * @return Object
	 */
	public static Object properties2Bean(ClassPropertiesNBean nbean) {
		Object bobj, val;
		String fval;
		String ftype;
		Class<?> cls;
		List<String> keyFname;
		List<String> keyLst;

		Assert.assertNotEmpty(nbean, "�ӿڲ���");
		Assert.assertNotEmpty(nbean.getClsname(), "������");

		try {
			bobj = getObjectByClassName(nbean.getClsname());
			// ������ͨBean����ֵ
			MapBean<Object> mapb = new MapBean<Object>(bobj);
			keyFname = nbean.getAllStruFname();
			for (String fname : keyFname) {
				fval = nbean.getStruFval(fname);
				ftype = nbean.getStruFtype(fname);
				val = StringUtil.parseVal(fval, ftype);
				mapb.put(fname, val);
			}

			// ����List����ֵ
			keyLst = nbean.getAllListNamme();
			for (String flstname : keyLst) {
				List<Object> lstval = new ArrayList<Object>();
				cls = getClazzByClassName(nbean.getLstclsname(flstname));
				CLSLSTP clslstp = nbean.getFlistCSLLSTP(flstname);
				if (BeanUtil.isSimpleType(cls)) {
					for (CLSP clsp : clslstp.getFclsp()) {
						lstval.add(StringUtil.parseVal(clsp.getFval(), clsp.getFtype()));
					}
				} else {
					for (CLSP clsp : clslstp.getFclsp()) {
						Object o = getObjectByClass(cls);
						MapBean<Object> mb = new MapBean<Object>(o);
						val = StringUtil.parseVal(clsp.getFname(), clsp.getFtype());
						mb.put(clsp.getFname(), val);
						lstval.add(o);
					}
				}
				mapb.put(flstname, lstval);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new PropertiesToBeanErrorException().addScene("CLASS", nbean.getClsname());
		}
		return bobj;
	}

	/**
	 * ������������Class
	 * @author lixl (2014-11-18)
	 * @param clsname
	 * @return Class<?>
	 */
	public static Class<?> getClazzByClassName(String clsname) {
		try {
			return Class.forName(clsname);
		} catch (ClassNotFoundException e) {
			logger.error(e.toString(), e);
			throw new NewInstanceErrorException().addScene("CLSNAME", clsname);
		}
	}

	/**
	 * ��������new����
	 * @author lixl (2014-11-26)
	 * @param T ����
	 * @param clsname ����
	 * @return T ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getInstanceByClassName(String clsname) {
		try {
			return (T) getInstanceByClzz(getClazzByClassName(clsname));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new NewInstanceErrorException().addScene("CLSNAME", clsname);
		}
	}

	/**
	 * ����class���ɶ���
	 * @author lixl (2014-12-4)
	 * @param T
	 * @param clzz
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getInstanceByClzz(Class<?> clzz) {
		try {
			return (T) clzz.newInstance();
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new NewInstanceErrorException().addScene("CLSNAME", clzz.getName());
		}
	}

	/**
	 * ����class���ɶ���
	 * @author lixl (2014-11-19)
	 * @param cls
	 * @return Object
	 */
	public static Object getObjectByClass(Class<?> cls) {
		try {
			return cls.newInstance();
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new NewInstanceErrorException().addScene("CLSNAME", cls.getName());
		}
	}

	/**
	 * �����������ɶ���
	 * @author lixl (2014-11-18)
	 * @param clsname
	 * @return Object
	 */
	public static Object getObjectByClassName(String clsname) {
		try {
			return getClazzByClassName(clsname).newInstance();
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new NewInstanceErrorException().addScene("CLSNAME", clsname);
		}
	}

	/**
	 * ����class name���ɶ���ʹ��injector���ɶ���
	 * @author lixl (2014-12-4)
	 * @param T
	 * @param clsname
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByClassName(String clsname) {
		return (T) getBeanByClzz(getClazzByClassName(clsname));
	}

	/**
	 * ����class����ʵ����ʹ��injector���ɶ���
	 * @author lixl (2014-12-4)
	 * @param T
	 * @param clzz
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByClzz(Class<?> clzz) {
		Injector injector = Controller.getInstance().getInjector();
		return (T) injector.getBean(clzz);
	}

	/**
	 * ��beanת����ServiceData, ע�⣺ֻ������һ�ֶ�,list֮�಻����
	 * @author lixl (2014-12-10)
	 * @param obj
	 * @return ServiceData
	 */
	public static ServiceData bean2sdo(Object obj) {
		ServiceData sdo = new ServiceData();
		ConverterUtil.bean2ServiceData(obj, sdo);
		return sdo;
	}

	/**
	 * ��ServiceDataת����Bean
	 * @author lixl (2014-12-10)
	 * @param T
	 * @param sdo
	 * @param clzz
	 * @return T
	 */
	public static <T> T sdo2bean(ServiceData sdo, Class<T> clzz) {
		return ConverterUtil.serviceData2Bean(sdo, clzz);
	}

	/**
	 * �ж�����ĳ�� field �Ƿ���� �ؼ���
	 * @author lixl (2014-12-23)
	 * @param clzz ��
	 * @param fname ������(��ȫ������������ֵ�)
	 * @param keyword �ؼ���
	 * @return boolean �Ƿ����
	 */
	public static boolean fldHasKeyWord(Class<?> clzz, String fname, String keyword) {
		Map<String, Boolean> mp = new HashMap<String, Boolean>();
		Field[] flds = clzz.getDeclaredFields();
		for (Field f : flds) {
			String fldstr = f.toString();
			if (fldstr.contains(fname)) {
				boolean has = fldstr.contains(keyword);
				Boolean v = mp.get(fname);
				if (v != null) {
					v = v && has;
					mp.remove(fname);
					mp.put(fname, v);
				} else {
					Boolean nv = Boolean.valueOf(has);
					mp.put(fname, nv);
				}
			}
		}
		Boolean rb = mp.get(fname);
		if (rb != null) {
			return rb.booleanValue();
		} else {
			throw new CorsManagerSystemErrorException("CMS_NO_SUCH_FIELD").addScene("E", fname);
		}
	}

	/**
	 * �ж϶���ĳ�����Ƿ���� �ؼ���
	 * @author lixl (2014-12-23)
	 * @param obj ����
	 * @param fname ������
	 * @param keywork �ؼ���
	 * @return boolean �Ƿ����
	 */
	public static boolean fldHasKeyWork(Object obj, String fname, String keywork) {
		return fldHasKeyWord(obj.getClass(), fname, keywork);
	}

	private static Map<String, Map<String, String>> fldMapStruCname(String key, Object obj) {
		Map<String, Map<String, String>> mp = new HashMap<String, Map<String, String>>();
		Map<String, String> m = fldMapCname(obj);
		mp.put(key, m);
		BeanProperty[] pros = BeanUtil.getReadProperties(obj.getClass());
		for (BeanProperty pro : pros) {
			if (!isContain(pro)) {
				Object val = pro.getValue(obj);
				if (val == null)
					continue;
				Map<String, String> m2 = fldMapCname(val);
				mp.put(pro.getName(), m2);
			}
		}
		return mp;
	}

	private static Object getVal(Field2 fld, Object obj) {
		try {
			return fld.get(obj);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	private static Map<String, String> fldMapCname(Object obj) {
		Class2 c2 = Class2.get(obj.getClass());
		Map<String, String> out = new HashMap<String, String>();
		Field2[] flds = c2.getAllFields();
		for (Field2 fld : flds) {
			String fname = fld.getName();
			int len = fname.length();
			String sufname = fname.substring(len - 2, len);
			// ȷ���ֶ���������
			if (fld.isFinal() && fld.isStatic() && fld.isPublic() && sufname.equals("CN")) {
				fname = fname.substring(0, fname.length() - 2).toLowerCase();
				String val = getValue(getVal(fld, obj));
				out.put(fname, val);
			}
		}
		return out;
	}

	/**
	 * ���ݶ������ɶ���ṹ, ������List������ʱ��֧�� Description:
	 * @param obj Bean����
	 * @return
	 */
	public static Map<String, List<BFieldNameBean>> objBean2BFld(Object obj) {
		Assert.assertNotEmpty(obj, "����");

		Map<String, List<BFieldNameBean>> mpbf = new HashMap<String, List<BFieldNameBean>>();
		// ӳ�������ƶ�Ӧ��ֵ
		ClassPropertiesNBean nb = bean2Properties(obj);
		// ӳ�������ƶ�Ӧ������
		Map<String, Map<String, String>> mp = fldMapStruCname(DEFAULT_KEY, obj);
		List<CLSP> fns = nb.getAllStru();
		for (CLSP fld : fns) {
			String fname = fld.getFname();
			String fstruname = !Assert.isEmpty(fld.getFstruname()) ? fld.getFstruname() : DEFAULT_KEY;
			Map<String, String> m = mp.get(fstruname);
			String fcname = m.get(fname);
			fcname = Assert.isEmpty(fcname) ? fname : fcname;
			BFieldNameBean bf = new BFieldNameBean();
			bf.setFname(fld.getFname());
			bf.setFval(fld.getFval());
			bf.setFcname(fcname);
			bf.setFtype(fld.getFtype());
			List<BFieldNameBean> lbf = mpbf.get(fstruname);
			if (Assert.isEmpty(lbf)) {
				lbf = new ArrayList<BFieldNameBean>();
				mpbf.put(fstruname, lbf);
			}
			lbf.add(bf);
		}
		// List��֧��

		return mpbf;
	}

	/**
	 * ƴ�Ӷ���Json�ַ��� Description:
	 * @param json Json�ַ���List
	 * @return
	 */
	public static String concatJsonStr(List<String> json) {
		Assert.assertNotEmpty(json, "json");
		StringBuffer b = new StringBuffer();
		b.append("[");
		int c = 0;
		for (String s : json) {
			if (Assert.isEmpty(s))
				continue;
			if (c++ != 0)
				b.append(",");
			b.append(s);
		}
		b.append("]");
		return b.toString();
	}

	/**
	 * ����ת����json String [{fcname:'�ӿڣ�', fname:'user_id', fval:'10001'}, ...]
	 * @param obj
	 * @return String
	 */
	public static String bean2JsonStr(Object obj) {
		List<String> jsonstrlst = new ArrayList<String>();
		jsonstrlst.add(beanFld2JsonStr(objBean2BFld(obj), false, false));
		Object val;
		Class<?> cls;
		cls = obj.getClass();
		String fcname = "";
		BeanProperty[] proAry = BeanUtil.getReadProperties(cls);
		Map<String, Map<String, String>> mp = fldMapStruCname(DEFAULT_KEY, obj);
		for (BeanProperty pro : proAry) {
			if (pro.isFinal())
				continue;

			val = pro.getValue(obj);
			if (Assert.isEmpty(val))
				continue;

			fcname = mp.get(DEFAULT_KEY).get(pro.getName());
			if (!isKeytoShow(fcname))
				continue;
			fcname = fcname.substring(0, fcname.length() - 2);

			if (pro.isList()) {
				logger.debug("beanTool--------->�б�List");
				List<?> lstval = (List<?>) val;
				if (Assert.isEmpty(lstval))
					continue;
				jsonstrlst.add(list2JsonStr(lstval, pro.getName(), fcname));
			} else if (pro.isArray()) {
				logger.debug("beanTool--------->����Array");
				StringBuffer sb = new StringBuffer();
				sb.append("{'fcname':'").append(fcname).append("','fname':'").append(pro.getName()).append("','fval':[");
				Object[] aryval = (Object[]) pro.getValue(obj);
				int c = 0;
				for (Object ob : aryval) {
					if (c++ != 0)
						sb.append(",");
					sb.append("'").append(ob.toString()).append("'");
				}
				sb.append("],'ftype':'").append(pro.getValue(obj).getClass().getSimpleName()).append("','shtype':'Array'}");
				jsonstrlst.add(sb.toString());
			}
		}

		return BeanTool.concatJsonStr(jsonstrlst);
	}

	/**
	 * @param obj
	 * @return String
	 */
	public static String beanToJsonStr(Object obj) {
		List<FieldBean> jsonstrlst = new ArrayList<FieldBean>();
		jsonstrlst.addAll(beanFldToJsonStr(objBean2BFld(obj), false, false));
		Object val;
		Class<?> cls;
		cls = obj.getClass();
		String fcname = "";
		BeanProperty[] proAry = BeanUtil.getReadProperties(cls);
		Map<String, Map<String, String>> mp = fldMapStruCname(DEFAULT_KEY, obj);
		for (BeanProperty pro : proAry) {
			if (pro.isFinal())
				continue;
			val = pro.getValue(obj);
			if (Assert.isEmpty(val))
				continue;
			fcname = mp.get(DEFAULT_KEY).get(pro.getName());
			if (!isKeytoShow(fcname))
				continue;
			fcname = fcname.substring(0, fcname.length() - 2);
			if (pro.isList()) {
				logger.debug("beanTool--------->�б�List");
				List<?> lstval = (List<?>) val;
				if (Assert.isEmpty(lstval))
					continue;
				jsonstrlst.add(listToJsonStr(lstval, pro.getName(), fcname));
			} else if (pro.isArray()) {
				logger.debug("beanTool--------->����Array");
				StringBuffer sb = new StringBuffer();
				FieldBean field = new FieldBean();
				field.setFcname(fcname);
				field.setFname(pro.getName());
				field.setFtype(pro.getValue(obj).getClass().getSimpleName());
				field.setShtype("Array");
				Object[] aryval = (Object[]) pro.getValue(obj);
				int c = 0;
				for (Object ob : aryval) {
					if (c++ != 0)
						sb.append(",");
					sb.append(ob.toString());
				}
				field.setFval(sb.toString());
				jsonstrlst.add(field);
			}
		}
		return BeanTool.concatToJsonStr(jsonstrlst);
	}

	/**
	 * ƴ�Ӷ���Json�ַ��� Description:
	 * @param json Json�ַ���List
	 * @return
	 */
	public static String concatToJsonStr(List<FieldBean> jsonstrlst) {
        logger.debug("Json�ַ�����[{}]", Assert.isEmpty(jsonstrlst) ? jsonstrlst : Arrays.toString(jsonstrlst.toArray()));
		return JSON.fromObject(jsonstrlst, JSONCaseType.DEFAULT);
	}

	/**
	 * List����ת����Json�ַ��� Description:
	 * @param lst_obj list_object
	 * @param lst_name list_name
	 * @param string
	 * @param string
	 * @return json String
	 */
	public static FieldBean listToJsonStr(List<?> lst_obj, String lst_name, String lst_cn_name) {
		Assert.assertNotEmpty(lst_obj, "lst_obj");
		Assert.assertNotEmpty(lst_name, "lst_name");
		FieldBean field = new FieldBean();
		field.setFcname(lst_cn_name);
		field.setFname(lst_name);
		List<FieldBean> fd_lst = new ArrayList<FieldBean>();
		for (Object obj : lst_obj) {
			FieldBean cache = new FieldBean();
			cache.setField_list(beanFldToJsonStr(objBean2BFld(obj), true, true));
			fd_lst.add(cache);
			//fd_lst.addAll(beanFldToJsonStr(objBean2BFld(obj), true, true));
		}
		field.setField_list(fd_lst);
		field.setFtype("List");
		field.setShtype("List");
		return field;
	}

	/**
	 * @param beans ����ӿ�Bean
	 * @param isadd_bkt �Ƿ���Ҫ���"["
	 * @return String �ַ���
	 */
	public static List<FieldBean> beanFldToJsonStr(Map<String, List<BFieldNameBean>> mlbean, boolean isadd_bkt, boolean isfield_show) {
		Assert.assertNotEmpty(mlbean, "BFieldNameBean");
		List<FieldBean> field_list = new ArrayList<FieldBean>();
		for (Map.Entry<String, List<BFieldNameBean>> entry : mlbean.entrySet()) {
			// valueֵ����
			List<BFieldNameBean> beans = entry.getValue();
			for (BFieldNameBean bean : beans) {
				String fcname = bean.getFcname();
				if (!(isKeytoShow(fcname) || isfield_show))
					continue;
				FieldBean field = new FieldBean();
				if (isKeytoShow(fcname)) {
					fcname = fcname.substring(0, fcname.length() - 2);
				}
				if (!Assert.isEmpty(bean.getFcname())) {
					field.setFcname(fcname);
				}
				if (!Assert.isEmpty(bean.getFname())) {
					field.setFname(bean.getFname());
				}
				if (!Assert.isEmpty(bean.getFval())) {
					field.setFval(bean.getFval());
				}
				if (!Assert.isEmpty(bean.getFtype())) {
					field.setFtype(bean.getFtype());
				}
				field.setShtype("String");
				field_list.add(field);
				logger.debug("BeanTool--------------->һ�㳣����Ϣ[{}]", field.toString());
			}
		}
		return field_list;
	}

	/**
	 * ����ת����json String [{fcname:'�ӿڣ�', fname:'user_id', fval:'10001'}, ...]
	 * @param obj
	 * @return String
	 */
	public static String obj2JsonStr(Object obj) {
		return beanFld2JsonStr(objBean2BFld(obj), true, false);
	}

	/**
	 * List����ת����Json�ַ��� Description:
	 * @param lst_obj list_object
	 * @param lst_name list_name
	 * @param string
	 * @param string
	 * @return json String
	 */
	public static String list2JsonStr(List<?> lst_obj, String lst_name, String lst_cn_name) {
		Assert.assertNotEmpty(lst_obj, "lst_obj");
		Assert.assertNotEmpty(lst_name, "lst_name");

		StringBuffer sb = new StringBuffer();
		sb.append("{'fcname':'").append(lst_cn_name).append("','fname':'").append(lst_name).append("','fval':[");
		int c = 0;
		for (Object obj : lst_obj) {
			if (c++ != 0)
				sb.append(",");
			sb.append(beanFld2JsonStr(objBean2BFld(obj), true, true));
		}
		sb.append("],'ftype':'List','shtype':'List'}");
		return sb.toString();
	}

	/**
	 * ��Beans����ƴ�ӳ�Json�����ַ��� [{fcname:'�ӿڣ�', fname:'user_id', fval:'10001'}, ...]
	 * @param beans ����ӿ�Bean
	 * @param isadd_bkt �Ƿ���Ҫ���"["
	 * @return String �ַ���
	 */
	public static String beanFld2JsonStr(Map<String, List<BFieldNameBean>> mlbean, boolean isadd_bkt, boolean isfield_show) {
		Assert.assertNotEmpty(mlbean, "BFieldNameBean");
		StringBuffer b = new StringBuffer();
		int mc = 0;
		for (Map.Entry<String, List<BFieldNameBean>> entry : mlbean.entrySet()) {
			String key = entry.getKey();
			if (mc++ != 0) {
				b.append(",");
			}
			if (!DEFAULT_KEY.equals(key)) {
				b.append("'").append(key).append("':");
			}
			List<BFieldNameBean> beans = entry.getValue();
			if (isadd_bkt)
				b.append("[");
			int c = 0;
			for (BFieldNameBean bean : beans) {
				String fcname = bean.getFcname();
				if (!(isKeytoShow(fcname) || isfield_show))
					continue;
				if (c++ != 0) {
					b.append(",");
				}
				if (isKeytoShow(fcname)) {
					fcname = fcname.substring(0, fcname.length() - 2);
				}
				if (!Assert.isEmpty(bean.getFcname())) {
					b.append("{'fcname':'").append(fcname).append("'");
				}
				if (!Assert.isEmpty(bean.getFname())) {
					b.append(", 'fname':'").append(bean.getFname()).append("'");
				}
				if (!Assert.isEmpty(bean.getFval())) {
					b.append(", 'fval':'").append(bean.getFval()).append("'");
				}
				if (!Assert.isEmpty(bean.getFtype())) {
					b.append(", 'ftype':'").append(bean.getFtype()).append("'");
				}
				b.append(",'shtype':'String'}");
			}
			if (isadd_bkt)
				b.append("]");
		}
		logger.debug("BeanTool--------------->һ�㳣����Ϣ[{}]", b.toString());
		return b.toString();
	}

	/**
	 * Description: �жϽӿ���Ϣ�Ƿ�չʾ
	 * @param fcname
	 * @return
	 */
	private static boolean isKeytoShow(String fcname) {
		if (Assert.isEmpty(fcname) || fcname.length() < 2)
			return false;
		int len = fcname.length();
		String sufname = fcname.substring(len - 2, len);
		if ("_V".equals(sufname)) {
			return true;
		}
		return false;
	}

	/**
	 * ����Bean String ת����Bean list
	 * @author lixl (2015-2-7)
	 * @param str �ַ���
	 * @param iscnt_bkt �Ƿ����"[]"
	 * @return List<BFieldNameBean>
	 */
	public static List<BFieldNameBean> jsonStr2BeanFld(String str, boolean iscnt_bkt) {
		Assert.assertNotEmpty(str, "JsonBeanString");
		List<BFieldNameBean> bs = new ArrayList<BFieldNameBean>();
		String _str = str;
		if (iscnt_bkt) {
			_str = str.replace("[", "").replace("]", "");
		}
		String[] bstr = _str.split("}");
		for (String item : bstr) {
			BFieldNameBean b = new BFieldNameBean();
			String fcname = item.split("fcname':")[1].split("'")[0] + "'";
			String fname = item.split("fname':")[1].split("'")[0] + "'";
			String fval = item.split("fval':")[1].split("'")[0] + "'";
			String ftype = item.split("ftype':")[1].split("'")[0] + "'";
			b.setFcname(fcname);
			b.setFname(fname);
			b.setFval(fval);
			b.setFtype(ftype);
			bs.add(b);
		}

		return bs;
	}
}
