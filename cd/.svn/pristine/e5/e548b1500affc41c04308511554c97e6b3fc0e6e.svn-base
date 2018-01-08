/**
 * Title: ViSysEnvServerDao.java
 * File Description: 系统环境服务器视图查询类
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.view.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.view.info.ViSysEnvServerView;
import com.wk.cd.common.util.Assert;
import com.wk.db.DBIterator;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class description:系统环境服务器视图查询类
 * @author AutoGen
 */
@Query
public abstract class ViSysEnvServerQuery{

	/** 
	 * Description: 测试查询所有应用环境服务器视图
	 * @return 
	 */
	@SqlParam(sql="select * from vi_sys_env_server")
	public abstract DBIterator<ViSysEnvServerView> queryAllView();
	
	/**
	 * Description: 根据服务名查询关联环境及系统信息
	 * @param server_name
	 * @return
	 */
	@SqlParam(sql="SELECT SYS_NAME,SYS_CN_NAME,SYS_TYPE,ENV_NAME,ENV_CN_NAME,ENV_TYPE FROM VI_SYS_ENV_SERVER WHERE SERVER_NAME =:server_name group by SYS_NAME,SYS_CN_NAME,SYS_TYPE,ENV_NAME,ENV_CN_NAME,ENV_TYPE")
	public abstract List<ViSysEnvServerView> querySysByServerName(String server_name);
	
	/**
	 * 按系统列表和IP模糊查询服务器详细信息
	 * @param String server_ip
	 * @param List<String> sys_name_list
	 * @return List<ViSysEnvServerInfo>
	 */
	public List<ViSysEnvServerView> queryServerDetailByIPAndSystems(String server_ip, String[] sys_name_list) {
		String sys_name_str = null;
		if (!Assert.isEmpty(sys_name_list)) {
			StringBuilder sys_name_sb = new StringBuilder();
			for (String sys_name : sys_name_list) {
				sys_name_sb.append("'" + sys_name + "',");
			}
			sys_name_str = sys_name_sb.substring(0, sys_name_sb.length() - 1);
		}
		DBIterator<ViSysEnvServerView> iterator = queryServerDetailByIPAndSystems(server_ip, sys_name_str);
		List<ViSysEnvServerView> list = new ArrayList<ViSysEnvServerView>();
		try {
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return list;
	}
	
	/**
	 * 按系统列表和IP模糊查询服务器详细信息
	 * @param String server_ip
	 * @param String sys_name_str
	 * @return DBIterator<ViSysEnvServerInfo>
	 */
	@SqlParam(sql = "select distinct SYS_TYPE,SERVER_NAME,SERVER_CN_NAME,SERVER_DESC,SERVER_IP,SERVER_OS,OS_SBK_VER from VI_SYS_ENV_SERVER where SERVER_NAME is not null AND (SERVER_IP LIKE '%${server_ip}%') AND (SYS_NAME IN(${sys_name_str:2:1=1})) order by SERVER_IP", dynamic = true)
	public abstract DBIterator<ViSysEnvServerView> queryServerDetailByIPAndSystems(String server_ip, String sys_name_str);
}