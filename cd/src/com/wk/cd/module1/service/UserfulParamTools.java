/**
 * Title: UserfullParamTools.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017��2��13��
 */
package com.wk.cd.module1.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.CmdParamIsNotExistException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 
 * @author zhangj
 */
public class UserfulParamTools {
	private static final Log logger = LogFactory.getLog();
	
	/**
	 * Description: ��ȡ�������������õĲ���������ϴ���
	 * @param param_in_cmd
	 * @param params
	 * @param cmd
	 * @return
	 */
	public static List<List<ParamInfo>> getUsefulParam(
			List<String> param_in_cmd, List<ParamInfo> params,
			String cmd) {
		List<ParamInfo> list = ParamInfo.copy(params);
		List<ParamInfo> index_param = mergeParam(list);
		List<List<ParamInfo>> list_father = new ArrayList<List<ParamInfo>>();
		// ������������Ҫʹ�õ��Ĳ���
		for (String param : param_in_cmd) {
			List<ParamInfo> params_return = new ArrayList<ParamInfo>();
			for (ParamInfo bean : index_param) {
				String param_name = bean.getParam_name();
				String group = bean.getParam_group();
				if (Assert.isEmpty(group)) {
					if (param.equals(param_name)) {
						params_return.add(bean);
					}
				} else {
					if (param.equals(group + "." + param_name)) {
						params_return.add(bean);
					}
				}

			}
			if (Assert.isEmpty(params_return)) {
				throw new CmdParamIsNotExistException()
						.addScene("PARAM", param);
			}
			list_father.add(params_return);
		}
	
		return paramFilter(descartes(list_father));
	}
	/**
	 * Description: ������������Ƕ�ֵ�����������Ѳ�����ɶ�� ������index ��1��ʼ��˳�����
	 * @param list
	 * @return
	 */
	private static List<ParamInfo> mergeParam(List<ParamInfo> list){
		List<ParamInfo> deal_list = new ArrayList<ParamInfo>();
		for (ParamInfo info : list) {
			logger.debug("��ǰ�����Ĳ�������[{}],����ֵ[{}]", info.getParam_name(), info.getParam_value());
			Integer phase_no = info.getPhase_no();
			String value = info.getParam_value();
			// ������Զ�������������׶κŵĲ����������ڲ������У���Ϊ����ʵ����ʵ�����Բ�����Ϊmap��key�������ȥ���ظ�
//			if ((phase_no != null && phase_no > 0) || Assert.isEmpty(value)) {
//				continue;
//			}
			String[] values = null;
			if(Assert.isEmpty(value)){
				deal_list.add(info);
				continue;
			}else if (value.contains("-,-")) {
				values = value.split("-,-");
			} else {
				values = new String[]{value};
			}
			int index = 1;
			for(String sv : values){
				ParamInfo pi = ParamInfo.copy(info);
				pi.setParam_value(sv);
				pi.setIndex(index++);
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
	private static List<List<ParamInfo>> descartes(
			List<List<ParamInfo>> params) {

		// ������ѿ���������
		int rows = params.size() > 0 ? 1 : 0;

		for (List<ParamInfo> data : params) {
			rows *= data.size();
		}
		// �ѿ�����������¼
		int[] record = new int[params.size()];
		List<List<ParamInfo>> results = new ArrayList<List<ParamInfo>>();
		// �����ѿ�����
		for (int i = 0; i < rows; i++) {
			List<ParamInfo> row = new ArrayList<ParamInfo>();
			// ���ɵѿ�������ÿ������
			for (int index = 0; index < record.length; index++) {
				row.add(params.get(index).get(record[index]));
			}
			results.add(row);
			descartesRecord(params, record, params.size() - 1);
		}
		return results;
	}

	private static void descartesRecord(List<List<ParamInfo>> sourceArgs,
			int[] record, int level) {
		record[level] = record[level] + 1;
		if (record[level] >= sourceArgs.get(level).size() && level > 0) {
			record[level] = 0;
			descartesRecord(sourceArgs, record, level - 1);
		}
	}

	private static List<List<ParamInfo>> paramFilter(
			List<List<ParamInfo>> params) {
		List<List<ParamInfo>> results = new ArrayList<List<ParamInfo>>();
		for (List<ParamInfo> list : params) {
			if (checkParam(list)) {
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
	private static boolean check(List<ParamInfo> param) {
		int index = param.get(0).getIndex();
		for (ParamInfo info : param) {
			if (info.getIndex() != index) {
				return false;
			}
		}
		return true;
	}
	

	/**
	 * Description: У���������б��� ���гɶԵĲ��� �Ƿ����һ��
	 * @param param
	 * @return
	 */
	private static boolean checkParam(List<ParamInfo> param) {
		List<List<ParamInfo>> list_father = splitByInstacneParam(param);
		for (List<ParamInfo> list : list_father) {
			if (!check(list)) {
				return false;
			}
		}
		return true;

	}
	
	/**
	 * Description: �Ѳ������� group����
	 * @param param_group_list
	 * @return
	 */
	private static List<List<ParamInfo>> splitByInstacneParam(
			List<ParamInfo> param_group_list) {
		List<String> group_list = getInstance(param_group_list);
		List<List<ParamInfo>> list_father = new ArrayList<List<ParamInfo>>();
		for (String group : group_list) {
			List<ParamInfo> list_child = new ArrayList<ParamInfo>();
			for (ParamInfo info : param_group_list) {
				if (group.equals(info.getParam_group())) {
					list_child.add(info);
				}
			}
			list_father.add(list_child);
		}
		return list_father;

	}
	
	/**
	 * Description: ��ȡ�������� ȥ���ظ���
	 * @param param_group_list
	 * @return
	 */
	private static List<String> getInstance(List<ParamInfo> param_group_list) {
		List<String> param_list = new ArrayList<String>();
		for (ParamInfo info : param_group_list) {
			String group = info.getParam_group();
			if (!Assert.isEmpty(group) && !param_list.contains(group)) {
				param_list.add(group);
			}
		}
		return param_list;

	}


}