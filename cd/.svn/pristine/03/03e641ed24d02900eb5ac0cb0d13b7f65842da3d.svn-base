/**
 * Title: UsUserTermDaoService.java
 * File Description: 用户接入终端配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-9-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.enu.USE_STATE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.system.us.bean.UsUserTermBean;
import com.wk.cd.system.us.info.UsUserTermInfo;
import com.wk.lang.Inject;

/**
 * Class description:用户接入终端配置表
 * @author AutoGen
 */
public class UsUserTermDaoService {
	@Inject private UsUserTermDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserTermInfo info
	 * @return UsUserTermInfo
	 */
	public UsUserTermInfo getInfoByKey(UsUserTermInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserTermInfo info
	 * @return UsUserTermInfo
	 */
	public UsUserTermInfo getInfoByKeyForUpdate(UsUserTermInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsUserTermInfo info
	 * @return int
	 */
	public int insertInfo(UsUserTermInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserTermInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserTermInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 查询用户接入终端列表
	 * @param user_id
	 * @return 
	 */
	public List<UsUserTermInfo> queryUserTermList(String user_id) {
		return dao.queryUserTermList(user_id);
	}

	/** 
	 * Description: 删除用户接入终端
	 * @param user_id
	 * @param term_no 
	 */
	public int deleteUserTerm(String user_id, String term_no) {
		return dao.deleteUserTerm(user_id,term_no);
	}

	/** 
	 * Description: 分页查询用户待启用接入终端
	 * @param user_cn_name
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<UsUserTermBean> pageUnUseUserTerm(String user_cn_name,
			int start_recd, int limit_recd) {
		return dao.pageUnUseUserTerm(user_cn_name,start_recd,limit_recd);
	}

	/** 
	 * Description: 查询用户待启用接入终端总数
	 * @param user_cn_name
	 * @return 
	 */
	public int countUnUseUserTerm(String user_cn_name) {
		return dao.countUnUseUserTerm(user_cn_name);
	}
	
	/** 
	 * Description: 查询用户待启用接入终端总数
	 * @return 
	 */
	public int countAllUnUseUserTerm() {
		return dao.countAllUnUseUserTerm();
	}

	/** 
	 * Description: 修改用户接入终端启用状态到启用状态
	 * @param user_id
	 * @param term_no 
	 */
	public void updateUseState(String user_id, String term_no) {
		dao.updateUseState(USE_STATE.USED,user_id,term_no);
	}

	/** 
	 * Description: 检查用户接入终端不存在
	 * @param user_id
	 * @param term_no 
	 */
	public void checkUserTermNoExist(String user_id, String term_no) {
		int count=dao.countUserTerm(user_id,term_no);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", UsUserTermInfo.TABLECN).addScene("FIELD", "用户名["+user_id+"]终端号["+term_no+"]");
		}
	}

	/** 
	 * Description: 删除用户指定渠道申请的终端
	 * @param user_id
	 * @param channel_code 
	 */
	public void deleteUserTermByCh(String user_id, String channel_code,String term_no) {
		dao.deleteUserTermByCh(user_id,channel_code,term_no);
	}

}