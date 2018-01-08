/**
 * Title: MoComponentDaoService.java
 * File Description: 组件信息表
 *
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-18
 */
package com.wk.cd.module1.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.enu.COMPONENT_PURPOSE;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.StringUtil;

/**
 * Class description:组件信息表
 * @author AutoGen
 */
public class MoComponentDaoService {
	@Inject
	private MoComponentDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param info
	 * @return MoComponentInfo
	 */
	public MoComponentInfo getInfoByKey(MoComponentInfo info) {
		return dao.get(info);
	}

	public MoComponentInfo getInfoByKey(String id) {
		return dao.get(id);
	}

	/**
	 * Description: 根据组件名获取组件ID
	 * @param name
	 * @return
	 */
	public List<String> getIdByName(String name) {
		return dao.getIdByName(name);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param info
	 * @return MoComponentInfo
	 */
	public MoComponentInfo getInfoByKeyForUpdate(MoComponentInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param id
	 * @return MoComponentInfo
	 */
	public MoComponentInfo getInfoByKeyForUpdate(String id) {
		return dao.getForUpdate(id);
	}

	/**
	 * 插入一条记录
	 * @param info
	 * @return int
	 */
	public int insertInfo(MoComponentInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param infos
	 * @return int
	 */
	public int insertListInfo(List<MoComponentInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 修改组件信息
	 * @param info
	 */
	public int updateModuleInfo(MoComponentInfo info) {
		return dao.update(info);
	}

	/**
	 * Description: 删除组件或组件组
	 * @param id
	 */
	public int deleteInfoByKey(String id) {
		return dao.delete(id);
	}
	
	/**
	 * Description: 查询所有创建用户
	 * @return
	 */
	public List<String> queryAllCrtUser() {
		return dao.queryAllCrtUser();
	}

	/**
	 * Description: 获得所有组件信息
	 * @return
	 */
	public List<MoComponentInfo> queryAllComponentInfos(String key_word, MODULE_TYPE module_type, String order_col_name,
			ORDER_TYPE order_type) {
		List<MoComponentInfo> list = new ArrayList<MoComponentInfo>();
		DBIterator<MoComponentInfo> iterator = null;
		if (!Assert.isEmpty(order_col_name)) {
			order_col_name = order_col_name.replace(" ", "");
		}
		if ("default".equals(order_col_name) || StringUtil.isEmpty(order_col_name)) {
			iterator = dao.queryAllComponentInfos(key_word, module_type,
					"CRT_BK_DATE " + ORDER_TYPE.DESC.getName() + ",CRT_BK_TIME", ORDER_TYPE.DESC.getName());
		} else if ("CRT_BK_DATE,CRT_BK_TIME".equalsIgnoreCase(order_col_name)) {
			iterator = dao.queryAllComponentInfos(key_word, module_type,
					"CRT_BK_DATE " + order_type.getName() + ",CRT_BK_TIME", order_type.getName());
		} else {
			iterator = dao.queryAllComponentInfos(key_word, module_type, order_col_name, order_type.getName());
		}
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
	 * Description: 更新发布状态
	 * @param id
	 * @param publish_state
	 */
	public int updateCompPubStateById(String id, PUBLISH_STATE publish_state) {
		return dao.updateCompPubStateById(publish_state, id);
	}

	/**
	 * Description:
	 * @param module_type
	 * @param component_purpose
	 * @return
	 */
	public List<MoComponentInfo> queryPublishedCompByType(String cn_name, MODULE_TYPE module_type) {
		List<MoComponentInfo> list = new ArrayList<MoComponentInfo>();
		DBIterator<MoComponentInfo> iterator = dao.queryPublishedCompByType(cn_name, module_type);
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
	 * Description:
	 * @param module_type
	 * @param component_purpose
	 * @return
	 */
	public List<MoComponentInfo> queryPublishedCotCompByType(MODULE_TYPE module_type,
			COMPONENT_PURPOSE component_purposes) {
		List<MoComponentInfo> list = new ArrayList<MoComponentInfo>();
		DBIterator<MoComponentInfo> iterator = dao.queryPublishedCotCompByType(module_type, component_purposes);
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
	 * Description: 不关联标签的高级搜索
	 * @return
	 */
	public List<MoComponentInfo> pageComponent(int component_purpose, int[] impl_types, String crt_user_id,
			int publish_state, JaDate start_modify_date, JaDate end_modify_date, String component_cn_name,
			ORDER_TYPE order_type, int start_recd, int limit_recd) {
		COMPONENT_PURPOSE component_purpose1 = null;
		String impl_type1 = null;
		PUBLISH_STATE publish_state1 = null;
		if (COMPONENT_PURPOSE.isExistsValue(COMPONENT_PURPOSE.class, component_purpose)) {
			component_purpose1 = COMPONENT_PURPOSE.valueOf(COMPONENT_PURPOSE.class, component_purpose);
		}
		if (PUBLISH_STATE.isExistsValue(PUBLISH_STATE.class, publish_state)) {
			publish_state1 = IMPL_TYPE.valueOf(PUBLISH_STATE.class, publish_state);
		}
		if (Assert.notEmpty(impl_types)) {
			StringBuilder sb = new StringBuilder();
			for (int impl_type : impl_types) {
				if (IMPL_TYPE.isExistsValue(IMPL_TYPE.class, impl_type)) {
					sb.append('\'').append(impl_type).append("\',");
				}
			}
			if (Assert.notEmpty(sb)) {
				sb.deleteCharAt(sb.length() - 1);
				impl_type1 = sb.toString();
			}
		}
		if (Assert.isEmpty(order_type)) {
			order_type = ORDER_TYPE.DESC;
		}
		return dao.pageComponent(component_purpose1, impl_type1, crt_user_id, publish_state1, start_modify_date,
				end_modify_date, component_cn_name, order_type.getName(), order_type.getName(), start_recd, limit_recd);
	}

	/**
	 * Description: 不关联标签的高级搜索的记录数统计
	 * @return
	 */
	public int countComponent(int component_purpose, int[] impl_types, String crt_user_id, int publish_state,
			JaDate start_modify_date, JaDate end_modify_date, String component_cn_name) {
		COMPONENT_PURPOSE component_purpose1 = null;
		String impl_type1 = null;
		PUBLISH_STATE publish_state1 = null;
		if (COMPONENT_PURPOSE.isExistsValue(COMPONENT_PURPOSE.class, component_purpose)) {
			component_purpose1 = COMPONENT_PURPOSE.valueOf(COMPONENT_PURPOSE.class, component_purpose);
		}
		if (PUBLISH_STATE.isExistsValue(PUBLISH_STATE.class, publish_state)) {
			publish_state1 = IMPL_TYPE.valueOf(PUBLISH_STATE.class, publish_state);
		}
		if (Assert.notEmpty(impl_types)) {
			StringBuilder sb = new StringBuilder();
			for (int impl_type : impl_types) {
				if (IMPL_TYPE.isExistsValue(IMPL_TYPE.class, impl_type)) {
					sb.append('\'').append(impl_type).append("\',");
				}
			}
			if (Assert.notEmpty(sb)) {
				sb.deleteCharAt(sb.length() - 1);
				impl_type1 = sb.toString();
			}
		}
		return dao.countComponent(component_purpose1, impl_type1, crt_user_id, publish_state1, start_modify_date,
				end_modify_date, component_cn_name);
	}

	/**
	 * Description: 关联标签的高级搜索
	 * @return
	 */
	public List<MoComponentInfo> pageComponent(int component_purpose, int[] impl_types, String crt_user_id,
			int publish_state, JaDate start_modify_date, JaDate end_modify_date, String component_cn_name,
			List<String> comp_ids, ORDER_TYPE order_type, int start_recd, int limit_recd) {
		COMPONENT_PURPOSE component_purpose1 = null;
		String impl_type1 = null;
		PUBLISH_STATE publish_state1 = null;
		if (COMPONENT_PURPOSE.isExistsValue(COMPONENT_PURPOSE.class, component_purpose)) {
			component_purpose1 = COMPONENT_PURPOSE.valueOf(COMPONENT_PURPOSE.class, component_purpose);
		}
		if (PUBLISH_STATE.isExistsValue(PUBLISH_STATE.class, publish_state)) {
			publish_state1 = IMPL_TYPE.valueOf(PUBLISH_STATE.class, publish_state);
		}
		if (Assert.isEmpty(order_type)) {
			order_type = ORDER_TYPE.DESC;
		}
		if (Assert.notEmpty(impl_types)) {
			StringBuilder sb = new StringBuilder();
			for (int impl_type : impl_types) {
				if (IMPL_TYPE.isExistsValue(IMPL_TYPE.class, impl_type)) {
					sb.append('\'').append(impl_type).append("\',");
				}
			}
			if (Assert.notEmpty(sb)) {
				sb.deleteCharAt(sb.length() - 1);
				impl_type1 = sb.toString();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String id : comp_ids) {
			if (Assert.notEmpty(id)) {
				sb.append('\'').append(id).append("\',");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return dao.pageComponent(component_purpose1, impl_type1, crt_user_id, publish_state1, start_modify_date,
				end_modify_date, component_cn_name, sb.toString(), order_type.getName(), order_type.getName(),
				start_recd, limit_recd);
	}

	/**
	 * Description: 关联标签的高级搜索的记录数统计
	 * @return
	 */
	public int countComponent(int component_purpose, int[] impl_types, String crt_user_id, int publish_state,
			JaDate start_modify_date, JaDate end_modify_date, String component_cn_name, List<String> comp_ids) {
		COMPONENT_PURPOSE component_purpose1 = null;
		String impl_type1 = null;
		PUBLISH_STATE publish_state1 = null;
		if (COMPONENT_PURPOSE.isExistsValue(COMPONENT_PURPOSE.class, component_purpose)) {
			component_purpose1 = COMPONENT_PURPOSE.valueOf(COMPONENT_PURPOSE.class, component_purpose);
		}
		if (PUBLISH_STATE.isExistsValue(PUBLISH_STATE.class, publish_state)) {
			publish_state1 = IMPL_TYPE.valueOf(PUBLISH_STATE.class, publish_state);
		}
		if (Assert.notEmpty(impl_types)) {
			StringBuilder sb = new StringBuilder();
			for (int impl_type : impl_types) {
				if (IMPL_TYPE.isExistsValue(IMPL_TYPE.class, impl_type)) {
					sb.append('\'').append(impl_type).append("\',");
				}
			}
			if (Assert.notEmpty(sb)) {
				sb.deleteCharAt(sb.length() - 1);
				impl_type1 = sb.toString();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String id : comp_ids) {
			if (Assert.notEmpty(id)) {
				sb.append('\'').append(id).append("\',");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return dao.countComponent(component_purpose1, impl_type1, crt_user_id, publish_state1, start_modify_date,
				end_modify_date, component_cn_name, sb.toString());
	}

	public List<MoComponentInfo> pageComponentList(String cn_name, String order_col_name, ORDER_TYPE order_type, int start_recd, int limit_recd) {
		String order_type_str = order_type.getName();
		if("default".equals(order_col_name) || StringUtil.isEmpty(order_col_name)) {
			return dao.pageDefaultComponentList(cn_name,order_type_str,order_type_str,start_recd,limit_recd);
		} else {
			return dao.pageComponentList(cn_name,order_col_name,order_type_str,order_type_str,start_recd,limit_recd);
		}
	}

	public int countComponentByCnName(String component_cn_name) {
		return dao.countComponentByCnName(component_cn_name);
	}
	
	public int countComponentById(String comp_id) {
		return dao.countComponentById(comp_id);
	}
}