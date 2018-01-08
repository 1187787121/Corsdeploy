/**
 * Title: UsRoleRsPrivDao.java
 * File Description: ���Ž�ɫ��ԴȨ�޲�ѯ����Ӻ�ɾ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.info.*;

/**
 * Class description:���Ž�ɫ��ԴȨ�޲�ѯ����Ӻ�ɾ��
 * @author AutoGen
 */
abstract class UsRoleRsPrivDao
		extends EntityDao<UsRoleRsPrivInfo> {
	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯ��Դ�б�
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(querySet = { "rs_code" }, condition = "DPRL_CODE=:dprl_code ", orderBy = "dprl_code asc")
	abstract List<String> queryRsByDprl(String dprl_code);

	/**
	 * Description: ��ѯ���Ž�ɫ������ӵ�е���ԴȨ��
	 * @param dprl_code_arr
	 * @return
	 */
	@SqlParam(sql = "SELECT b.* FROM us_role_rs_priv a,rs_res b WHERE a.RS_CODE=b.RS_CODE AND a.DPRL_CODE IN ${dprl_code_arr::1=0} AND RCD_STATE=1", dynamic = true)
	abstract DBIterator<RsResInfo> iteratorRsByDprlArr(String dprl_code_arr);

	/**
	 * Description: ����Ӧ�ļ�¼�Ƿ��Ѿ����ڣ�������ڷ���1�������ڷ���0
	 * @param dprl_code
	 * @param rs_code
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code and RS_CODE=:rs_code ")
	abstract int countRsByDprlCode(String dprl_code, String rs_code);

	// /**
	// * Description: ���Ĳ��Ž�ɫ����Դ����״̬������-->ɾ��
	// * @param dprl_code
	// * @param rs_code
	// */
	// @SqlParam(updateSet = { "RCD_STATE" }, condition =
	// "DPRL_CODE=:dprl_code and RS_CODE=:rs_code")
	// abstract void updateRsByDprlCode(int rcd_state, String dprl_code,
	// String rs_code);

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ��������ԴȨ��
	 * @param dprl_code
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code")
	abstract void deleteAllRsByDprl(String dprl_code);

	/** 
	 * Description: ���ݲ��Ž�ɫ�����ѯ��Դ��Ϣ
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select b.* from us_role_rs_priv a,rs_res b where dprl_code=:dprl_code and a.rs_code=b.rs_code and rcd_state=1")
	abstract DBIterator<RsResInfo> iteratorRsByDprl(String dprl_code);
	
	/**
	 * 
	 * Description: ���ݲ��Ž�ɫ���������ѯ��ԴȨ��
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql="select distinct b.* from us_role_rs_priv a,rs_res b where (dprl_code in ${dprl_code}) and a.rs_code=b.rs_code and rcd_state=1",dynamic = true)
	abstract DBIterator<RsResInfo> iteratorRsByDprlArray(String dprl_code);
	
	/** 
	 * Description: ���ݲ��Ž�ɫ��ѯ��ԴȨ����Ϣ
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(querySet={"RS_CODE"},condition="dprl_code=:dprl_code")
	abstract DBIterator<String> iteratorRsPrivByDprl(String dprl_code);
}