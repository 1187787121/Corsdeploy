/**
 * Title: SQLActionRootInputBean.java
 * File Description: SQLActionBasic基础输入接口
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/8/2014
 */

package com.wk.cd.bean;

import java.util.List;

import com.wk.cd.enu.OPT_TYPE;

/**
 * Class Description: SQLActionBasic基础输入接口
 * @author lixl
 */
public class SQLActionRootInputBean extends ActionRootInputBean {
	private static final long serialVersionUID = -1794199007211070557L;

	/**
	* 操作类型
	*/
	private OPT_TYPE opt_type;

	public static final String OPT_TYPECN = "操作类型";

	/**
	 * SQL语句
	 */
	private String sql_text;

	public static final String SQL_TEXTCN = "SQL语句";

	/**
	 * 表名
	 */
	private String tbl_name;

	public static final String TBL_NAMECN = "表名";

	/**
	 * 列名
	 */
	private List<String> col_name;

	public static final String COL_NAMECN = "列名";
	
	/**
	* 深度拷贝构造函数
	* @param bean 
	*/
    public SQLActionRootInputBean(SQLActionRootInputBean bean){
		super(bean);
		this.opt_type = bean.getOpt_type();
		this.sql_text = bean.getSql_text();
    }

	/**
	* 构造函数
	*/
    public SQLActionRootInputBean(){
		super();
    }

    /**
	* @return OPT_TYPE 操作类型
	*/
	public OPT_TYPE getOpt_type(){
		return this.opt_type;
    }

	/**
	* @param opt_type 操作类型
	*/
	public void setOpt_type(OPT_TYPE opt_type){
		this.opt_type = opt_type;
    }

	/**
	 * @return String SQL语句
	 */
	public String getSql_text(){
		return this.sql_text;
	}

	/**
	 * @param sql_text SQL语句
	 */
	public void setSql_text(String sql_text){
		this.sql_text = sql_text;
	}

	/**
	 * @return String 表名
	 */
	public String getTbl_name(){
		return this.tbl_name;
	}

	/**
	 * @param tbl_name 表名
	 */
	public void setTbl_name(String tbl_name){
		this.tbl_name = tbl_name;
	}

	/**
	 * @return List<String> 列名
	 */
	public List<String> getCol_name(){
		return this.col_name;
	}

	/**
	 * @param col_name 列名
	 */
	public void setCol_name(List<String> col_name){
		this.col_name = col_name;
	}

	/**
	 * 克隆函数
	 * @return SQLActionRootInputBean 
	 */
	@Override
	public SQLActionRootInputBean clone(){
		return (SQLActionRootInputBean) super.clone();
	}
	
}

