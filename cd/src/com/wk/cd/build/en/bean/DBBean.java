/**
 * Title: DBBean.java
 * File Description: 数据库对象Bean
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

/**
 * Class Description: 数据库对象Bean
 * @author yangl
 */
public class DBBean {

	/**
	 * 数据库
	 */
	private String server_db;
	public static final String SERVER_DB_CN = "数据库";

	/**
	 * 数据库版本号
	 */
	private String db_bbk_ver;
	public static final String DB_BBK_VERCN = "数据库版本号";

	/**
	 * @return 数据库
	 */
	public String getServer_db() {
		return server_db;
	}

	/**
	 * @param 数据库
	 */
	public void setServer_db(String server_db) {
		this.server_db = server_db;
	}

	/**
	 * @return 数据库版本号
	 */
	public String getDb_bbk_ver() {
		return db_bbk_ver;
	}

	/**
	 * @param 数据库版本号
	 */
	public void setDb_bbk_ver(String db_bbk_ver) {
		this.db_bbk_ver = db_bbk_ver;
	}

}
