/**
 * Title: PhaseParamTools.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��8��18��
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
	 * Description: ��ȡ�������������õĲ���������ϴ���
	 * @param param_in_cmd
	 * @param params
	 * @param cmd
	 * @return
	 */
	public static List<List<PhaseParam>> getUsefulParam(
			List<String> param_in_cmd, List<PhaseParam> params) {
		List<PhaseParam> list = PhaseParam.copy(params);
		List<PhaseParam> index_param = mergeParam(list);    //������������Ƕ�ֵ�����������Ѳ�����ɶ�� ������index ��1��ʼ��˳�����
		List<List<PhaseParam>> list_father = new ArrayList<List<PhaseParam>>();
		// ������������Ҫʹ�õ��Ĳ���
		for (String param : param_in_cmd) {
			List<PhaseParam> params_return = new ArrayList<PhaseParam>();
			for (PhaseParam bean : index_param) {
				String param_name = bean.getParam_name();
					if (param.equals(param_name)) {
						//�����ʱ������Ӷ��param_name��ͬ��PhaseParam
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
		//�����ļ������Ĵ���
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
	 * Description: ������������Ƕ�ֵ�����������Ѳ�����ɶ�� ������index ��1��ʼ��˳�����
	 * @param list
	 * @return
	 */
	private static List<PhaseParam> mergeParam(List<PhaseParam> list){
		List<PhaseParam> deal_list = new ArrayList<PhaseParam>();
		for (PhaseParam info : list) {
			logger.debug("��ǰ����Ĳ�������[{}],����ֵ[{}]", info.getParam_name(), info.getParam_value());
			String value = info.getParam_value();
			String[] values = null;
			if(Assert.isEmpty(value)){
				deal_list.add(info);
				continue;
			}else if (value.contains(PhaseParam.PARAM_SLIP)) {
				//һ��������Ӧ���ֵ(ʹ�õ��ǡ�;������)
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
	 * Description: �������� ���еѿ����˻�
	 * @param crossArgs
	 * @return
	 */
	private static List<List<PhaseParam>> descartes(
			List<List<PhaseParam>> params) {

		// ������ѿ���������
		int rows = params.size() > 0 ? 1 : 0;

		for (List<PhaseParam> data : params) {
			rows *= data.size();
		}
		// �ѿ�����������¼
		int[] record = new int[params.size()];
		List<List<PhaseParam>> results = new ArrayList<List<PhaseParam>>();
		// �����ѿ�����
		for (int i = 0; i < rows; i++) {
			List<PhaseParam> row = new ArrayList<PhaseParam>();
			// ���ɵѿ�������ÿ������
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
	 * Description: У���������Ҫ�Ƿ�һ��
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
