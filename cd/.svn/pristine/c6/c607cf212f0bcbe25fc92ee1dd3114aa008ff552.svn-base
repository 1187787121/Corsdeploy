/**
 * Title: PhaseParamTools.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年8月18日
 */
package com.wk.cd.module1.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.CmdParamIsNotExistException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 
 * @author Administrator
 */
public class PhaseParamTools {
	private static final Log logger = LogFactory.getLog();
	
	/**
	 * Description: 获取单条命令中有用的参数并且组合处理
	 * @param param_in_cmd
	 * @param params
	 * @param cmd
	 * @return
	 */
	public static List<List<PhaseParam>> getUsefulParam(
			List<String> param_in_cmd, List<PhaseParam> params) {
		List<PhaseParam> list = PhaseParam.copy(params);
		List<PhaseParam> index_param = mergeParam(list);    //参数里面如果是多值的情况就这里把参数拆成多个 并且用index 从1开始的顺序递增
		List<List<PhaseParam>> list_father = new ArrayList<List<PhaseParam>>();
		// 遍历命令中所要使用到的参数
		for (String param : param_in_cmd) {
			List<PhaseParam> params_return = new ArrayList<PhaseParam>();
			for (PhaseParam bean : index_param) {
				String param_name = bean.getParam_name();
					if (param.equals(param_name)) {
						//多参数时，会添加多个param_name相同的PhaseParam
						params_return.add(bean);
					}

			}
			if (Assert.isEmpty(params_return)) {
				throw new CmdParamIsNotExistException()
						.addScene("PARAM", param);
			}
			list_father.add(params_return);
		}
		List<List<PhaseParam>> list_return = new ArrayList<List<PhaseParam>>();
		//配置文件参数的处理
		if(param_in_cmd.contains(PhaseParam.CONFIG_LOCAL) || param_in_cmd.contains(PhaseParam.CONFIG_REMOTE)){
			logger.debug("config phase cmd param needs filt");
			list_return = paramFilter(descartes(list_father));
		}else{
			list_return = descartes(list_father);
		}
		logger.debug("param_in_cmd [{}],used_param_list[{}]",param_in_cmd,list_return);
		return list_return;
		
	}
	/**
	 * Description: 参数里面如果是多值的情况就这里把参数拆成多个 并且用index 从1开始的顺序递增
	 * @param list
	 * @return
	 */
	private static List<PhaseParam> mergeParam(List<PhaseParam> list){
		List<PhaseParam> deal_list = new ArrayList<PhaseParam>();
		for (PhaseParam info : list) {
			logger.debug("当前处理的参数的名[{}],参数值[{}]", info.getParam_name(), info.getParam_value());
			String value = info.getParam_value();
			String[] values = null;
			if(Assert.isEmpty(value)){
				deal_list.add(info);
				continue;
			}else if (value.contains(PhaseParam.PARAM_SLIP)) {
				//一个参数对应多个值(使用的是“;”连接)
				values = value.trim().split(PhaseParam.PARAM_SLIP);
			} else {
				values = new String[]{value};
			}
			int index = 1;
			for(String sv : values){
				PhaseParam pi = PhaseParam.copy(info);
				pi.setParam_value(sv);
				if(pi.getIndex() == null || pi.getIndex() == 0){
					pi.setIndex(index++);
				}
				deal_list.add(pi);
			}
		}
		return deal_list;
	}
	/**
	 * Description: 将参数对 进行笛卡尔乘积
	 * @param crossArgs
	 * @return
	 */
	private static List<List<PhaseParam>> descartes(
			List<List<PhaseParam>> params) {

		// 计算出笛卡尔积行数
		int rows = params.size() > 0 ? 1 : 0;

		for (List<PhaseParam> data : params) {
			rows *= data.size();
		}
		// 笛卡尔积索引记录
		int[] record = new int[params.size()];
		List<List<PhaseParam>> results = new ArrayList<List<PhaseParam>>();
		// 产生笛卡尔积
		for (int i = 0; i < rows; i++) {
			List<PhaseParam> row = new ArrayList<PhaseParam>();
			// 生成笛卡尔积的每组数据
			for (int index = 0; index < record.length; index++) {
				row.add(params.get(index).get(record[index]));
			}
			results.add(row);
			descartesRecord(params, record, params.size() - 1);
		}
		return results;
	}

	private static void descartesRecord(List<List<PhaseParam>> sourceArgs,
			int[] record, int level) {
		record[level] = record[level] + 1;
		if (record[level] >= sourceArgs.get(level).size() && level > 0) {
			record[level] = 0;
			descartesRecord(sourceArgs, record, level - 1);
		}
	}

	private static List<List<PhaseParam>> paramFilter(
			List<List<PhaseParam>> params) {
		List<List<PhaseParam>> results = new ArrayList<List<PhaseParam>>();
		for (List<PhaseParam> list : params) {
			if (check(list)) {
				results.add(list);
			}
		}
		return results;
	}
	
	/**
	 * Description: 校验参数中需要是否一致
	 * @param param
	 * @return
	 */
	private static boolean check(List<PhaseParam> param) {
		int index = param.get(0).getIndex();
		for (PhaseParam info : param) {
			if (info.getIndex() != index) {
				return false;
			}
		}
		return true;
	}
	

	

}
