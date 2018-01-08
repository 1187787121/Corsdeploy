/**
 * Title: UsUserContactDaoService.java
 * File Description: 用户常用联系人关联表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-11-18
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.info.*;
import com.wk.lang.Inject;

/**
 * Class description:用户常用联系人关联表
 * @author AutoGen
 */
public class UsUserContactDaoService {
	@Inject private UsUserContactDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserContactInfo info
	 * @return UsUserContactInfo
	 */
	public UsUserContactInfo getInfoByKey(UsUserContactInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserContactInfo info
	 * @return UsUserContactInfo
	 */
	public UsUserContactInfo getInfoByKeyForUpdate(UsUserContactInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsUserContactInfo info
	 * @return int
	 */
	public int insertInfo(UsUserContactInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserContactInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserContactInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 校验用户常用联系人不存在
	 * @param user_id
	 * @param contact_user_id 
	 */
	public void checkUserContactNotExist(String user_id, String contact_user_id) {
		int count = dao.countUserContact(user_id,contact_user_id);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", UsUserContactInfo.TABLECN).addScene("FIELD", contact_user_id);
		}
	}

	/** 
	 * Description: 校验用户常用联系人存在
	 * @param user_id
	 * @param contact_user_id 
	 */
	public void checkUserContactExist(String user_id, String contact_user_id) {
		int count = dao.countUserContact(user_id,contact_user_id);
		if(count==0){
			throw new RecordNotFoundException().addScene("TABLE", UsUserContactInfo.TABLECN).addScene("FIELD", contact_user_id);
		}
	}

	/** 
	 * Description: 删除用户常用联系人
	 * @param contactInfo 
	 */
	public void deleteContact(UsUserContactInfo contactInfo) {
		dao.delete(contactInfo);
	}
}