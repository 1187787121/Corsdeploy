/**
 * Title: ParamBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月28日
 */
package com.wk.cd.module1.bean;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.module1.bean.ParamBean;


/**
 * Class Description: 
 * @author Zhangj
 */
public class ParamBean {
		
	/**
	 *参数名
	 */
	private String param_name;

	public static final String PARAM_NAMECN = "参数名";

	/**
	 *参数类型
	 */
	private PARAM_TYPE param_type;

	public static final String PARAM_TYPECN = "参数类型";

	/**
	 *参数值
	 */
	private String default_value;

	public static final String PARAM_VALUECN = "参数值";

	/**
	 *参数中文名
	 */
	private String param_cn_name;

	public static final String PARAM_CN_NAMECN = "参数中文名";

	/**
	 *参数描述
	 */
	private String param_desc;

	public static final String PARAM_BK_DESCCN = "参数描述";

	/**
	 *是否可修改
	 */
	private MODIFY_FLAG param_modify_flag;

	public static final String PARAM_MODIFY_FLAGCN = "是否可修改";


	/**
	 *参数分组
	 */
	private String param_group;

	public static final String PARAM_GROUPCN = "参数分组";

	/**
	 *阶段号
	 */
	private int phase_no;

	public static final String PHASE_NOCN = "阶段号";



	/**
	 *@return param_name 参数名
	 */
	public String getParam_name() {
		return this.param_name;
	}

	/**
	 *@param param_name 参数名
	 */
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	/**
	 *@return param_type 参数类型
	 */
	public PARAM_TYPE getParam_type() {
		return this.param_type;
	}

	/**
	 *@param param_type 参数类型
	 */
	public void setParam_type(PARAM_TYPE param_type) {
		this.param_type = param_type;
	}



	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public String getParam_desc() {
		return param_desc;
	}

	public void setParam_desc(String param_desc) {
		this.param_desc = param_desc;
	}

	public void setPhase_no(int phase_no) {
		this.phase_no = phase_no;
	}

	/**
	 *@return param_cn_name 参数中文名
	 */
	public String getParam_cn_name() {
		return this.param_cn_name;
	}

	/**
	 *@param param_cn_name 参数中文名
	 */
	public void setParam_cn_name(String param_cn_name) {
		this.param_cn_name = param_cn_name;
	}



	/**
	 *@return param_modify_flag 是否可修改
	 */
	public MODIFY_FLAG getParam_modify_flag() {
		return this.param_modify_flag;
	}

	/**
	 *@param param_modify_flag 是否可修改
	 */
	public void setParam_modify_flag(MODIFY_FLAG param_modify_flag) {
		this.param_modify_flag = param_modify_flag;
	}


	/**
	 *@return param_group 参数分组
	 */
	public String getParam_group() {
		return this.param_group;
	}

	/**
	 *@param param_group 参数分组
	 */
	public void setParam_group(String param_group) {
		this.param_group = param_group;
	}

	/**
	 *@return phase_no 阶段号
	 */
	public int getPhase_no() {
		return this.phase_no;
	}
	
	
	public static ParamInfo copyToInfo(ParamBean bean){
		ParamInfo info = new ParamInfo();
		info.setParam_name(bean.getParam_name());
		info.setParam_group(bean.getParam_group());
		Integer phase_no = bean.getPhase_no();
		if(phase_no == null || phase_no == 0){
			info.setParam_value(bean.getDefault_value());
		}else{
			info.setRef(bean.getDefault_value());
			info.setPhase_no(phase_no-1);
		}
		return info;
	}
	
	public static ParamInfo[] copyToInfoArray(ParamBean[] beans){
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		if(!Assert.isEmpty(beans)){
			for(ParamBean bean : beans){
				 ParamInfo info = copyToInfo(bean);
				 params.add(info);
			}
		}
		return params.toArray(new ParamInfo[params.size()]);
	}
	
	

}
