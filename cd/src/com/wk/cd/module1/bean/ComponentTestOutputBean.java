/**
 * Title: ComponentTestOutputBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年8月24日
 */
package com.wk.cd.module1.bean;

import java.util.List;

import com.wk.cd.module1.Result;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author zhangj
 */
public class ComponentTestOutputBean  extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4835568594391168786L;
	
	/**
	 * 日志路径
	 */
	private String log_file_path ;
	
	public static final String LOG_FILE_PATHCN = "日志文件全路径";
	
	private List<Result> result;

	/**
	 * @return log_file_path
	 */
	public String getLog_file_path() {
		return log_file_path;
	}

	/**
	 * @param log_file_path
	 */
	public void setLog_file_path(String log_file_path) {
		this.log_file_path = log_file_path;
	}

	/**
	 * @return result
	 */
	public List<Result> getResult() {
		return result;
	}

	/**
	 * @param result
	 */
	public void setResult(List<Result> result) {
		this.result = result;
	}

	
	
}
