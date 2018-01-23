/**
 * Title: SVNUtil.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017Äê12ÔÂ29ÈÕ
 */
package com.wk.cd.build.entity;

/**
 * Class Description: SVN 
 * @author Administrator
 */
public class SVNUtil implements VersionTemplate{

	@Override
	public String checkout(String remote_path, String local_path) {
		return "co " + remote_path + " " + local_path;
	}

	@Override
	public String download(String remote_path, String local_path) {
		return "export " + remote_path + " " + local_path;
	}
	
	@Override
	public String commit() {
		return "ci -m " + "commit";
	}
	
	@Override
	public String commit(String file) {
		return "ci " + file + "-m commit ";
	}

	@Override
	public String add() {
		return "add * --force";
	}

	@Override
	public String add(String file) {
		return "add " + file + " --force";
	}
	
	@Override
	public String update(String local_path) {
		return "up " + local_path;
	}

}
