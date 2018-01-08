/**
 * Title: ClassPropertiesNBean.java
 * File Description: CLASS属性数据结构
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/16/2014
 */

package com.wk.cd.common.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Description:CLASS属性数据结构
 * @author lixl
 */
public class ClassPropertiesNBean {
	private String clsname;
	private Map<String, CLSP> fstru;
	private Map<String, CLSLSTP> flist;

	/**
	 * 构造函数
	 * @author lixl (2014-11-17)
	 * @param clsname 
	 */
	public ClassPropertiesNBean(String clsname){
		this.clsname = clsname;
		this.fstru = new HashMap<String, CLSP>();
		this.flist = new HashMap<String, CLSLSTP>();
	}

	/**
	 * 构造函数
	 * @author lixl (2014-11-17)
	 */
	public ClassPropertiesNBean(){
		this.clsname = "";
		this.fstru = new HashMap<String, CLSP>();
		this.flist = new HashMap<String, CLSLSTP>();
	}

	/**
	 * 增加一个域
	 * @author lixl (2014-11-17)
	 * @param fname 
	 * @param fval 
	 * @param ftype 
	 */
	public void addFstru(String fstruname, String fname, String fval, String ftype){
		CLSP c = new CLSP(fname,fval,ftype);
		c.setFstruname(fstruname);
		fstru.put(fname, c);
	}

	/**
	 * 新增一个域
	 * @author lixl (2015-2-10)
	 * @param clsp 
	 * @param fname 
	 */
	public void addFstru(CLSP clsp, String fname){
		fstru.put(fname, clsp);
	}

	/**
	 * 增加一个域列表
	 * @author lixl (2014-11-17)
	 * @param flstname 
	 * @param fname 
	 * @param fval 
	 * @param ftype 
	 */
	public void addList(String flstclsname, String flstname, 
						String fname, String fval, String ftype){
		CLSLSTP clslstp = flist.get(flstname);
		if (clslstp == null){
			clslstp = new CLSLSTP(flstclsname, flstname, fname, fval, ftype);
			flist.put(flstname, clslstp);
		}else{
			clslstp.addFclsp(new CLSP(fname, fval, ftype));
		}
	}
	
	/**
	 * 设置类名称
	 * @author lixl
	 * @param clsname
	 */
	public void setClsname(String clsname){
		this.clsname = clsname;
	}
	
	/**
	 * 返回类名称
	 * @author lixl
	 * @return String
	 */
	public String getClsname(){
		return this.clsname;
	}

	/**
	 * 设置List对应的类名
	 * @author lixl (2014-11-18)
	 * @param flstname 
	 * @param lstclsname 
	 */
	public void setLstclsname(String flstname, String lstclsname){
		this.flist.get(flstname).setFlstclsname(lstclsname);
	}

	/**
	 * 获取List类名
	 * @author lixl (2014-11-18)
	 * @param flstname 
	 * @return String 
	 */
	public String getLstclsname(String flstname){
		return this.flist.get(flstname).getFlstclsname();
	}

	/**
	 * 获取结构中包括多少个域 
	 * 例如： UserBean中包括：user_id user_name返回“2”
	 * @author lixl (2014-11-17)
	 * @return int 
	 */
	public int getFstruSize(){
		return fstru.size();
	}

	/**
	 * 获取结构中包括几个List类型域
	 * @author lixl (2014-11-17)
	 * @return int 
	 */
	public int getFlistSize(){
		return flist.size();
	}

	/**
	 * 获取指定List中包括多少个域记录
	 * 例如：UserBean中的lst1下有user_id user_name这样返回“2”
	 * @author lixl (2014-11-17)
	 * @param flstname 
	 * @return int 
	 */
	public int getFlistRcdSize(String flstname){
		return flist.get(flstname).getFclsp().size();
	}
	
	/**
	 * 获取List中对应域名下记录数
	 * @param flstname 列表名称
	 * @param fname 域名称
	 * @return
	 */
	public int getFlistFnameRcdSize(String flstname, String fname){
		CLSLSTP clslstp = flist.get(flstname);
		int count = 0;
		int size = 0;
		
		size = clslstp.getFclsp().size();
		for(int i=0; i<size; i++){
			CLSP clsp = clslstp.getFclsp().get(i);
			if(clsp.getFname().equals(fname)){
				count++;
			}
		}
		return count;
	}

	/**
	 * 获取指定List中包含多少个Bean记录 
	 * 例如List<UserBean> lst中lst的size 
	 * 按照本类的数据结构,即flsp的size=fval个数*fname个数 
	 * 因此bean的size=总记录/包含的域名个数
	 * @author lixl (2014-11-19)
	 * @param flstname 
	 * @return int 
	 */
	public int getFlistBeanRcdSize(String flstname){
		int s1 = getFlistRcdSize(flstname);
		int s2 = getFlistContaintFnameNum(flstname);
		return s1/s2;
	}

