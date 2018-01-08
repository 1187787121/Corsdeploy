/**
 * Title: CompCommonService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年8月14日
 */
package com.wk.cd.module1.service;

import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.exc.ComponentIdAlreadyExistException;
import com.wk.cd.module1.exc.ComponentNotExistException;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author yangl
 */
public class CompCommonService {

	@Inject
	private MoComponentDaoService moComponentDaoService;

	/**
	 * Description: 校检组件或组件组ID是否存在，不存在抛出异常
	 * @param comp_id
	 */
	public void checkCompIdIsExist(String comp_id, EnumValue<?> comp_type) {
		if (moComponentDaoService.getInfoByKeyForUpdate(comp_id) == null) {
			throw new ComponentNotExistException().addScene("COMPTYPE", comp_type.getCname()).addScene("COMPID",
					comp_id);
		}
	}

	/**
	 * Description: 校检组件或组件组ID是否不存在，存在抛出异常
	 * @param comp_id
	 */
	public void checkCompIdNotExist(String comp_id, EnumValue<?> comp_type) {
		if (moComponentDaoService.getInfoByKeyForUpdate(comp_id) != null) {
			throw new ComponentIdAlreadyExistException().addScene("COMPTYPE", comp_type.getCname()).addScene("COMPID",
					comp_id);
		}
	}
}
