/**
 * Title: SystemParamManager.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月21日
 */
package com.wk.cd.module1;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.wk.cd.module1.exc.CheckElementNodeIsEmptyException;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.xml.XmlTags;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;

/**
 * Class Description: 系统参数管理类
 * @author zhangj
 */
public class SystemParamManager {
	
	private static final Map<String,ParamInfo> param = new HashMap<String,ParamInfo>() ;
	
	public static void getparams(){
		Document doc = CfgTool.getSystemParamDom("system_param.xml");
		NodeList node_list = doc.getElementsByTagName("params");
		if (node_list == null || node_list.getLength() == 0) {
			throw new CheckElementNodeIsEmptyException().addScene("TAG", "params");
		}
		Element params = (Element) node_list.item(0);

		NodeList sub_params = params.getElementsByTagName(XmlTags.PARAM);
		if (!Assert.isEmpty(sub_params)) {
			for (int i = 0; i < sub_params.getLength(); i++) {
				Element param_ele = (Element) sub_params.item(i);
				String param_name = param_ele.getAttribute(XmlTags.NAME);
				String param_cn_name = param_ele.getAttribute(XmlTags.CNNAME);
				String group = param_ele.getAttribute(XmlTags.GROUP);
				String param_desc = param_ele.getAttribute(XmlTags.DESC);
				String default_value = param_ele.getAttribute(XmlTags.DEFAULT);

				ParamInfo info = new ParamInfo();
				info.setParam_group(group);
				info.setParam_name(param_name);
				info.setParam_cn_name(param_cn_name);
				info.setParam_bk_desc(param_desc);
				info.setParam_value(default_value);
				param.put(param_name, info);
			}
		}
	}
	
	public static ParamInfo getTaskNo(String task_no){
		return param.get("task_no");
	}
	
	public static void main(String[] args) {
		ParamInfo info = getTaskNo("");
		System.out.println(info.getParam_name());
		System.out.println(info.getParam_cn_name());
	}

	
}