	/**
	 * 返回结构所有域名称
	 * @author lixl (2014-11-17)
	 * @return List<String> 
	 */
	public List<String> getAllStruFname(){
		List<String> fnameAll = new ArrayList<String>();
		for(String fname : fstru.keySet()) {
			fnameAll.add(fname);
		}
		return fnameAll;
	}

	/**
	 * 获取所有属性
	 * @author lixl (2015-2-9)
	 * @return List<CLSP> 
	 */
	public List<CLSP> getAllStru(){
		List<CLSP> clsAll = new ArrayList<CLSP>();
		for(Map.Entry<String, CLSP> cls : fstru.entrySet()) {
			clsAll.add(cls.getValue());
		}
		return clsAll;
	}
	
	/**
	 * 获取域名对应的域名称（域名就等于Key)
	 * @param fname
	 * @return
	 */
	public String getStruFname(String fname){
		return fstru.get(fname).getFname();
	}

	/**
	 * 返回域名对应的域值
	 * @author lixl (2014-11-17)
	 * @param fname 
	 * @return String 
	 */
	public String getStruFval(String fname){
		return fstru.get(fname).getFval();
	}

	/**
	 * 返回域名对应的域类型
	 * @author lixl (2014-11-17)
	 * @param fname 
	 * @return String 
	 */
	public String getStruFtype(String fname){
		return fstru.get(fname).getFtype();
	}

	/**
	 * 获取域名对应的值、类型
	 * @author lixl (2015-2-9)
	 * @param fname 
	 * @return CLSP 
	 */
	public CLSP getStru(String fname){
		return fstru.get(fname);
	}

	/**
	 * 获取所有List域名 
	 * 例如：lst lst2 lst3 
	 * @author lixl (2014-11-17)
	 * @return List<String> 
	 */
	public List<String> getAllListNamme(){
		List<String> fnameAll = new ArrayList<String>();
		for(String fname : flist.keySet()) {
			fnameAll.add(flist.get(fname).getFlstname());
		}
		return fnameAll;
	}
	
	/**
	 * 获取Bean中指定List名下对应的所有域名集合 
	 * 例如：lst下包括{user_id, user_name} 
	 * @param flstname 数组名称
	 * @return
	 */
	public String[] getAllListFname(String flstname){
		String [] fnameall;
		CLSLSTP clslstp = flist.get(flstname);
		StringBuffer fnbuf = new StringBuffer();
		for(CLSP clsp : clslstp.getFclsp()){
			if(!fnbuf.toString().contains(clsp.getFname())){
				fnbuf.append(clsp.getFname()).append("-");
			}
		}
		fnameall = fnbuf.toString().split("\\-");
		return fnameall;
	}

	/**
	 * 获取list中一共包含多少个域名（distinact)
	 * @author lixl (2014-11-19)
	 * @param flstname 
	 * @return int 
	 */
	public int getFlistContaintFnameNum(String flstname){
		return getAllListFname(flstname).length;
	}
	
	/**
	 * 根据List名称以及List中的域名称获取第i条域值
	 * @param flstname 列表名称
	 * @param fname 域名称
	 * @param i 对应第几条记录
	 * @return
	 */
	public String getFlistFval(String flstname, String fname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		int index = 0, size = 0;
		size = clslstp.getFclsp().size();
		for(int j=0; j<size; j++){
			if(clslstp.getFclsp().get(j).getFname().equals(fname)){
				index = j+i;
			}
		}
		return clslstp.getFclsp().get(index).getFval();
	}

	/**
	 * 获取指定list对象
	 * @param flstname list名称
	 * @return
	 */
	public CLSLSTP getFlistCSLLSTP(String flstname){
		return flist.get(flstname);
	}
	
	/**
	 * 获取指定List中第"i"条对应的 fname、fval、ftype
	 * @param flstname
	 * @param i
	 * @return 类属性元素
	 */
	public CLSP getFlistCSLP(String flstname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		return clslstp.getFclsp().get(i);
	}
	
	/**
	 * 获取指定List中第“i”条记录的域名称
	 * @author lixl (2014-11-19)
	 * @param flstname 
	 * @param i 
	 * @return fname域名
	 */
	public String getFlistFname(String flstname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		return clslstp.getFclsp().get(i).getFname();
	}
	
	/**
	 * 获取List中第“i”条记录
	 * @author lixl (2014-11-19)
	 * @param flstname list名称
	 * @param i 第几条
	 * @return String 
	 */
	public String getFlistFval(String flstname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		return clslstp.getFclsp().get(i).getFval();
	}

	/**
	 * 获取对应List中所有域名对应的域类型(fname对应的第一类型)
	 * @param flstname 列表名称
	 * @param fname 域名
	 * @return
	 */
	public String getFlistFtype(String flstname, String fname){
		CLSLSTP clslstp = flist.get(flstname);
		String type="";
		int size = 0;
		
		size = clslstp.getFclsp().size();
		for(int i=0; i<size; i++){
			CLSP clsp = clslstp.getFclsp().get(i);
			if(clsp.getFname().equals(fname)){
				type = clsp.getFtype(); 
			}
		}
		return type;
	}
}

