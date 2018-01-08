/**
 * Title: DeptQuery.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2015-1-15
 */
package com.wk.cd.system.rs.dao;

import java.util.List;

import com.wk.cd.enu.TARGET_TYPE;
import com.wk.cd.system.rs.bean.ResExtendsBean;
import com.wk.cd.system.rs.bean.RsOptPrivBean;
import com.wk.cd.system.us.bean.RsUrlBean;
import com.wk.db.DBIterator;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: 
 * @author xuy
 */
@Query
public abstract class ResQuery {
	
	@SqlParam(sql ="select (select count(*) from rs_res rs2 where rs2.super_rs_code=rs.rs_code) LOWRS_BK_NUM,rs.* from rs_res rs where rs.rcd_state=1 order by rs_code")
	public abstract DBIterator<ResExtendsBean> queryAllResInfo();
	
	@SqlParam(sql ="select (select count(*) from rs_res rs2 where rs2.super_rs_code=rs.rs_code and rs2.rs_code=:rs_code1 and rs2.rcd_state=1) LOWRS_BK_NUM,rs.* from rs_res rs where rs.rs_code=:rs_code2 and rs.rcd_state=1")
	public abstract ResExtendsBean queryResInfoByCode(String rs_code1, String rs_code2);

	/** 
	 * 根据对象类型和对象编码获取对象权限列表
	 * @param rs_code
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select opt.opt_code,opt.opt_name,opt.opt_bk_expl,opt.dis_express,priv.priv_flag from rs_opt opt ,rs_opt_priv priv where opt.opt_code=priv.opt_code and opt.BL_RS_CODE=:rs_code and priv.TARGET_TYPE=:target_type and priv.TARGET_CODE=:target_code and opt.RCD_STATE=1")
	abstract List<RsOptPrivBean> getTargetOptPriv(String rs_code, TARGET_TYPE target_type,String target_code);

	/** 
	 * 获取需要操作检查的资源配置信息
	 * @return 
	 */
	@SqlParam(sql="select distinct rs_code,rs_url from rs_res rs ,rs_opt opt where rs.rs_code=opt.bl_rs_code")
	abstract List<RsUrlBean> getRsConfig();
}
