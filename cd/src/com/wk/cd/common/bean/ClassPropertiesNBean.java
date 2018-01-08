/**
 * Title: ClassPropertiesNBean.java
 * File Description: CLASS�������ݽṹ
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
 * Class Description:CLASS�������ݽṹ
 * @author lixl
 */
public class ClassPropertiesNBean {
	private String clsname;
	private Map<String, CLSP> fstru;
	private Map<String, CLSLSTP> flist;

	/**
	 * ���캯��
	 * @author lixl (2014-11-17)
	 * @param clsname 
	 */
	public ClassPropertiesNBean(String clsname){
		this.clsname = clsname;
		this.fstru = new HashMap<String, CLSP>();
		this.flist = new HashMap<String, CLSLSTP>();
	}

	/**
	 * ���캯��
	 * @author lixl (2014-11-17)
	 */
	public ClassPropertiesNBean(){
		this.clsname = "";
		this.fstru = new HashMap<String, CLSP>();
		this.flist = new HashMap<String, CLSLSTP>();
	}

	/**
	 * ����һ����
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
	 * ����һ����
	 * @author lixl (2015-2-10)
	 * @param clsp 
	 * @param fname 
	 */
	public void addFstru(CLSP clsp, String fname){
		fstru.put(fname, clsp);
	}

	/**
	 * ����һ�����б�
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
	 * ����������
	 * @author lixl
	 * @param clsname
	 */
	public void setClsname(String clsname){
		this.clsname = clsname;
	}
	
	/**
	 * ����������
	 * @author lixl
	 * @return String
	 */
	public String getClsname(){
		return this.clsname;
	}

	/**
	 * ����List��Ӧ������
	 * @author lixl (2014-11-18)
	 * @param flstname 
	 * @param lstclsname 
	 */
	public void setLstclsname(String flstname, String lstclsname){
		this.flist.get(flstname).setFlstclsname(lstclsname);
	}

	/**
	 * ��ȡList����
	 * @author lixl (2014-11-18)
	 * @param flstname 
	 * @return String 
	 */
	public String getLstclsname(String flstname){
		return this.flist.get(flstname).getFlstclsname();
	}

	/**
	 * ��ȡ�ṹ�а������ٸ��� 
	 * ���磺 UserBean�а�����user_id user_name���ء�2��
	 * @author lixl (2014-11-17)
	 * @return int 
	 */
	public int getFstruSize(){
		return fstru.size();
	}

	/**
	 * ��ȡ�ṹ�а�������List������
	 * @author lixl (2014-11-17)
	 * @return int 
	 */
	public int getFlistSize(){
		return flist.size();
	}

	/**
	 * ��ȡָ��List�а������ٸ����¼
	 * ���磺UserBean�е�lst1����user_id user_name�������ء�2��
	 * @author lixl (2014-11-17)
	 * @param flstname 
	 * @return int 
	 */
	public int getFlistRcdSize(String flstname){
		return flist.get(flstname).getFclsp().size();
	}
	
	/**
	 * ��ȡList�ж�Ӧ�����¼�¼��
	 * @param flstname �б�����
	 * @param fname ������
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
	 * ��ȡָ��List�а������ٸ�Bean��¼ 
	 * ����List<UserBean> lst��lst��size 
	 * ���ձ�������ݽṹ,��flsp��size=fval����*fname���� 
	 * ���bean��size=�ܼ�¼/��������������
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
	 * ���ؽṹ����������
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
	 * ��ȡ��������
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
	 * ��ȡ������Ӧ�������ƣ������͵���Key)
	 * @param fname
	 * @return
	 */
	public String getStruFname(String fname){
		return fstru.get(fname).getFname();
	}

	/**
	 * ����������Ӧ����ֵ
	 * @author lixl (2014-11-17)
	 * @param fname 
	 * @return String 
	 */
	public String getStruFval(String fname){
		return fstru.get(fname).getFval();
	}

	/**
	 * ����������Ӧ��������
	 * @author lixl (2014-11-17)
	 * @param fname 
	 * @return String 
	 */
	public String getStruFtype(String fname){
		return fstru.get(fname).getFtype();
	}

	/**
	 * ��ȡ������Ӧ��ֵ������
	 * @author lixl (2015-2-9)
	 * @param fname 
	 * @return CLSP 
	 */
	public CLSP getStru(String fname){
		return fstru.get(fname);
	}

	/**
	 * ��ȡ����List���� 
	 * ���磺lst lst2 lst3 
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
	 * ��ȡBean��ָ��List���¶�Ӧ�������������� 
	 * ���磺lst�°���{user_id, user_name} 
	 * @param flstname ��������
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
	 * ��ȡlist��һ���������ٸ�������distinact)
	 * @author lixl (2014-11-19)
	 * @param flstname 
	 * @return int 
	 */
	public int getFlistContaintFnameNum(String flstname){
		return getAllListFname(flstname).length;
	}
	
	/**
	 * ����List�����Լ�List�е������ƻ�ȡ��i����ֵ
	 * @param flstname �б�����
	 * @param fname ������
	 * @param i ��Ӧ�ڼ�����¼
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
	 * ��ȡָ��list����
	 * @param flstname list����
	 * @return
	 */
	public CLSLSTP getFlistCSLLSTP(String flstname){
		return flist.get(flstname);
	}
	
	/**
	 * ��ȡָ��List�е�"i"����Ӧ�� fname��fval��ftype
	 * @param flstname
	 * @param i
	 * @return ������Ԫ��
	 */
	public CLSP getFlistCSLP(String flstname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		return clslstp.getFclsp().get(i);
	}
	
	/**
	 * ��ȡָ��List�еڡ�i������¼��������
	 * @author lixl (2014-11-19)
	 * @param flstname 
	 * @param i 
	 * @return fname����
	 */
	public String getFlistFname(String flstname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		return clslstp.getFclsp().get(i).getFname();
	}
	
	/**
	 * ��ȡList�еڡ�i������¼
	 * @author lixl (2014-11-19)
	 * @param flstname list����
	 * @param i �ڼ���
	 * @return String 
	 */
	public String getFlistFval(String flstname, int i){
		CLSLSTP clslstp = flist.get(flstname);
		return clslstp.getFclsp().get(i).getFval();
	}

	/**
	 * ��ȡ��ӦList������������Ӧ��������(fname��Ӧ�ĵ�һ����)
	 * @param flstname �б�����
	 * @param fname ����
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

