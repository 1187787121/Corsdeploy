/**
 * Title: ParamInCmdBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月19日
 */
package com.wk.cd.module1.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.module1.bean.ParamInCmdBean;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class ParamInCmdBean {
	String group;

	String param_name;

	String param_value;

	int index;

	public ParamInCmdBean() {
		super();
	}

	/**
	 * 构造函数
	 * @param group
	 * @param param_name
	 * @param param_value
	 * @param index
	 */
	public ParamInCmdBean(String group, String param_name, String param_value, int index) {
		super();
		this.group = group;
		this.param_name = param_name;
		this.param_value = param_value;
		this.index = index;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static List<ParamInCmdBean> coypList(List<ParamInfo> list){
		List<ParamInCmdBean> list_cmd = new ArrayList<ParamInCmdBean>();
		for(ParamInfo info : list){
			list_cmd.addAll(copy(info));
		}
		return list_cmd;
		
	}

	private static List<ParamInCmdBean> copy(ParamInfo info) {
		List<ParamInCmdBean> list = new ArrayList<ParamInCmdBean>();
		List<String> values = getParamList(info.getParam_value());
		int index = 1;
		for (String value : values) {
			ParamInCmdBean bean = new ParamInCmdBean(info.getParam_group(), info.getParam_name(), value, index++);
			list.add(bean);
		}

		return list;

	}

	private static List<String> getParamList(String value) {
		List<String> list = new ArrayList<String>();
		if (Assert.isEmpty(value)) {
			return list;
		}
		String[] values = null;
		if (value.contains("-,-")) {
			values = value.split("-,-");
			list = Arrays.asList(values);
		} else {
			list.add(value);
		}
		return list;
	}
}
