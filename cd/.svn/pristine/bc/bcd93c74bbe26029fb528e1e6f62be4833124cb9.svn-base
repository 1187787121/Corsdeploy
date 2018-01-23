/**
 * Title: FireflyUtil.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2017年12月29日
 */
package com.wk.cd.build.entity;

/**
 * Class Description: Firefly
 * @author chiss
 */
public class FireFlyUtil implements VersionTemplate{

	public static final String IP = "";//服务器地址
	public static final String DEVELOP = "";//开发库
	public static final String USER = "";//用户名
	public static final String PWD = "";//密码
	
	@Override
	public String checkout(String project_name, String local_path) {
		String s = "hff init -h" + IP + " -proj" + project_name + " -p" + DEVELOP + " -u" + USER + " -pwd" + PWD + " -d" + local_path;
		return s;
	}
	
	@Override
	public String download(String project_name, String local_path) {
		String s = "hff download -h" + IP + " -proj" + project_name + " -p" + DEVELOP + " -u" + USER + " -pwd" + PWD + " -d" + local_path;
		return s;
	}

	@Override
	public String commit() {
		return "hff submit -i -c coment -crid crid -r review";
	}

	@Override
	public String commit(String file) {
		return "hff submit -i -c coment -crid crid -r review " +file;
	}
	
	@Override
	public String add() {
		return "hff create";
	}
	
	@Override
	public String add(String file) {
		return "hff create " + file;
	}

	@Override
	public String update(String path) {
		return "hff bringover " + path;
	}


}
