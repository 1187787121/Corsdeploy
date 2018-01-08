/**
 * Title: MoTagsInfo.java
 * File Description: 组件分类标签表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-11-16
 */

package com.wk.cd.module1.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:组件分类标签表
 * @author AutoGen
 */
@Table("MO_TAGS")
@PrimaryKey({"component_id","component_tag"})
public class MoTagsInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "组件分类标签表";

	/**
	 *组件ID
	 */
	private String component_id;

	public static final String COMPONENT_IDCN = "组件ID";

	/**
	 *组件分类标签
	 */
	private String component_tag;

	public static final String COMPONENT_TAGCN = "组件分类标签";
	
	/**
	 * 备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return component_id 组件ID
	 */
	public String getComponent_id() {
		return this.component_id;
	}

	/**
	 *@param component_id 组件ID
	 */
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	/**
	 *@return component_tag 组件分类标签
	 */
	public String getComponent_tag() {
		return this.component_tag;
	}

	/**
	 *@param component_tag 组件分类标签
	 */
	public void setComponent_tag(String component_tag) {
		this.component_tag = component_tag;
	}
	
	/**
	 * @return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 * @param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

	/**
	 * 构造函数
	 * @param component_id
	 * @param component_tag
	 */
	public MoTagsInfo(String component_id, String component_tag) {
		super();
		this.component_id = component_id;
		this.component_tag = component_tag;
	}

	/**
	 * 构造函数
	 */
	public MoTagsInfo() {
		super();
	}
	
}
