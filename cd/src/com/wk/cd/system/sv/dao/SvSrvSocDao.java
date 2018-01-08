/**
 * Title: SvSrvSocDao.java
 * File Description: ��������Դ���ñ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.sv.info.*;

/**
 * Class description:��������Դ���ñ�
 * @author AutoGen
 */
abstract class SvSrvSocDao extends EntityDao<SvSrvSocInfo> {
	//���ݷ������ƻ�ȡһ���������Դ��Ϣ
	@SqlParam(condition = "SRV_NAME = :srv_name", orderBy = "soc_seq")
	abstract List<SvSrvSocInfo> getListInfoByName(String srv_name);
	//���ݷ�������ɾ��һ���������Դ��Ϣ
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int deleteInfo(String srv_name);
	//���շ������Ʋ�ѯ��¼����
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int countInfo(String srv_name);
}