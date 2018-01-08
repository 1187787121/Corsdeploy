/**
 * Title: EnvConfigfileModDao.java
 * File Description: ���������ļ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import com.wk.cd.build.ea.info.EnvConfigfileModInfo;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:���������ļ������
 * @author AutoGen
 */
abstract class EnvConfigfileModDao extends EntityDao<EnvConfigfileModInfo> {

	/** 
	 * Description: �������κż��������Ͳ�ѯ�޸���Ϣ�б�(ȥ��)
	 * @param batch_no
	 * @param fopt_type
	 * @return 
	 */
	@SqlParam(sql = "SELECT FILE_BK_PATH, FILE_BK_FNAME, DIR_YN_FLAG FROM ENV_CONFIGFILE_MOD WHERE SERVER_NAME=:server_name AND BATCH_NO =:batch_no AND FOPT_TYPE =:fopt_type GROUP BY FILE_BK_PATH, FILE_BK_FNAME, DIR_YN_FLAG")
	abstract DBIterator<EnvConfigfileModInfo> getInfoByBatchAndFopt(String server_name, String batch_no, FOPT_TYPE fopt_type);

}