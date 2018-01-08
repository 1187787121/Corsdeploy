/**
 * Title: UsUserTermDao.java
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
import com.wk.cd.system.us.bean.UsUserTermBean;
import com.wk.cd.system.us.info.UsUserTermInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:用户接入终端配置表
 * @author AutoGen
 */
abstract class UsUserTermDao extends EntityDao<UsUserTermInfo> {

	/** 
	 * Description: 查询用户接入终端列表
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id")
	abstract List<UsUserTermInfo> queryUserTermList(String user_id);

	/** 
	 * Description: 删除用户接入终端
	 * @param user_id
	 * @param term_no
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and term_no=:term_no")
	abstract int deleteUserTerm(String user_id, String term_no);

	/** 
	 * Description: 分页查询用户待启用接入终端
	 * @param user_cn_name
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select ut.user_id, ut.term_no, ut.channel_code,ut.dept_id,ut.use_state,ut.user_cn_name,ut.dept_cn_name,ch.channel_cn_name "
			+ "from us_user_term ut,ch_channel ch where ut.channel_code=ch.channel_code and ut.use_state=1 "
			+ "and (ut.user_cn_name like '%${user_cn_name}%') order by ut.dept_id",dynamic=true)
	abstract List<UsUserTermBean> pageUnUseUserTerm(String user_cn_name,int start_recd, int limit_recd);

	/** 
	 * Description: 查询用户待启用接入终端总数
	 * @param user_cn_name
	 * @return 
	 */
	@SqlParam(sql="select count(*) from us_user_term ut where ut.use_state=1 and (ut.user_cn_name like '%${user_cn_name}%')",dynamic=true)
	abstract int countUnUseUserTerm(String user_cn_name);
	
	/** 
	 * Description: 查询用户待启用接入终端总数
	 * @return 
	 */
	@SqlParam(condition="use_state=1")
	abstract int countAllUnUseUserTerm();


	/** 
	 * Description:  修改用户接入终端启用状态到启用状态
	 * @param used
	 * @param user_id
	 * @param term_no 
	 */
	@SqlParam(updateSet={"USE_STATE"},condition="USER_ID=:user_id and TERM_NO=:term_no")
	abstract void updateUseState(USE_STATE used, String user_id, String term_no);

	/** 
	 * Description: 查询用户接入终端总数
	 * @param user_id
	 * @param term_no
	 * @return 
	 */
	@SqlParam(condition="USER_ID=:user_id and TERM_NO=:term_no")
	abstract int countUserTerm(String user_id, String term_no);

	/** 
	 * Description: 删除用户指定渠道申请的终端
	 * @param user_id
	 * @param channel_code 
	 */
	@SqlParam(condition="USER_ID=:user_id and CHANNEL_CODE=:channel_code and TERM_NO<>:term_no")
	abstract void deleteUserTermByCh(String user_id, String channel_code,String term_no);
}