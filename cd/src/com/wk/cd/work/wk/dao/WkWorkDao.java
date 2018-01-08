/**
 * Title: WkWorkDao.java
 * File Description: �������
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:�������
 * @author AutoGen
 */
abstract class WkWorkDao
		extends EntityDao<WkWorkInfo> {
	/**
	 * ������������ѯ��ϸ��Ϣ
	 * @param work_code �������
	 * @return ��ϸ��Ϣ
	 */
	@SqlParam(condition = "WORK_CODE = :work_code and RCD_STATE = 1")
	abstract WkWorkInfo getInfoByWorkCode(String work_code);

	/**
	 * Description: ����work_code��ȡ��������
	 * @param work_code �������
	 * @return
	 */
	@SqlParam(condition = "rcd_state=1 and work_code=:work_code")
	abstract int countByWorkcode(String work_code);

	/**
	 * Description: ����keyword�Ĵ�ֵ��ȡ���ϴ����������ҳ��Ϣ
	 * @param fun_type ��������
	 * @param keyword �ؼ���
	 * @param start_recd ��ʼ��¼��
	 * @param limit_recd ��ѯ��¼��
	 * @return
	 */
	@SqlParam(sql = "select work_code, work_cn_name, work_bk_desc, is_publish, work_fun_type, crt_bk_date, crt_bk_time, crt_user_id, " +
			"modify_bk_date, modify_bk_time, modify_user_id, del_bk_date, del_bk_time, del_user_id, rcd_state from WK_WORK where " +
			"rcd_state=1 and work_fun_type=${fun_type} and (work_cn_name like '%${keyword}%')", dynamic = true)
	abstract List<WkWorkInfo> pagePbmWork(FUN_TYPE fun_type, String keyword,
			int start_recd, int limit_recd);

	/**
	 * ��ѯ����������Ϣ��������Ӧ�б�
	 * @param start_rcd ��ʼ����
	 * @param limit_rcd ��ѯ����
	 * @return ��ѯ��Ϣ����
	 */
	@SqlParam(sql = "select work_code, work_cn_name, work_bk_desc, is_publish, work_fun_type, crt_bk_date, crt_bk_time, crt_user_id, " +
			"modify_bk_date, modify_bk_time, modify_user_id, del_bk_date, del_bk_time, del_user_id, rcd_state from WK_WORK where " +
			"(WORK_FUN_TYPE in ${work_type_str}) and (IS_PUBLISH in ${is_publish_str}) and RCD_STATE = 1 order by WORK_FUN_TYPE, WORK_CODE", dynamic = true)
	abstract List<WkWorkInfo> pageAllWork(String work_type_str,
			String is_publish_str, int start_recd, int limit_recd);

	/**
	 * ��ѯ������������
	 * @return ��ѯ��Ϣ����
	 */
	@SqlParam(sql = "select count(*) from WK_WORK where (WORK_FUN_TYPE in ${work_type_str}) and (IS_PUBLISH in ${is_publish_str}) and RCD_STATE = 1", dynamic = true)
	abstract int countAllWork(String work_type_str, String is_publish_str);

	/**
	 * ���·������
	 * @param work_code �������
	 * @param work_cn_name ��������
	 * @param work_bk_desc ��������
	 * @param is_publish �Ƿ񷢲�
	 * @param work_fun_type ��������
	 * @return ��������
	 */
	@SqlParam(updateSet = { "WORK_CN_NAME", "WORK_BK_DESC", "IS_PUBLISH",
			"WORK_FUN_TYPE", "MODIFY_BK_DATE", "MODIFY_BK_TIME",
			"MODIFY_USER_ID" }, condition = "PK")
	abstract int updateWorkByWorkCode(String work_cn_name, String work_bk_desc,
			IS_PUBLISH is_publish, FUN_TYPE work_fun_type,
			JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String work_code);

	/**
	 * �����������ɾ����Ӧ������Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	@SqlParam(updateSet = { "DEL_BK_DATE", "DEL_BK_TIME", "DEL_USER_ID",
			"RCD_STATE" }, condition = "PK")
	abstract int updateWorkByWorkCodeDel(JaDate del_bk_date,
			JaTime del_bk_time, String del_user_id, RCD_STATE rcd_state,
			String work_code);
}