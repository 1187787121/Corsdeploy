/**
 * Title: CompCommonService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017��8��14��
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
	 * Description: У������������ID�Ƿ���ڣ��������׳��쳣
	 * @param comp_id
	 */
	public void checkCompIdIsExist(String comp_id, EnumValue<?> comp_type) {
		if (moComponentDaoService.getInfoByKeyForUpdate(comp_id) == null) {
			throw new ComponentNotExistException().addScene("COMPTYPE", comp_type.getCname()).addScene("COMPID",
					comp_id);
		}
	}

	/**
	 * Description: У������������ID�Ƿ񲻴��ڣ������׳��쳣
	 * @param comp_id
	 */
	public void checkCompIdNotExist(String comp_id, EnumValue<?> comp_type) {
		if (moComponentDaoService.getInfoByKeyForUpdate(comp_id) != null) {
			throw new ComponentIdAlreadyExistException().addScene("COMPTYPE", comp_type.getCname()).addScene("COMPID",
					comp_id);
		}
	}
}
