/**
 * Title: VersionTemplate.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��12��29��
 */
package com.wk.cd.build.entity;

/**
 * Class Description: 
 * @author Administrator
 */
public interface VersionTemplate {
	
	//���
	public String checkout(String remote_path,String local_path);
	
	//����
	public String download(String remote_path,String local_path);
	
	//�ύ
	public String commit();
	public String commit(String file);
	
	//��ӵ��汾
	public String add();
	public String add(String file);
	
	//�汾����
	public String update(String path);
}
