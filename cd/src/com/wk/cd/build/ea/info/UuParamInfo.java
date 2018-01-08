/**
 * Title: UuParamInfo.java
 * File Description: 模板组件参数表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:模板组件参数表
 * @author AutoGen
 */
@Table("UU_PARAM")
@PrimaryKey({"param_uuid","param_name"})
public class UuParamInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "模板组件参数表";

	/**
	 *参数UUID
	 */
	private String param_uuid;

	public static final String PARAM_UUIDCN = "参数UUID";

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
	private String param_value;

	public static final String PARAM_VALUECN = "参数值";

	/**
	 *参数中文名
	 */
	private String param_cn_name;

	public static final String PARAM_CN_NAMECN = "参数中文名";

	/**
	 *参数描述
	 */
	private String param_bk_desc;

	public static final String PARAM_BK_DESCCN = "参数描述";

	/**
	 *是否可修改
	 */
	private MODIFY_FLAG param_modify_flag;

	public static final String PARAM_MODIFY_FLAGCN = "是否可修改";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *@return param_uuid 参数UUID
	 */
	public String getParam_uuid() {
		return this.param_uuid;
	}

	/**
	 *@param param_uuid 参数UUID
	 */
	public void setParam_uuid(String param_uuid) {
		this.param_uuid = param_uuid;
	}

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

	/**
	 *@return param_value 参数值
	 */
	public String getParam_value() {
		return this.param_value;
	}

	/**
	 *@param param_value 参数值
	 */
	public void setParam_value(String param_value) {
		this.param_value = param_value;
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
	 *@return param_bk_desc 参数描述
	 */
	public String getParam_bk_desc() {
		return this.param_bk_desc;
	}

	/**
	 *@param param_bk_desc 参数描述
	 */
	public void setParam_bk_desc(String param_bk_desc) {
		this.param_bk_desc = param_bk_desc;
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
	 *@return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
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

	/**
	 *@param phase_no 阶段号
	 */
	public void setPhase_no(int phase_no) {
		this.phase_no = phase_no;
	}
	
	public static UuParamInfo copy(UuParamInfo info){
		UuParamInfo bean = new UuParamInfo();
		bean.setParam_bk_desc(info.getParam_bk_desc());
		bean.setParam_cn_name(info.getParam_cn_name());
		bean.setParam_group(info.getParam_group());
		bean.setParam_modify_flag(info.getParam_modify_flag());
		bean.setParam_name(info.getParam_name());
		bean.setParam_type(info.getParam_type());
		if(!Assert.isEmpty(info.getParam_value())){
			bean.setParam_value(info.getParam_value().trim());
		}
		bean.setPhase_no(info.getPhase_no());
		return bean;
		
	}
	
	public static ParamInfo copyToParamInfo(UuParamInfo info){
		ParamInfo bean = new ParamInfo();
		bean.setParam_bk_desc(info.getParam_bk_desc());
		bean.setParam_cn_name(info.getParam_cn_name());
		bean.setParam_group(info.getParam_group());
		bean.setParam_modify_flag(info.getParam_modify_flag());
		bean.setParam_name(info.getParam_name());
		bean.setParam_type(info.getParam_type());
		bean.setParam_value(info.getParam_value());
		bean.setPhase_no(info.getPhase_no());
		return bean;
	}
	
	public static ParamInfo[] copyToParamInfos(List<UuParamInfo> infos){
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		if(!Assert.isEmpty(infos)){
			for(UuParamInfo info : infos){
				list.add(copyToParamInfo(info));
			}
		}
		
		return list.toArray(new ParamInfo[list.size()]);
	}
	
	public static List<ParamInfo> copyToParamInfoList(List<UuParamInfo> infos){
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		if(!Assert.isEmpty(infos)){
			for(UuParamInfo info : infos){
				list.add(copyToParamInfo(info));
			}
		}
		
		return list;
	}
	
	public static List<PhaseParam> copyToPhaseParams(List<UuParamInfo> infos){
		List<PhaseParam> list = new ArrayList<PhaseParam>();
		if(!Assert.isEmpty(infos)){
			for (UuParamInfo info : infos) {
				list.add(copyToPhaseParam(info));
			}
		}
		return list;
	}

	 
	private static PhaseParam copyToPhaseParam(UuParamInfo info) {
		if (info == null) {
			return null;
		}
		PhaseParam bean = new PhaseParam();
		bean.setParam_value(info.getParam_value());
		bean.setParam_cn_name(info.getParam_cn_name());
		bean.setParam_bk_desc(info.getParam_bk_desc());
		bean.setParam_name(info.getParam_name());
		bean.setParam_type(info.getParam_type());
		bean.setRef(null);
		bean.setModify_flag(MODIFY_FLAG.YES);
		bean.setIndex(1);
		bean.setIp(null);
		bean.setSensitive_flag(false);
		return bean;
	}

}
