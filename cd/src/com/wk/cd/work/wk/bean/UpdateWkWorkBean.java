/**
 * Title: UpdateWkWorkBean.java
 * File Description:更新任务定义表Wk_Work信息接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:更新任务定义表Wk_Work信息接口  
 * @author tlw
 */
public class UpdateWkWorkBean {
	
	/**
	 * 任务名称
	 */
	private String work_cn_name;
	
	public static final String WORK_CN_NAMECN = "任务名称";
	
	/**
	 * 任务描述
	 */
	private String work_bk_desc;
	
	public static final String WORK_BK_DESCCN = "任务描述";
	
	/**
	 * 任务类型
	 */
	private FUN_TYPE work_fun_type;
	
	public static final String WORK_FUN_TYPECN = "任务类型";
	
	/**
	 * 是否发布
	 */
	private IS_PUBLISH is_publish;
	
	public static final String IS_PUBLISHCN = "是否发布";
	
	/**
	 * 修改日期
	 */
	private JaDate modify_bk_date;
	
	public static final String MODIFY_BK_DATECN = "修改日期";

	/**
	 * 修改时间
	 */
	private JaTime modify_bk_time;
	
	public static final String MODIFY_BK_TIMECN = "修改时间";
	
	/**
	 * 修改用户
	 */
	private String modify_user_id;
	
	public static final String MODIFY_USER_IDCN = "修改用户";
	
	/**
	 * @return work_cn_name 任务名称
	 */
	public String getWork_cn_name() {
		return work_cn_name;
	}

	/**
	 * @param work_cn_name 任务名称
	 */
	public void setWork_cn_name(String work_cn_name) {
		this.work_cn_name = work_cn_name;
	}

	/**
	 * @return work_bk_desc 任务描述
	 */
	public String getWork_bk_desc() {
		return work_bk_desc;
	}

	/**
	 * @param work_bk_desc 任务描述
	 */
	public void setWork_bk_desc(String work_bk_desc) {
		this.work_bk_desc = work_bk_desc;
	}

	/**
	 * @return work_fun_type 任务类型
	 */
	public FUN_TYPE getWork_fun_type() {
		return work_fun_type;
	}

	/**
	 * @param work_fun_type 任务类型
	 */
	public void setWork_fun_type(FUN_TYPE work_fun_type) {
		this.work_fun_type = work_fun_type;
	}

	/**
	 * @return is_publish 是否发布
	 */
	public IS_PUBLISH getIs_publish() {
		return is_publish;
	}

	/**
	 * @param is_publish 是否发布
	 */
	public void setIs_publish(IS_PUBLISH is_publish) {
		this.is_publish = is_publish;
	}

	/**
	 * @return modify_bk_date 修改日期
	 */
	public JaDate getModify_bk_date() {
		return modify_bk_date;
	}

	/**
	 * @param modifyBkDate 修改日期
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 * @return modify_bk_time 修改时间
	 */
	public JaTime getModify_bk_time() {
		return modify_bk_time;
	}

	/**
	 * @param modifyBkTime 修改时间
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 * @return modify_user_id 修改用户
	 */
	public String getModify_user_id() {
		return modify_user_id;
	}

	/**
	 * @param modify_user_id 修改用户
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	
	
}
