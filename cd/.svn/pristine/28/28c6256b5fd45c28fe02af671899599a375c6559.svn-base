/**
 * Title: BuildConfigfileDao.java
 * File Description: 构建任务配置文件变更表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import com.wk.cd.build.ea.info.BuildConfigfileInfo;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:构建任务配置文件变更表
 * @author AutoGen
 */
abstract class BuildConfigfileDao extends EntityDao<BuildConfigfileInfo> {

	/**
	 * Description: 根据任务编号和操作类型查询待修改信息列表
	 * @param server_ip
	 * @param work_id
	 * @param fopt_type
	 * @return
	 */
	@SqlParam(sql = "SELECT FILE_BK_PATH, FILE_BK_FNAME, DIR_YN_FLAG FROM build_configfile WHERE SERVER_NAME=:server_name AND WORK_ID =:work_id AND FOPT_TYPE =:fopt_type AND CFG_TYPE=:cfg_type GROUP BY FILE_BK_PATH, FILE_BK_FNAME, DIR_YN_FLAG")
	abstract DBIterator<BuildConfigfileInfo> getInfoByWorkAndFopt(String server_name, String work_id, FOPT_TYPE fopt_type, CFG_TYPE cfg_type);

	/**
	 * Description: 根据任务编号和服务器IP删除一条记录
	 * @param work_id
	 * @param server_ip
	 * @param fopt_type
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id and SERVER_IP=:server_ip and FOPT_TYPE=:fopt_type and FILE_BK_FNAME=:file_bk_fname")
	abstract int deleteInfoByWorkAndIp(String work_id, String server_ip, FOPT_TYPE fopt_type, String file_bk_fname);

	/**
	 * Description: 根据任务编号查询待配置文件列表
	 * @param server_ip
	 * @param work_id
	 * @param cfg_type
	 * @return
	 */
	@SqlParam(condition = "SERVER_IP =:server_ip AND WORK_ID =:work_id AND CFG_TYPE =:cfg_type", orderBy = "MODIFY_BK_DATE DESC,MODIFY_BK_TIME DESC")
	abstract DBIterator<BuildConfigfileInfo> getInfoByWork(String server_ip, String work_id, CFG_TYPE cfg_type);
}