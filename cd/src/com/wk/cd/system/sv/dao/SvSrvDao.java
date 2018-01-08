/**
 * Title: SvSrvDao.java
 * File Description: �������ñ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.system.ap.bean.ApproveServiceBean;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:�������ñ�
 * @author AutoGen
 */
abstract class SvSrvDao
		extends EntityDao<SvSrvInfo> {

	// ����������ѯһ����¼
//	@SqlParam(condition = "SRV_NAME = :srv_name and RCD_STATE = 1")
	@SqlParam(sql="select * from SV_SRV where SRV_NAME = :srv_name and RCD_STATE = 1")
	abstract SvSrvInfo getInfoByName(String srv_name);

	// �޸ļ�¼״̬
	@SqlParam(updateSet = { "RCD_STATE" }, condition = "PK")
	abstract int updateRcdStateInfo(RCD_STATE rcd_state, String srv_name);

	// ���շ������Ʋ�ѯ��¼����
	@SqlParam(condition = "SRV_NAME = :srv_name and RCD_STATE = 1")
	abstract int countInfo(String srv_name);

	// ���·������ñ���Ϣ
	@SqlParam(updateSet = { "SRV_BK_DESC", "SRV_FUN_TYPE", "SRV_CLASS_NAME",
			"SRV_METHOD_NAME", "CHECK_FLAG", "AUTH_FLAG", "SOC_FLAG" }, 
			condition = "PK")
	abstract int updateInfo(SvSrvInfo info);

	// ��ҳ��ѯһ�������Ϣ
	@SqlParam(sql = "select srv_name, srv_bk_desc, srv_fun_type, srv_class_name, srv_method_name, check_flag, auth_flag, soc_flag, sallow_flag, " +
			"log_level, rcd_state from SV_SRV where (SRV_NAME in ${user_srv_str}) and (SRV_FUN_TYPE in ${srv_type_str}) and RCD_STATE = 1 " +
			"order by SRV_FUN_TYPE, SRV_NAME", dynamic = true)
	abstract List<SvSrvInfo> pageAllSvSrv(String user_srv_str,
			String srv_type_str, int start_recd, int limit_recd);

	// ��ҳ��ѯʱ���������ҳ������������
	@SqlParam(sql = "select count(*) from SV_SRV where (SRV_NAME in ${user_srv_str}) and (SRV_FUN_TYPE in ${srv_type_str}) and RCD_STATE = 1 ", dynamic = true)
	abstract int countAllSvSrv(String user_srv_str, String srv_type_str);

	// ���ݴ���ķ��������ַ�������ʽΪ��('srv1','srv2','srv3')������ѯ��ϸ��Ϣ
	@SqlParam(sql = "select * from SV_SRV where SRV_NAME in ${srv_name_str::1=0} and RCD_STATE = 1", dynamic = true)
	abstract DBIterator<SvSrvInfo> iteratorSrvBySrvNames(String srv_name_str);

	// ��������ѯ���еļ�¼״̬Ϊ�����ķ���
	@SqlParam(condition = "RCD_STATE = 1")
	abstract DBIterator<SvSrvInfo> iteratorAllSrv();

	/** 
	 * ��ѯ���б�����Ȩ��������
	 * @return 
	 */
	@SqlParam(sql="select b.SRV_NAME from (select DISTINCT SRV_NAME from sv_srv_auth where AUTH_TYPE=1)a ,sv_srv b where a.SRV_NAME=b.SRV_NAME and b.AUTH_FLAG=1")
	abstract DBIterator<String> queryLocalAuthSrv();
	
	/**
	 * Description: ���ݷ������ͷ�ҳ��ѯ����
	 * @param srv_fun_type1 ��������
	 * @param start_recd ��ʼ����
	 * @param limit_recd ��ѯ����
	 * @return
	 */
	@SqlParam(sql="select srv_name, srv_bk_desc, srv_fun_type, srv_class_name, srv_method_name, check_flag, auth_flag, soc_flag, sallow_flag,log_level, rcd_state from sv_srv where (srv_fun_type =${srv_fun_type}) and RCD_STATE = 1 order by SRV_FUN_TYPE, SRV_NAME",dynamic=true)
	abstract List<SvSrvInfo> pageSrvByFunType(FUN_TYPE srv_fun_type,int start_recd, int limit_recd);

	/**
	 * Description: �����û������������ͷ�ҳ��ѯ�û�ӵ�еķ���Ȩ��������
	 * @param srv_fun_type
	 * @return
	 */
	@SqlParam(sql="select count(*) from sv_srv where (srv_fun_type =${srv_fun_type}) and RCD_STATE = 1",dynamic=true)
	abstract int countSrvByFunType(FUN_TYPE srv_fun_type);

	/** 
	 * Description: ��ѯ��������������б�
	 * @param sup_srvg_code
	 * @return 
	 */
	@SqlParam(condition="SUP_SRVG_CODE=:sup_srvg_code and RCD_STATE=1")
	abstract DBIterator<SvSrvInfo> listSubSrvBySrvgCode(String sup_srvg_code);
	
	/**
	 * Description: ��ѯ���пɸ��˺Ϳ���Ȩ�ķ���
	 * @return
	 */
	@SqlParam(condition="(check_flag = 1 or auth_flag = 1) and rcd_state = 1")
	abstract DBIterator<ApproveServiceBean> queryCanApproveSrv();

	@SqlParam(updateSet = "SRV_CLASS_NAME", condition = "PK")
	abstract int updateClassNameByKey(String class_name, String srv_name);

	/** 
	 * Description: 
	 * @param key
	 * @return 
	 */
	@SqlParam(sql="SELECT * FROM SV_SRV WHERE (SRV_BK_DESC LIKE '%${key}%') AND (CHECK_FLAG = 1 OR AUTH_FLAG = 1) AND RCD_STATE = 1",dynamic=true)
	abstract DBIterator<ApproveServiceBean> queryCanApproveSrvByKey(String key);
}