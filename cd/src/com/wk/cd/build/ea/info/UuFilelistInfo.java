/**
 * Title: UuFilelistInfo.java
 * File Description: 文件清单表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-29
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:文件清单表
 * @author AutoGen
 */
@Table("UU_FILELIST")
@PrimaryKey({"list_uuid","file_work_seq"})
public class UuFilelistInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "文件清单表";

	/**
	 *清单UUID
	 */
	private String list_uuid;

	public static final String LIST_UUIDCN = "清单UUID";

	/**
	 *文件流水号
	 */
	private String file_work_seq;

	public static final String FILE_WORK_SEQCN = "文件流水号";

	/**
	 *文件目录
	 */
	private String file_path;

	public static final String FILE_PATHCN = "文件目录";

	/**
	 *文件名
	 */
	private String file_name;

	public static final String FILE_NAMECN = "文件名";

	/**
	 *文件类型
	 */
	private String file_type;

	public static final String FILE_TYPECN = "文件类型";

	/**
	 *文件大小
	 */
	private int file_size;

	public static final String FILE_SIZECN = "文件大小";

	/**
	 *所属包名
	 */
	private String package_name;

	public static final String PACKAGE_NAMECN = "所属包名";

	/**
	 *所属服务器名
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "所属服务器名";
	
	/**
	 *打包根路径
	 */
	private String storage_bk_path;

	public static final String STORAGE_BK_PATHCN = "打包根路径";

	/**
	 *目标包相对路径
	 */
	private String target_relative_path;

	public static final String TARGET_RELATIVE_PATHCN = "目标包相对路径";

	/**
	 *@return list_uuid 清单UUID
	 */
	public String getList_uuid() {
		return this.list_uuid;
	}

	/**
	 *@param list_uuid 清单UUID
	 */
	public void setList_uuid(String list_uuid) {
		this.list_uuid = list_uuid;
	}

	/**
	 *@return file_work_seq 文件流水号
	 */
	public String getFile_work_seq() {
		return this.file_work_seq;
	}

	/**
	 *@param file_work_seq 文件流水号
	 */
	public void setFile_work_seq(String file_work_seq) {
		this.file_work_seq = file_work_seq;
	}

	/**
	 *@return file_path 文件目录
	 */
	public String getFile_path() {
		return this.file_path;
	}

	/**
	 *@param file_path 文件目录
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 *@return file_name 文件名
	 */
	public String getFile_name() {
		return this.file_name;
	}

	/**
	 *@param file_name 文件名
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 *@return file_type 文件类型
	 */
	public String getFile_type() {
		return this.file_type;
	}

	/**
	 *@param file_type 文件类型
	 */
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	/**
	 *@return file_size 文件大小
	 */
	public int getFile_size() {
		return this.file_size;
	}

	/**
	 *@param file_size 文件大小
	 */
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	/**
	 *@return package_name 所属包名
	 */
	public String getPackage_name() {
		return this.package_name;
	}

	/**
	 *@param package_name 所属包名
	 */
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	/**
	 *@return server_name 所属服务器名
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name 所属服务器名
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}
	
	/**
	 *@return storage_bk_path 打包根路径
	 */
	public String getStorage_bk_path() {
		return this.storage_bk_path;
	}

	/**
	 *@param storage_bk_path 打包根路径
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}

	/**
	 *@return target_relative_path 目标包相对路径
	 */
	public String getTarget_relative_path() {
		return this.target_relative_path;
	}

	/**
	 *@param target_relative_path 目标包相对路径
	 */
	public void setTarget_relative_path(String target_relative_path) {
		this.target_relative_path = target_relative_path;
	}

}
